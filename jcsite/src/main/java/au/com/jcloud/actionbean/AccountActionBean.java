package au.com.jcloud.actionbean;

import static au.com.jcloud.WebConstants.PATH_SECURE;
import static au.com.jcloud.actionbean.AccountActionBean.JSP_BINDING;

import javax.annotation.security.PermitAll;

import com.avaje.ebean.Ebean;

import au.com.jcloud.model.Address;
import au.com.jcloud.model.User;
import au.com.jcloud.service.UserService;
import au.com.jcloud.util.PathParts;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontBind;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.Validate;

/**
 * Created by david.vittor on 3/08/16.
 */
@PermitAll
@UrlBinding(PATH_SECURE + JSP_BINDING)
public class AccountActionBean extends JCSecureActionBean {

	public static final String JSP_BINDING = "/account";

	@Validate(required = true, minlength = 2, maxlength = 64, on = "save")
	private String username;
	@Validate(required = true, minlength = 8, maxlength = 64, on = "{save, changepwd}")
	private String password;
	@Validate(required = true, minlength = 2, maxlength = 64, on = "save", converter = EmailTypeConverter.class)
	private String email;
	@Validate(required = true, minlength = 2, maxlength = 32, on = "save")
	private String firstname;
	@Validate(required = true, minlength = 2, maxlength = 32, on = "save")
	private String lastname;
	@Validate(required = true, minlength = 2, maxlength = 64, on = "{save, changepwd}")
	private String newusername;
	@Validate(required = true, minlength = 8, maxlength = 64, on = "{save, changepwd}")
	private String newpassword;

	@Validate(required = false, minlength = 2, maxlength = 64, on = "save")
	private String address;
	@Validate(required = false, minlength = 2, maxlength = 64, on = "save")
	private String city;
	@Validate(required = false, minlength = 2, maxlength = 3, on = "save")
	private String state;
	@Validate(required = false, minlength = 2, maxlength = 4, on = "save")
	private String postcode;

	@SpringBean
	private UserService userService;

	@Override
	public String getJSPBinding() {
		return JSP_BINDING;
	}

	@PermitAll
	@DontValidate
	@DontBind
	@DefaultHandler
	@Override
	public Resolution action() {
		User user = getUser();
		username = user.getUsername();
		firstname = user.getFirstName();
		lastname = user.getLastName();
		email = user.getEmail();

		Address defaultAddress = userService.getDefaultAddress(user);
		if (defaultAddress != null) {
			address = defaultAddress.getAddress();
			city = defaultAddress.getCity();
			state = defaultAddress.getState();
			postcode = defaultAddress.getPostcode();
		}

		PathParts pathParts = getPathParts(); // 0=account, 1=edit
		LOG.debug("pathParts=" + pathParts);
		if (pathParts.size() > 1 && pathParts.get(1).equals("edit")) {
			LOG.debug("go edit = " + getEditResolution());
			return getEditResolution();
		}
		return getShowResolution();
	}

	@HandlesEvent("save")
	public Resolution save() throws Exception {
		User user = getUser();
		try {
			user.setUsername(username);
			user.setFirstName(firstname);
			user.setLastName(lastname);
			user.setEmail(email);
			Ebean.save(user);

			Address defaultAddress = userService.getDefaultAddress(user);
			if (defaultAddress == null) {
				defaultAddress = new Address();
				defaultAddress.setUser(user);
			}
			defaultAddress.setAddress(address);
			defaultAddress.setCity(city);
			defaultAddress.setState(state);
			defaultAddress.setPostcode(postcode);
			Ebean.save(defaultAddress);

			return getShowResolution();
		} catch (Exception e) {
			LOG.error("Error saving user " + user + ". " + e, e);
		}
		return getEditResolution();
	}

	@HandlesEvent("changepwd")
	public Resolution changePassword() throws Exception {
		User user = getUser();
		// TODO: Chnage password
		return action();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
