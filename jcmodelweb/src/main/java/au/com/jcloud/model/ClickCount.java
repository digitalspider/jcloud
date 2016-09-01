package au.com.jcloud.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by david.vittor on 2/09/16.
 */

@Entity
@Table(name = "clickcount")
public class ClickCount extends BaseBean {
	protected String domain;
	protected String url;
	protected String username;
	protected String userAgent;
	protected String ipAddress;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
