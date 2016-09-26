package au.com.jcloud.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by david.vittor on 17/07/16.
 */
@Entity
@Table(name = "order")
public class Order extends BaseBean {
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	protected User user;
	protected Date date;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "billingAddress_id", referencedColumnName = "id")
	protected Address billingAddress;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "shippingAddress_id", referencedColumnName = "id")
	protected Address shippingAddress;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "creditCard_id", referencedColumnName = "id")
	protected CreditCard creditCard;

	@ManyToMany
	@JoinTable(name = "orderproduct", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id") )
	protected List<Product> products;

	@OneToMany(mappedBy = "order")
	protected List<Server> servers;

	@OneToMany(mappedBy = "order")
	protected List<Invoice> invoices;

	@OneToOne(mappedBy = "order")
	protected Cart cart;

	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
	protected List<Request> requests;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
}
