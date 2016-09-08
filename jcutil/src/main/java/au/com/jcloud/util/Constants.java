package au.com.jcloud.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by david on 26/05/16.
 */
public class Constants {
	public static final String DATE_FORMAT_ISO = "yyyy-MM-dd";
	public static final String DATE_FORMAT_AUD = "dd/MM/yyyy";
	public static final String TIME_FORMAT_24_SEC = "HH:mm:ss";
	public static final String TIME_FORMAT_24 = "HH:mm";
	public static final String TIME_FORMAT_12 = "hh:mm";

	public static final DateFormat DATEFORMAT_TIME_24_SEC = new SimpleDateFormat(TIME_FORMAT_24_SEC);
	public static final DateFormat DATEFORMAT_TIME_24 = new SimpleDateFormat(TIME_FORMAT_24);
	public static final DateFormat DATEFORMAT_TIME_12 = new SimpleDateFormat(TIME_FORMAT_12);

	public static final DateFormat DATEFORMAT_DATE_DB = new SimpleDateFormat(DATE_FORMAT_ISO);
	public static final DateFormat DATEFORMAT_DATETIME_DB = new SimpleDateFormat(DATE_FORMAT_AUD+" "+TIME_FORMAT_24);

	public static final DateFormat DATEFORMAT_DATE_AUD = new SimpleDateFormat(DATE_FORMAT_AUD);
	public static final DateFormat DATEFORMAT_DATETIME_AUD = new SimpleDateFormat(DATE_FORMAT_AUD+" "+TIME_FORMAT_24);

	public static final String PARAM_OVERRIDE = "override";
	public static final String PROP_SYS_OS_NAME = "os.name";
	public static final String OS_WIN = "win";

	public static final String PATH_RESOURCES_TEST = "src/test/resources/";
	public static final String PATH_RESOURCES_MAIN = "src/main/resources/";

	public static final String FILENAME_PROPERTIES_BATCH = "batch.properties";
	public static final String FILENAME_PROPERTIES_JC = "jc.properties";
	public static final String FILENAME_PROPERTIES_DB = "db.properites";
	public static final String FILENAME_PROPERTIES_EMAIL = "email.properties";
	public static final String FILENAME_PROPERTIES_EBEAN = "ebean.properties";
	public static final String FILENAME_PROPERTIES_EBEAN_TEST = "test-ebean.properties";

	public static final String HEADER_CDN_X_REAL_IP = "CDN-X-Real-IP";
	public static final String HEADER_COUNTRY_CODE = "X-Country-Code";
	public static final String HEADER_DEVICE_TYPE = "X-Device-Type";
	public static final String HEADER_X_FORWARDED_FOR = "x-forwarded-for";
	public static final String HEADER_X_REAL_IP = "X-Real-IP";
	public static final String HEADER_X_FRAME_OPTIONS = "X-FRAME-OPTIONS";
	public static final String HEADER_USER_AGENT = "User-Agent";
	public static final String HEADER_REFERER = "referer";
	
	public static final String EBEAN = "ebean";
	public static final String EBEAN_DEFAULT = EBEAN+".default";
	public static final String EBEAN_JNDI_DS = EBEAN+".jndi.ds";

	public static final String PROP_FEEDS="feeds";
	public static final String PROP_FEEDS_ENABLE="feeds.enable";
}
