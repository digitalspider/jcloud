package au.com.jcloud.security;

import static au.com.jcloud.util.Constants.HEADER_X_FRAME_OPTIONS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


public class ClickjackFilterTest {

	private ClickjackFilter filter;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private FilterChain chain;

	@Before
	public void setUp() {
		filter = new ClickjackFilter();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		chain = mock(FilterChain.class);
	}
	

	@Test
	public void destroy_shouldDoNothing() throws IOException, ServletException {
		filter.destroy();
	}

	@Test
	public void setConfigMode_shouldSetModeDenyWhenModeParameterInvalid() throws IOException, ServletException {
		String configMode = "INVALID";
		filter.setConfigMode(configMode);
		assertEquals(ClickjackFilter.MODE_DENY, filter.getMode());
	}

	@Test
	public void init_shouldSetModeDenyWhenModeParameterDeny() throws IOException, ServletException {
		String configMode = ClickjackFilter.MODE_DENY;
		filter.setConfigMode(configMode);
		assertEquals(ClickjackFilter.MODE_DENY, filter.getMode());
	}

	@Test(expected = ServletException.class)
	public void init_shouldThrowExceptionWhenExcludePathParameterInvalid() throws IOException, ServletException {
		FilterConfig filterConfig = mock(FilterConfig.class);
		doReturn("[2\\{%").when(filterConfig).getInitParameter(ClickjackFilter.PARAM_EXCLUDE_PATH);
		filter.init(filterConfig);
	}

	@Test
	public void setConfigMode_shouldSetModeSameOriginWhenModeParameterSameOrigin() throws IOException, ServletException {
		String configMode = ClickjackFilter.MODE_SAME_ORIGIN;
		filter.setConfigMode(configMode);
		assertEquals(ClickjackFilter.MODE_SAME_ORIGIN, filter.getMode());
	}

	@Test
	public void init_shouldSetDefaultsWhenFilterConfigEmpty() throws IOException, ServletException {
		FilterConfig filterConfig = mock(FilterConfig.class);
		filter.init(filterConfig);
		assertEquals(ClickjackFilter.MODE_DENY, filter.getMode());
		assertEquals(null, filter.getExcludePathPattern());
	}

	@Test
	public void init_shouldSetExcludePathWhenSingleExcludePath() throws IOException, ServletException {
		FilterConfig filterConfig = mock(FilterConfig.class);
		doReturn("/test.*").when(filterConfig).getInitParameter(ClickjackFilter.PARAM_EXCLUDE_PATH);
		filter.init(filterConfig);
		Pattern pattern = filter.getExcludePathPattern();
		assertNotNull(pattern);
		assertTrue(pattern.matcher("/test123").matches());
	}

	@Test
	public void init_shouldSetExcludePathsWhenMultipleExcludePath() throws IOException, ServletException {
		FilterConfig filterConfig = mock(FilterConfig.class);
		doReturn("\\/test-1\\.ep.*|\\/product-2\\.bk.*").when(filterConfig).getInitParameter(ClickjackFilter.PARAM_EXCLUDE_PATH);
		filter.init(filterConfig);
		Pattern pattern = filter.getExcludePathPattern();
		assertNotNull(pattern);
		assertFalse(pattern.matcher("/test123").matches());
		assertFalse(pattern.matcher("/test-1").matches());
		assertFalse(pattern.matcher("test-1.ep").matches());
		assertTrue(pattern.matcher("/test-1.ep").matches());
		assertTrue(pattern.matcher("/test-1.ep?values").matches());
		assertFalse(pattern.matcher("/product-2.ep?values").matches());
		assertTrue(pattern.matcher("/product-2.bk?values").matches());
	}

	@Test
	public void doFilter_shouldNotSetHeaderWhenNoExcludePaths() throws IOException, ServletException {
		filter.doFilter(request, response, chain);
		assertFalse(response.containsHeader(HEADER_X_FRAME_OPTIONS));
		verify(chain, times(1)).doFilter(request, response);
	}

	@Test
	public void doFilter_shouldSetDenyHeaderWhenExcludePathsDoNotMatchPath() throws IOException, ServletException {
		request.setRequestURI("/");
		filter.setExcludePathPattern("/test");
		filter.doFilter(request, response, chain);
		verify(chain, times(1)).doFilter(request, response);
		String header = response.getHeader(HEADER_X_FRAME_OPTIONS);
		assertEquals(ClickjackFilter.MODE_DENY, header);
	}

	@Test
	public void doFilter_shouldSetNotDenyHeaderWhenExcludePathsMatchPath() throws IOException, ServletException {
		request.setRequestURI("/test2.ok");
		filter.setExcludePathPattern("\\/test|\\/test2.*");
		filter.doFilter(request, response, chain);
		verify(chain, times(1)).doFilter(request, response);
		assertFalse(response.containsHeader(HEADER_X_FRAME_OPTIONS));
	}

	@Test
	public void doFilter_shouldSetNotDenyHeaderWhenAlreadySet() throws IOException, ServletException {
		request.setRequestURI("/test2.ok");
		response.addHeader(HEADER_X_FRAME_OPTIONS, "TEST");
		filter.setExcludePathPattern("\\/test|\\/test2.*");
		filter.doFilter(request, response, chain);
		verify(chain, times(1)).doFilter(request, response);
		String header = response.getHeader(HEADER_X_FRAME_OPTIONS);
		assertEquals("TEST", header);
	}
}