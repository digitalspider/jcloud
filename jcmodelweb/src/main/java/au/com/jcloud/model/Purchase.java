package au.com.jcloud.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by david.vittor on 17/07/16.
 */
@Entity
@Table(name = "purchase")
public class Purchase extends BaseBean {
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	protected User user;
	protected Date date;
	protected BigDecimal totalDiscount;
	protected BigDecimal totalSalePrice;
	protected BigDecimal totalShippingPrice;
	protected BigDecimal totalPrice;
	@Column(columnDefinition = "varchar(5)")
	protected String currency;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "billingaddress_id", referencedColumnName = "id")
	protected Address billingAddress;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "shippingaddress_id", referencedColumnName = "id")
	protected Address shippingAddress;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "creditcard_id", referencedColumnName = "id")
	protected CreditCard creditCard;

	@OneToMany(mappedBy = "purchase", cascade = CascadeType.PERSIST)
	protected List<PurchaseProduct> products;

	@OneToMany(mappedBy = "purchase", cascade = CascadeType.PERSIST)
	protected List<Server> servers;

	@OneToMany(mappedBy = "purchase", cascade = CascadeType.PERSIST)
	protected List<Invoice> invoices;

	@OneToOne(mappedBy = "purchase", cascade = CascadeType.PERSIST)
	protected Cart cart;

	@OneToMany(mappedBy = "purchase", cascade = CascadeType.PERSIST)
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

	public List<PurchaseProduct> getProducts() {
		return products;
	}

	public void setProducts(List<PurchaseProduct> products) {
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

	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(BigDecimal totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public BigDecimal getTotalSalePrice() {
		return totalSalePrice;
	}

	public void setTotalSalePrice(BigDecimal totalSalePrice) {
		this.totalSalePrice = totalSalePrice;
	}

	public BigDecimal getTotalShippingPrice() {
		return totalShippingPrice;
	}

	public void setTotalShippingPrice(BigDecimal totalShippingPrice) {
		this.totalShippingPrice = totalShippingPrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
