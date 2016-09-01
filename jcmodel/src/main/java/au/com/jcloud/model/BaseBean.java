package au.com.jcloud.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;

/**
 * Created by david.vittor on 5/08/16.
 */
@MappedSuperclass
public class BaseBean extends IdBean {
	@CreatedTimestamp
	protected Date cdate;
	@UpdatedTimestamp
	protected Date mdate;
	protected int status;

	@Override
	public String toString() {
		return super.toString() + " status=" + status;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Date getMdate() {
		return mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
