package au.com.jcloud.util;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import au.com.jcloud.actionbean.JCActionBean;

/**
 * Created by david.vittor on 17/08/16.
 */
public class HttpUtil {
	private static final Logger LOG = Logger.getLogger(HttpUtil.class);
	private static Pattern ipFilterPattern;

	static {
		String ipFilter = System.getProperty(Constants.ENV_IP_FILTER, "");
		if (StringUtils.isNotBlank(ipFilter)) {
			ipFilterPattern = Pattern.compile(ipFilter);
		}
	}

	public static boolean isIgnoreIPAddress(String ipAddress) {
		try {
			if (ipFilterPattern != null) {
				Matcher linkMat = ipFilterPattern.matcher(ipAddress);
				return linkMat.matches();
			}
		} catch (Exception e) {
			LOG.error("Error checking ip", e);
		}
		return false;
	}

	public static String getDomain(final HttpServletRequest request) {
		String domain = null;
		if (domain == null) {
			domain = request.getHeader("Host");
		}
		if (domain == null) {
			domain = request.getServerName();
		} else {
			int pos = domain.indexOf(':');
			if (pos > -1)
				domain = domain.substring(0, pos);
		}
		return domain;
	}

	public static String getContextUrl(final HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String contextUrl = url.substring(0, url.length() - request.getRequestURI().length())
				+ request.getContextPath();
		LOG.debug("contextUrl=" + contextUrl);
		return contextUrl;
	}

	public static String getServletUrl(final HttpServletRequest request) {
		String baseUrl = getContextUrl(request);
		baseUrl = baseUrl + request.getServletPath();
		LOG.debug("baseUrl=" + baseUrl);
		return baseUrl;
	}

	public static void sendRedirect(final HttpServletRequest request, HttpServletResponse response,
			String redirectParam) throws IOException {
		String redirectPath = HttpUtil.getServletUrl(request);
		if (redirectParam != null) {
			String redirect = request.getParameter(redirectParam);
			if (StringUtils.isNotEmpty(redirect)) {
				redirectPath += redirect;
			}
		}
		LOG.debug("redirectPath=" + redirectPath);
		response.sendRedirect(response.encodeRedirectURL(redirectPath));
	}

	public static String getUserAgent(final HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}

	public static String getIpAddress(final HttpServletRequest request) {
		String ipAddress = request.getHeader(Constants.HEADER_CDN_X_REAL_IP);
		String xRealIpAddress = request.getHeader(Constants.HEADER_X_REAL_IP);
		String xForwardForIpAddress = request.getHeader(Constants.HEADER_X_FORWARDED_FOR);
		String remoteAddress = request.getRemoteAddr();
		LOG.debug("IP address values: CDN-X-Real-IP header - " + ipAddress + ". X-Real-IP: " + xRealIpAddress
				+ ". x-forwarded-for: " + xForwardForIpAddress + " remoteAddress=" + remoteAddress);
		if (StringUtils.isBlank(ipAddress)) {
			ipAddress = request.getHeader(Constants.HEADER_X_REAL_IP);
			if (StringUtils.isBlank(ipAddress)) {
				ipAddress = request.getHeader(Constants.HEADER_X_FORWARDED_FOR);
			}
		}
		if (ipAddress == null) {
			ipAddress = remoteAddress;
		}
		LOG.debug("IP address=" + ipAddress);
		return ipAddress;
	}

	public static void denyAccess(final HttpServletResponse response) {
		response.addHeader(Constants.HEADER_X_FRAME_OPTIONS, "DENY");
	}

	public static void addRequestNonce(final HttpServletRequest request, String nonceName) {
		if (StringUtils.isEmpty((String) request.getSession().getAttribute(nonceName))) {
			String requestId = generateUniqueRequestId();
			request.getSession().setAttribute(nonceName, requestId);
		}
	}

	public static void validateRequestNonce(final HttpServletRequest request, JCActionBean actionBean, String nonceName)
			throws Exception {
		String requestIdFromForm = actionBean.getRequestId();
		String requestIdFromSession = (String) request.getSession().getAttribute(nonceName);
		if (requestIdFromForm == null || requestIdFromSession == null
				|| !requestIdFromForm.equals(requestIdFromSession)) {
			throw new Exception("Request cannot be verified!");
		}
	}

	public static String generateUniqueRequestId() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
}
