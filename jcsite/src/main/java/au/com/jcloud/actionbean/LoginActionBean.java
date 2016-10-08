package au.com.jcloud.actionbean;

import static au.com.jcloud.WebConstants.ACTION_SECURE_LOGIN;
import static au.com.jcloud.WebConstants.PAGE_INDEX;
import static au.com.jcloud.WebConstants.PAGE_LOGIN;
import static au.com.jcloud.WebConstants.PAGE_RESET;
import static au.com.jcloud.WebConstants.PAGE_SECURE;
import static au.com.jcloud.WebConstants.URL_PARAM_REDIRECT;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import au.com.jcloud.enums.FormType;
import au.com.jcloud.model.User;
import au.com.jcloud.service.EmailService;
import au.com.jcloud.service.FormSubmissionCountService;
import au.com.jcloud.service.TokenService;
import au.com.jcloud.service.UserService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.Validate;

/**
 * Created by david.vittor on 3/08/16.
 */
@UrlBinding(ACTION_SECURE_LOGIN)
public class LoginActionBean extends JCActionBean {

	@SpringBean
	private UserService userService;
	@SpringBean
	private EmailService emailService;
	@SpringBean
	private TokenService tokenService;
	@SpringBean
	private FormSubmissionCountService formSubmissionCountService;
	@Validate(required = true, minlength = 2, maxlength = 64, on = "{login, forgot}")
	private String username;
	@Validate(required = true, minlength = 8, maxlength = 64, on = "{login, reset}")
	private String password;
	@Validate(required = true, minlength = 2, maxlength = 64, on = "register", converter = EmailTypeConverter.class)
	private String email;
	@Validate(required = true, minlength = 2, maxlength = 32, on = "register")
	private String firstname;
	@Validate(required = true, minlength = 2, maxlength = 32, on = "register")
	private String lastname;
	@Validate(required = true, minlength = 2, maxlength = 64, on = "register")
	private String newusername;
	@Validate(required = true, minlength = 8, maxlength = 64, on = "register")
	private String newpassword;

	public static final String PARAM_USERNAME = "username";
	public static final String PARAM_TOKEN = "token";

	@Before(stages = LifecycleStage.BindingAndValidation)
	public void presubmit() {
		LOG.debug("presubmit()");
	}

	@HandlesEvent("login")
	public Resolution login() throws Exception {
		String ipAddress = getIpAddress();
		LOG.info("login attempt for user=" + username + " from ip=" + ipAddress);
		// Ensure user is valid
		if (validateAlreadyLoggedIn()) {
			return getSourcePageResolution();
		}

		// Prevent too many logins from the same ipAddress
		try {
			formSubmissionCountService.allow(ipAddress, FormType.LOGIN);
		} catch (Exception e) {
			addGlobalValidationError(ACTION_SECURE_LOGIN + ".tooManyAttempts", username);
			return getSourcePageResolution();
		}

		// Try authenticate the user
		User user = userService.getUserByAuth(username, password);
		if (user == null) {
			formSubmissionCountService.increment(ipAddress, FormType.LOGIN);
			addGlobalValidationError(ACTION_SECURE_LOGIN + ".invalidAttempt", username);
			return getSourcePageResolution();
		}
		else {
			context.setUser(user);
		}

		LOG.info("login successful for user=" + username);
		String redirect = PAGE_SECURE;
		String referrer = getReferrer();
		String path = getConextPath();
		if (path != null) {
			path += PAGE_LOGIN;
		}
		LOG.debug("referrer=" + referrer);
		LOG.debug("path=" + path);
		if (StringUtils.isNotBlank(referrer) && StringUtils.isNotBlank(path) && referrer.startsWith(path)) {
			String queryString = referrer.substring(path.length());
			LOG.debug("queryString=" + queryString);

			if (queryString != null && queryString.startsWith(URL_PARAM_REDIRECT)) {
				redirect = queryString.substring(URL_PARAM_REDIRECT.length());
			}
		}
		return new RedirectResolution(redirect);
	}

	@HandlesEvent("register")
	public Resolution register() throws Exception {
		String ipAddress = getIpAddress();
		LOG.info("register attempt for user=" + newusername + " from ip=" + ipAddress);
		if (validateAlreadyLoggedIn()) {
			return getContext().getSourcePageResolution();
		}

		// Prevent too many registations from the same ipAddress
		try {
			formSubmissionCountService.allow(ipAddress, FormType.REGISTRATION);
		} catch (Exception e) {
			addGlobalValidationError(ACTION_SECURE_LOGIN + ".tooManyAttempts", username);
			return getSourcePageResolution();
		}

		// Ensure user does not already exist
		if (!isValidNewUser()) {
			return getContext().getSourcePageResolution();
		}

		// Create the new user
		User user = userService.createUser(newusername, firstname, lastname, email, newpassword);
		LOG.info("user created=" + user);
		try {
			emailService.sendFromEmail(user.getFullName(), user.getEmail(), "New User Registered", "User=" + user);
		} catch (Exception e) {
			LOG.error("Email not sent " + e.getMessage());
		}

		// Register
		formSubmissionCountService.increment(ipAddress, FormType.REGISTRATION);

		// save the logged in user to the session
		context.setUser(user);

		return new RedirectResolution(PAGE_SECURE);
	}

	private boolean isValidNewUser() {
		if (userService.getByUsername(newusername) != null) {
			addGlobalValidationError(ACTION_SECURE_LOGIN + ".newusername.alreadyExists");
			return false;
		}
		if (userService.getByEmail(email) != null) {
			addGlobalValidationError(ACTION_SECURE_LOGIN + ".email.alreadyExists");
			return false;
		}
		return true;
	}

	public static boolean isResetReady(HttpServletRequest request) {
		String username = request.getParameter("username");
		String token = request.getParameter("token");
		boolean resetReady = StringUtils.isNotBlank(username) && StringUtils.isNotBlank(token);
		return resetReady;
	}

	@HandlesEvent("forgot")
	public Resolution forgot() throws Exception {
		LOG.info("forgot for user=" + username);
		if (validateAlreadyLoggedIn()) {
			return getContext().getSourcePageResolution();
		}
		User user = userService.getByUsernameOrEmail(username);
		if (user == null) {
			addGlobalValidationError(ACTION_SECURE_LOGIN + ".username.invalid");
			return getContext().getSourcePageResolution();
		}
		String basePath = getConextPath();
		LOG.info("basePath=" + basePath);
		String url = basePath + PAGE_RESET + "?" + PARAM_USERNAME + "=" + user.getUsername() + "&" + PARAM_TOKEN + "=" + tokenService.generateAndRecordToken(user.getUsername());
		LOG.info("url=" + url);
		String message = "Hi " + user.getFirstName() + ",<br/><br/>" + "You have 30 minutes to use the below link to reset your password<br/>" + "<a href=\"" + url + "\">" + url + "</a><br/><br/>" + "Yours sincerely,<br/>" + "The JCloud team<br/>";
		emailService.sendToEmail(user.getFullName(), user.getEmail(), "JCloud Password Reset", message);
		return getContext().getSourcePageResolution();
	}

	@HandlesEvent("reset")
	public Resolution reset() throws Exception {
		LOG.info("reset() called");
		String referrer = getReferrer();
		LOG.info("referrer=" + referrer);
		if (StringUtils.isNotBlank(referrer) && referrer.contains("?")) {
			String queryString = referrer.split("\\?")[1];
			LOG.info("queryString=" + queryString);
			if (StringUtils.isNotBlank(queryString) && queryString.contains("&")) {
				String[] queryParams = queryString.split("\\&");
				Map<String, String> queryParamMap = new HashMap<String, String>();
				for (String queryParam : queryParams) {
					String[] queryParamParts = queryParam.split("=");
					queryParamMap.put(queryParamParts[0], queryParamParts[1]);
				}
				String usernameParam = queryParamMap.get(PARAM_USERNAME);
				LOG.info("usernameParam=" + usernameParam);
				if (StringUtils.isBlank(usernameParam)) {
					addGlobalValidationError(ACTION_SECURE_LOGIN + ".reset.userMissing", usernameParam);
					return getContext().getSourcePageResolution();
				}
				String token = queryParamMap.get(PARAM_TOKEN);
				LOG.info("token=" + token);
				if (StringUtils.isBlank(token)) {
					addGlobalValidationError(ACTION_SECURE_LOGIN + ".reset.tokenMissing", usernameParam);
					return getContext().getSourcePageResolution();
				}
				if (!tokenService.validateToken(usernameParam, token)) {
					addGlobalValidationError(ACTION_SECURE_LOGIN + ".reset.invalidToken", usernameParam);
					return getContext().getSourcePageResolution();
				}
				User user = userService.updatePassword(usernameParam, password);
				context.setUser(user);
				tokenService.clearToken(usernameParam);
			}
		}
		return new RedirectResolution(PAGE_INDEX);
	}

	private boolean validateAlreadyLoggedIn() {
		if (context.getUser() != null) {
			addGlobalValidationError(ACTION_SECURE_LOGIN + ".alreadyLoggedIn", context.getUser().getUsername());
			return true;
		}
		return false;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public TokenService getTokenService() {
		return tokenService;
	}

	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getNewusername() {
		return newusername;
	}

	public void setNewusername(String newusername) {
		this.newusername = newusername;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
}
