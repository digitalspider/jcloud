package au.com.jcloud.filter;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import au.com.jcloud.WebConstants;
import au.com.jcloud.util.HttpUtil;

import static au.com.jcloud.WebConstants.ENV_IP_FILTER;

/**
 * Created by david.vittor on 2/09/16.
 */
@WebFilter(filterName= WebConstants.FILTER_NAME_IP, urlPatterns = "/*")
public class IPScreeningFilter extends BaseFilter {

	private static Pattern blacklistIpFilterPattern;

	static {
		String ipFilter = System.getProperty(ENV_IP_FILTER, "");
		if (StringUtils.isNotBlank(ipFilter)) {
			blacklistIpFilterPattern = Pattern.compile(ipFilter);
		}
	}

	@Override
	public boolean filterAction(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		if (blacklistIpFilterPattern!=null) {
			String ipAddress = HttpUtil.getIpAddress(request);
			if (StringUtils.isNotBlank(ipAddress)) {
				if (HttpUtil.matchesPattern(blacklistIpFilterPattern, ipAddress)) {
					throw new ServletException("ip="+ipAddress+" has been BLOCKED");
				}
			}
		}
		return false;
	}
}
