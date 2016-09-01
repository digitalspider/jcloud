package au.com.jcloud.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by david.vittor on 5/08/16.
 */
@Entity
@Table(name = "server")
public class Server extends BaseBean {
	protected String lxdId;
	protected String ip;
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
}
