package au.com.jcloud.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import au.com.jcloud.model.User;

/**
 * Created by david on 26/05/16.
 */
public class Constants {
	public static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static final String PATH_RESOURCES_TEST = "src/test/resources/";
	public static final String PATH_RESOURCES_MAIN = "src/main/resources/";

	public static final String SHOW_JSP = "show.jsp";
	public static final String EDIT_JSP = "edit.jsp";
	public static final String LIST_JSP = "list.jsp";

	public static final String SESSION_ATTRIBUTE_USER = "jcuser";
	public static final String SESSION_ATTRIBUTE_ROLES = "jcroles";
	public static final String SESSION_ATTRIBUTE_DEVICE = "jcdevice";
	public static final String PATH_PUBLIC = "/public";
	public static final String PATH_ADMIN = "/admin";
	public static final String PATH_SECURE = "/secure";
	public static final String PATH_SECURE_JSP = "/jsp/secure";

	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_INDEX = "/index.jsp";
	public static final String PAGE_RESET = "/reset.jsp";
	public static final String PAGE_CONTACT = "/contact.jsp";
	public static final String PAGE_SECURE = PATH_SECURE_JSP + "/index.jsp";
	public static final String ACTION_PUBLIC_LINK = PATH_PUBLIC + "/link";
	public static final String ACTION_PUBLIC_CONTACT = PATH_PUBLIC + "/contact";
	public static final String ACTION_SECURE_INDEX = PATH_SECURE + "/index";
	public static final String ACTION_SECURE_LOGIN = PATH_SECURE + "/login";
	public static final String URL_PARAM_LOGIN_REDIRECT = "?r=";

	public static final String HEADER_CDN_X_REAL_IP = "CDN-X-Real-IP";
	public static final String HEADER_COUNTRY_CODE = "X-Country-Code";
	public static final String HEADER_DEVICE_TYPE = "X-Device-Type";
	public static final String HEADER_X_FORWARDED_FOR = "x-forwarded-for";
	public static final String HEADER_X_REAL_IP = "X-Real-IP";
	public static final String HEADER_X_FRAME_OPTIONS = "X-FRAME-OPTIONS";

	public static final String ENV_IP_FILTER = "ipFilter";

	public static final String SESSION_NONCE_UID_LOGIN = "";
	public static final String SESSION_NONCE_UID_REGSITER = "";
	public static final String SESSION_NONCE_UID_PASSWORD = "";
	public static final String SESSION_NONCE_UID_USERACCOUNT = "";

	public static final String REQUEST_DEVICE_TYPE = "deviceType";

	public static final User systemUser;

	static {
		systemUser = new User();
		systemUser.setId(1L);
		systemUser.setName("SYSTEM");
	}

	public static User getSystemUser() {
		return systemUser;
	}
}
