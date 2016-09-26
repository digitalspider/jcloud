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
@Table(name = "creditcard")
public class CreditCard extends BaseBean {
	protected String type;
	protected String expiry;
	protected String cardSuffix;
	protected String token;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	protected User user;

	@OneToMany(mappedBy = "creditCard", cascade = CascadeType.PERSIST)
	protected List<Order> orders;

	@Override
	public String toString() {
		return super.toString() + " type=" + type + " expiry=" + expiry;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public String getCardSuffix() {
		return cardSuffix;
	}

	public void setCardSuffix(String cardSuffix) {
		this.cardSuffix = cardSuffix;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
