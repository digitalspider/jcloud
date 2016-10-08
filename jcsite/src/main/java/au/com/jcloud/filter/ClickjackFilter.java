package au.com.jcloud.filter;

import static au.com.jcloud.util.Constants.HEADER_X_FRAME_OPTIONS;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import au.com.jcloud.WebConstants;

/**
 * A filter to prevent to clickjacking.
 * Adapted from https://www.owasp.org/index.php/ClickjackFilter_for_Java_EE
 */
@WebFilter(filterName= WebConstants.FILTER_NAME_CLICKJACK, urlPatterns = "/*")
public class ClickjackFilter extends BaseFilter {
	
	public static final String MODE_DENY = "DENY";
	public static final String MODE_SAME_ORIGIN = "SAMEORIGIN";
	public static final String PARAM_MODE = "mode";
	public static final String PARAM_EXCLUDE_PATH = "excludePath";

	private String mode = MODE_DENY;
	private Pattern excludePathPattern;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);

		setConfigMode(filterConfig.getInitParameter(PARAM_MODE));
		String excludePath = filterConfig.getInitParameter(PARAM_EXCLUDE_PATH);
		if (StringUtils.isNotBlank(excludePath)) {
			try {
				excludePathPattern = Pattern.compile(excludePath);
			} catch (PatternSyntaxException e) {
				throw new ServletException("Invalid regular expression for ClickjackFilter parameter " + PARAM_EXCLUDE_PATH + ". ERROR=" + e.getMessage(), e);
			}
		}
	}
	
	protected void setConfigMode(String configMode) {
		if (configMode != null) {
			if (configMode.equals(MODE_DENY) || configMode.equals(MODE_SAME_ORIGIN)) {
				mode = configMode;
			} else {
				LOG.error("Unsupported configMode: " + configMode);
			}
		}
	}
	
	@Override
	public boolean filterAction(final HttpServletRequest request, final HttpServletResponse response,
	                         FilterChain filterChain) throws IOException, ServletException {

		LOG.debug(response.containsHeader(HEADER_X_FRAME_OPTIONS) + " " + request.getRequestURL());

		String path = request.getRequestURI();
		// If the "Clickjack Filter Deny" filter has already set the header, don't overwrite it
		// when the "Clickjack Filter Same Origin" filter is called
		if (excludePathPattern != null && !response.containsHeader(HEADER_X_FRAME_OPTIONS)) {
			Matcher matcher = excludePathPattern.matcher(path);
			if (!matcher.matches()) {
				response.addHeader(HEADER_X_FRAME_OPTIONS, mode);
			}
		}
		// NB Add header before calling chain.doFilter*/
		filterChain.doFilter(request, response);
		return true;
	}
	
	protected String getMode() {
		return mode;
	}

	protected Pattern getExcludePathPattern() {
		return excludePathPattern;
	}

	protected void setExcludePathPattern(String excludePathPattern) {
		this.excludePathPattern = Pattern.compile(excludePathPattern);
	}
}
