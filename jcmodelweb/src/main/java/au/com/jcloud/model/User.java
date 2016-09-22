package au.com.jcloud.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by david.vittor on 5/08/16.
 */
@Entity
@Table(name = "user")
public class User extends BaseBean {
	protected String username;
	protected String email;
	protected String password;
	protected String firstName;
	protected String lastName;

	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	protected List<Address> addresses;

	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	protected List<Server> servers;

	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	protected List<CreditCard> creditCards;

	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	protected List<Rating> ratings;

	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	protected List<Purchase> purchases;

	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	protected List<Invoice> invoices;

	@ManyToMany
	@JoinTable(name = "userrole", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") )
	protected Set<Role> roles;

	@Override
	public String toString() {
		return super.toString() + " username=" + username + " email=" + email + " firstName=" + firstName + " lastName=" + lastName;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<CreditCard> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
}
