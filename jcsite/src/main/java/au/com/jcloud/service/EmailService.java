package au.com.jcloud.service;

import java.util.Properties;

import javax.mail.Message;

import org.apache.commons.lang3.StringUtils;
import org.simplejavamail.email.Email;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.ServerConfig;
import org.simplejavamail.mailer.config.TransportStrategy;

import au.com.jcloud.util.PropertyUtil;

/**
 * Created by david.vittor on 4/08/16.
 */
public class EmailService extends BaseService {

	public static final String EMAIL_PROPERTIES = "email.properties";
	public static final String PROP_EMAIL_HOST = "email.host";
	public static final String PROP_EMAIL_PORT = "email.port";
	public static final String PROP_EMAIL_USERNAME = "email.username";
	public static final String PROP_EMAIL_PASSWORD = "email.password";
	public static final String PROP_DEFAULT_FROM_USERNAME = "default.from.username";
	public static final String PROP_DEFAULT_FROM_EMAIL = "default.from.email";
	public static final String PROP_DEFAULT_TO_USERNAME = "default.to.username";
	public static final String PROP_DEFAULT_TO_EMAIL = "default.to.email";

	private static Properties properties = new Properties();
	private static Mailer mailer;

	public void init() throws Exception {
		if (properties.isEmpty() || mailer == null) {
			properties = PropertyUtil.loadProperties(EMAIL_PROPERTIES);
			validateProperties(properties);

			mailer = new Mailer(new ServerConfig(properties.getProperty(PROP_EMAIL_HOST), Integer.parseInt(properties.getProperty(PROP_EMAIL_PORT)), properties.getProperty(PROP_EMAIL_USERNAME), properties.getProperty(PROP_EMAIL_PASSWORD)), TransportStrategy.SMTP_TLS);
		}
	}

	public void validateProperties(Properties properties) throws Exception {
		if (StringUtils.isBlank(properties.getProperty(PROP_EMAIL_HOST))) {
			throw new Exception("Property " + PROP_EMAIL_HOST + " has not been set! Please update " + EMAIL_PROPERTIES);
		}
		if (StringUtils.isBlank(properties.getProperty(PROP_EMAIL_PORT))) {
			throw new Exception("Property " + PROP_EMAIL_PORT + " has not been set! Please update " + EMAIL_PROPERTIES);
		}
		if (Integer.parseInt(properties.getProperty(PROP_EMAIL_PORT)) < 0) {
			throw new Exception("Property " + PROP_EMAIL_PORT + " needs to be a positive number! Please update " + EMAIL_PROPERTIES);
		}
		if (StringUtils.isBlank(properties.getProperty(PROP_EMAIL_USERNAME))) {
			throw new Exception("Property " + PROP_EMAIL_USERNAME + " has not been set! Please update " + EMAIL_PROPERTIES);
		}
		if (StringUtils.isBlank(properties.getProperty(PROP_EMAIL_PASSWORD))) {
			throw new Exception("Property " + PROP_EMAIL_PASSWORD + " has not been set! Please update " + EMAIL_PROPERTIES);
		}
	}

	public void sendToEmail(String toName, String toEmail, String subject, String message) throws Exception {
		init();
		if (StringUtils.isBlank(properties.getProperty(PROP_DEFAULT_TO_EMAIL))) {
			throw new Exception("Property " + PROP_DEFAULT_TO_EMAIL + " has not been set! Please update " + EMAIL_PROPERTIES);
		}
		if (StringUtils.isBlank(properties.getProperty(PROP_DEFAULT_TO_USERNAME))) {
			throw new Exception("Property " + PROP_DEFAULT_TO_USERNAME + " has not been set! Please update " + EMAIL_PROPERTIES);
		}

		String defaultFromName = properties.getProperty(PROP_DEFAULT_FROM_USERNAME);
		String defaultFromEmail = properties.getProperty(PROP_DEFAULT_FROM_EMAIL);
		sendEmail(defaultFromName, defaultFromEmail, toName, toEmail, subject, message);
	}

	public void sendFromEmail(String fromName, String fromEmail, String subject, String message) throws Exception {
		init();
		if (StringUtils.isBlank(properties.getProperty(PROP_DEFAULT_TO_EMAIL))) {
			throw new Exception("Property " + PROP_DEFAULT_TO_EMAIL + " has not been set! Please update " + EMAIL_PROPERTIES);
		}
		if (StringUtils.isBlank(properties.getProperty(PROP_DEFAULT_TO_USERNAME))) {
			throw new Exception("Property " + PROP_DEFAULT_TO_USERNAME + " has not been set! Please update " + EMAIL_PROPERTIES);
		}
		String defaultToName = properties.getProperty(PROP_DEFAULT_TO_USERNAME);
		String defaultToEmail = properties.getProperty(PROP_DEFAULT_TO_EMAIL);
		sendEmail(fromName, fromEmail, defaultToName, defaultToEmail, subject, message);
	}

	public void sendEmail(String fromName, String fromEmail, String toName, String toEmail, String subject, String message) throws Exception {
		init();
		Email emailMessage = new Email();
		emailMessage.addRecipient(toName, toEmail, Message.RecipientType.TO);
		emailMessage.setFromAddress(fromName, fromEmail);
		emailMessage.setReplyToAddress(fromName, fromEmail);
		emailMessage.setSubject(subject);
		emailMessage.setTextHTML(message);

		mailer.sendMail(emailMessage);
		LOG.info("email has been sent to: " + toEmail + " from: " + fromEmail);
	}
}
