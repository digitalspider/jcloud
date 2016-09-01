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

	public static final String PATH_RESOURCES_TEST = "src/test/resources/";
	public static final String PATH_RESOURCES_MAIN = "src/main/resources/";

	public static final String HEADER_CDN_X_REAL_IP = "CDN-X-Real-IP";
	public static final String HEADER_COUNTRY_CODE = "X-Country-Code";
	public static final String HEADER_DEVICE_TYPE = "X-Device-Type";
	public static final String HEADER_X_FORWARDED_FOR = "x-forwarded-for";
	public static final String HEADER_X_REAL_IP = "X-Real-IP";
	public static final String HEADER_X_FRAME_OPTIONS = "X-FRAME-OPTIONS";
}
