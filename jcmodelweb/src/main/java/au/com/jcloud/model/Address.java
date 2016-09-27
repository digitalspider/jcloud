package au.com.jcloud.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by david.vittor on 5/08/16.
 */
@Entity
@Table(name = "address")
public class Address extends BaseBean {
	protected String address;
	protected String city;
	protected String state;
	protected String postcode;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	protected User user;

	@OneToMany(mappedBy = "shippingAddress", cascade = CascadeType.PERSIST)
	protected List<Purchase> purchases;

	@Override
	public String toString() {
		return super.toString() + " address=" + address + " city=" + city + " state=" + state + " postcode=" + postcode;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
}
