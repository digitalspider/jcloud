package au.com.jcloud.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by david.vittor on 1/09/16.
 */
@MappedSuperclass
public class BaseBeanWithName extends BaseBean {
	@Column(nullable = false)
	protected String name;

	@Override
	public String toString() {
		return super.toString() + " name=" + name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
