package au.com.jcloud.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by david.vittor on 17/07/16.
 */
@Entity
@Table(name = "os")
public class OperatingSystem extends BaseBean {

	private String distribution;
	private String version;
	private String architecture;

	@OneToMany(mappedBy = "os")
	protected List<Server> servers;

	public String getDistribution() {
		return distribution;
	}

	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getArchitecture() {
		return architecture;
	}

	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}

	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}
}
