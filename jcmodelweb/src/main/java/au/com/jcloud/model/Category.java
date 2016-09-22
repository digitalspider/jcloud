package au.com.jcloud.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Created by david.vittor on 17/07/16.
 */
@Entity
@Table(name = "category")
public class Category extends BaseBeanWithName {

	@ManyToMany
	@JoinTable(name = "category_product", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id") )
	protected List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
