package au.com.jcloud.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/**
 * Created by david.vittor on 24/08/16.
 */
@MappedSuperclass
public class BaseRole extends IdBean {
	private String name;

	@ManyToMany(mappedBy = "roles")
	protected List<BaseUser> users;

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

	public List<BaseUser> getUsers() {
		return users;
	}

	public void setBaseUsers(List<BaseUser> users) {
		this.users = users;
	}
}
