package au.com.jcloud.model;

import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by david.vittor on 17/07/16.
 */
@Entity
@Table(name = "invoiceline")
public class InvoiceLine extends BaseBean {
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_id", referencedColumnName = "id")
	protected Invoice invoice;
	protected String description;
	protected BigDecimal price;
	protected Currency currency;

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
