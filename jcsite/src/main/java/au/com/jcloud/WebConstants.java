package au.com.jcloud;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by david on 2/09/16.
 */

public class WebConstants {
	public static final String SHOW_JSP = "show.jsp";
	public static final String EDIT_JSP = "edit.jsp";
	public static final String LIST_JSP = "list.jsp";
	public static final String CART_JSP = "cart.jsp";
	public static final String BILLING_JSP = "billing.jsp";
	public static final String THANKYOU_JSP = "thankyou.jsp";

	public static final String SESSION_ATTRIBUTE_USER = "jcuser";
	public static final String SESSION_ATTRIBUTE_ROLES = "jcroles";
	public static final String SESSION_ATTRIBUTE_DEVICE = "jcdevice";
	public static final String PATH_CLICK_COUNT = "/cc";
	public static final String PATH_PUBLIC = "/public";
	public static final String PATH_ADMIN = "/admin";
	public static final String PATH_REST = "/rest";
	public static final String PATH_SECURE = "/secure";
	public static final String PATH_SECURE_JSP = "/jsp/secure";

	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_INDEX = "/index.jsp";
	public static final String PAGE_RESET = "/reset.jsp";
	public static final String PAGE_PAGE = "/page.jsp";
	public static final String PAGE_CONTACT = "/contact.jsp";
	public static final String PAGE_SECURE = PATH_SECURE_JSP + "/index.jsp";
	public static final String ACTION_REST_LINK = PATH_REST + "/link";
	public static final String ACTION_REST_BLOG = PATH_REST + "/blog";
	public static final String ACTION_PUBLIC_PAGE = PATH_PUBLIC + "/page";
	public static final String ACTION_PUBLIC_CONTACT = PATH_PUBLIC + "/contact";
	public static final String ACTION_SECURE_INDEX = PATH_SECURE + "/index";
	public static final String ACTION_SECURE_LOGIN = PATH_SECURE + "/login";
	public static final String URL_PARAM_REDIRECT = "?r=";
	public static final String URL_CC_PREFIX = PATH_CLICK_COUNT + URL_PARAM_REDIRECT;

	public static final String ENV_IP_FILTER = "ipFilter";

	public static final String FILTER_NAME_IP = "ip";
	public static final String FILTER_NAME_AUTH = "auth";
	public static final String FILTER_NAME_CLICK_COUNT = "cc";
	public static final String FILTER_NAME_DEVICE = "device";
	public static final String FILTER_NAME_CLICKJACK = "clickjack";

	public static final String SESSION_NONCE_UID_LOGIN = "";
	public static final String SESSION_NONCE_UID_REGSITER = "";
	public static final String SESSION_NONCE_UID_PASSWORD = "";
	public static final String SESSION_NONCE_UID_USERACCOUNT = "";

	public static final String REQUEST_DEVICE_TYPE = "deviceType";

	private static Pattern ipFilterPattern;

	static {
		String ipFilter = System.getProperty(ENV_IP_FILTER, "");
		if (StringUtils.isNotBlank(ipFilter)) {
			ipFilterPattern = Pattern.compile(ipFilter);
		}
	}
}
