package au.com.jcloud.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;

import au.com.jcloud.enums.Status;
import au.com.jcloud.model.Address;
import au.com.jcloud.model.CreditCard;
import au.com.jcloud.model.User;
import net.sourceforge.stripes.integration.spring.SpringBean;

/**
 * Created by david on 5/08/16.
 */
public class UserService extends BaseService {

	@SpringBean
	private EncryptService encryptService;

	public User createUser(String username, String firstName, String lastName, String email, String password)
			throws Exception {
		User user = getByUsername(username);
		if (user != null) {
			throw new Exception("Username already exists! Please select a different one");
		}
		user = getByEmail(email);
		if (user != null) {
			throw new Exception("This email is already registered! Please select a different one");
		}
		user = new User();
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		String passValue = getPassword(username, password);
		user.setPassword(passValue);
		user.setStatus(Status.ENABLED.value());
		LOG.info("creating new user: " + user);

		Ebean.save(user);
		// EBeanServerService.getEBeanServer().save(user);
		return user;
	}

	private String getPassword(String username, String password) throws IOException {
		String uservalue = username + ":" + password;
		return encryptService != null ? encryptService.md5(uservalue) : password;
	}

	public User updatePassword(String username, String password) throws Exception {
		User user = getByUsername(username);
		if (user == null) {
			throw new Exception("Could not find user: " + username);
		}
		String passValue = getPassword(username, password);
		user.setPassword(passValue);
		Ebean.save(user);
		user = getByUsername(username);
		return user;
	}


	public Address getDefaultAddress(User user) {
		List<Address> addresses = user.getAddresses();
		if (addresses.isEmpty()) {
			addresses = Ebean.find(Address.class).where().eq("user_id",user.getId()).findList();
		}
		if (!addresses.isEmpty()) {
			Address address = addresses.get(0);
			return address;
		}
		return null;
	}

	public CreditCard getDefaultCreditCard(User user) {
		List<CreditCard> creditCards = user.getCreditCards();
		if (creditCards.size()>0) {
			CreditCard creditCard = creditCards.get(0);
			return creditCard;
		}
		return null;
	}

	public User get(int id) {
		User user = Ebean.find(User.class, id);
		return user;
	}

	public User getByUsernameOrEmail(String usernameOrEmail) {
		User user = getByUsername(usernameOrEmail);
		if (user != null) {
			return user;
		}
		user = getByEmail(usernameOrEmail);
		return user;
	}

	public User getByUsername(String username) {
		User user = Ebean.find(User.class).select("id, username, email, status, firstName, lastName").where()
				.eq("username", username).findUnique();
		return user;
	}

	public User getByEmail(String username) {
		User user = Ebean.find(User.class).select("id, username, email, status, firstName, lastName").where()
				.eq("email", username).findUnique();
		return user;
	}

	public List<User> getListByUsername(String username) {
		List<User> users = Ebean.find(User.class).select("id, username, email, status, firstName, lastName").where()
				.eq("username", username).findList();
		return users;
	}

	public List<User> getListByEmail(String username) {
		List<User> users = Ebean.find(User.class).select("id, username, email, status, firstName, lastName").where()
				.eq("email", username).findList();
		return users;
	}

	public User getUserByAuth(String username, String password) throws IOException {
		String passValue = getPassword(username, password);
		User user = null;
		if (StringUtils.isNotBlank(username)) {
			user = Ebean.find(User.class).select("id, username, email, status, firstName, lastName").where()
					.or(Expr.eq("username", username), Expr.eq("email", username)).eq("password", passValue)
					.eq("status", Status.ENABLED.value()).findUnique();
		}
		return user;
	}

	public EncryptService getEncryptService() {
		return encryptService;
	}

	public void setEncryptService(EncryptService encryptService) {
		this.encryptService = encryptService;
	}

	public static void main(String[] args) {
		try {
			EBeanServerService.createEbeanServer();
			// EBeanServerService service = new EBeanServerService(true);
			UserService us = new UserService();
			System.out.println("us=" + us);
			User user = us.createUser("test", "first", "last", "email", "pass");
			// user.setStatus(StatusService.Status.DISABLED.name());
			// Ebean.save(user);
			System.out.println("user=" + user);
			System.out.println("test=" + us.getByUsername("test"));
			System.out.println("te%=" + us.getByUsername("te%"));
			System.out.println("auth=" + us.getUserByAuth("test", "pass"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
