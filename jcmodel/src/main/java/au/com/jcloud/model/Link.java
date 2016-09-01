package au.com.jcloud.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by david.vittor on 30/08/16.
 */
@Entity
@Table(name = "link")
public class Link extends BaseBean {
	protected String url;
	protected int clickCount;

	@Override
	public String toString() {
		return super.toString() + " clickCount=" + clickCount+" url=" + url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
