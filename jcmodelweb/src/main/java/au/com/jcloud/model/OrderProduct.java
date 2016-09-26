package au.com.jcloud.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by david.vittor on 17/07/16.
 */
@Entity
@Table(name = "orderproduct")
public class OrderProduct {

	protected int quantity;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	protected Order order;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	protected Product product;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
