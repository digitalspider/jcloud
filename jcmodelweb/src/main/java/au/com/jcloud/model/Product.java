package au.com.jcloud.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by david.vittor on 17/07/16.
 */
@Entity
@Table(name = "product")
public class Product extends BaseBeanWithName {
	protected String description;
	protected String type;
	protected BigDecimal costPrice;
	protected BigDecimal listPrice;
	protected BigDecimal salePrice;
	protected String currency;
	protected Product parent;

	@ManyToMany(mappedBy = "products")
	protected List<Category> categories;

	@OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
	protected List<OrderProduct> orders;

	@OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
	protected List<CartItem> cartItems;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getListPrice() {
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public Product getParent() {
		return parent;
	}

	public void setParent(Product parent) {
		this.parent = parent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<OrderProduct> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderProduct> order) {
		this.orders = orders;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
