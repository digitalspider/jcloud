package au.com.jcloud.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by david.vittor on 2/09/16.
 */

@Entity
@Table(name = "clickcountsummary")
public class ClickCountSummary extends BaseBean {
	protected Date date;
	protected String url;
	protected String count;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
