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

/**
 * Created by david.vittor on 17/08/16.
 */
public class HttpUtil {
	private static final Logger LOG = Logger.getLogger(HttpUtil.class);

	public static boolean matchesPattern(Pattern pattern, String input) {
		try {
			if (pattern != null) {
				Matcher matcher = pattern.matcher(input);
				return matcher.matches();
			}
		} catch (Exception e) {
			LOG.error(e, e);
		}
		return false;
	}

	public static boolean matchesRegex(String regex, String input) {
		Pattern pattern = Pattern.compile(regex);
		return matchesPattern(pattern, input);
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
	
	public static PathParts getPathParts(HttpServletRequest request) {
		return getPathParts(request.getPathInfo());
	}
	
    public static PathParts getPathParts(String pathInfo) {
        LOG.debug("pathInfo="+pathInfo);
        if (pathInfo!=null) {
        	if (pathInfo.startsWith("/")) {
        		pathInfo = pathInfo.substring(1);
        	}
        	return new PathParts(pathInfo.split("/"));
        }
        return new PathParts();
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
		return request.getHeader(Constants.HEADER_USER_AGENT);
	}

	public static String getReferrer(final HttpServletRequest request) {
		return request.getHeader(Constants.HEADER_REFERER);
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

	public static void validateRequestNonce(final HttpServletRequest request, String requestId, String nonceName)
			throws Exception {
		String requestIdFromSession = (String) request.getSession().getAttribute(nonceName);
		if (requestId == null || requestIdFromSession == null
				|| !requestId.equals(requestIdFromSession)) {
			throw new Exception("Request cannot be verified!");
		}
	}

	public static String generateUniqueRequestId() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
}
