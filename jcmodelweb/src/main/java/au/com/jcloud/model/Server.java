package au.com.jcloud.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by david.vittor on 5/08/16.
 */
@Entity
@Table(name = "server")
public class Server extends BaseBeanWithName {
	protected String lxdId;
	protected String ip;
	protected String host;
	protected String type;
	protected String alias;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "os_id", referencedColumnName = "id")
	protected OperatingSystem os;
	protected String architecture;
	protected String osVersion;
	protected String description;
	protected Date pdate;
	protected double price;
	protected double cpuLimit;
	protected double cpuCurrent;
	protected double cpuPeak;
	protected double memLimit;
	protected double memCurrent;
	protected double memPeak;
	protected double hddLimit;
	protected double hddCurrent;
	protected double hddPeak;
	protected String sshKey;
	protected long parentServerId;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	protected User user;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	protected Order order;

	@OneToMany(mappedBy = "server", cascade = CascadeType.PERSIST)
	protected List<Service> services;
	@OneToMany(mappedBy = "server", cascade = CascadeType.PERSIST)
	protected List<Request> requests;

	@Override
	public String toString() {
		return super.toString() + " ip=" + ip + " alias=" + alias + " os=" + os;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public OperatingSystem getOs() {
		return os;
	}

	public void setOs(OperatingSystem os) {
		this.os = os;
	}

	public String getArchitecture() {
		return architecture;
	}

	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsversion(String osVersion) {
		this.osVersion = osVersion;
	}

	public double getMemLimit() {
		return memLimit;
	}

	public void setMemLimit(double memLimit) {
		this.memLimit = memLimit;
	}

	public double getMemPeak() {
		return memPeak;
	}

	public void setMemPeak(double memPeak) {
		this.memPeak = memPeak;
	}

	public double getMemCurrent() {
		return memCurrent;
	}

	public void setMemcurrent(double memCurrent) {
		this.memCurrent = memCurrent;
	}

	public double getHddLimit() {
		return hddLimit;
	}

	public void setHddLimit(double hddLimit) {
		this.hddLimit = hddLimit;
	}

	public double getHddPeak() {
		return hddPeak;
	}

	public void setHddPeak(double hddPeak) {
		this.hddPeak = hddPeak;
	}

	public double getHddCurrent() {
		return hddCurrent;
	}

	public void setHddCurrent(double hddCurrent) {
		this.hddCurrent = hddCurrent;
	}

	public String getSshKey() {
		return sshKey;
	}

	public void setSshKey(String sshKey) {
		this.sshKey = sshKey;
	}

	public long getParentServerId() {
		return parentServerId;
	}

	public void setParentServerId(long parentServerId) {
		this.parentServerId = parentServerId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public String getLxdId() {
		return lxdId;
	}

	public void setLxdId(String lxdId) {
		this.lxdId = lxdId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getCpuLimit() {
		return cpuLimit;
	}

	public void setCpuLimit(double cpuLimit) {
		this.cpuLimit = cpuLimit;
	}

	public double getCpuCurrent() {
		return cpuCurrent;
	}

	public void setCpuCurrent(double cpuCurrent) {
		this.cpuCurrent = cpuCurrent;
	}

	public double getCpuPeak() {
		return cpuPeak;
	}

	public void setCpuPeak(double cpuPeak) {
		this.cpuPeak = cpuPeak;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public void setMemCurrent(double memCurrent) {
		this.memCurrent = memCurrent;
	}
}
