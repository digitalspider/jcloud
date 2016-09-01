package au.com.jcloud.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Created by david.vittor on 24/08/16.
 */
@Entity
@Table(name = "role")
public class Role extends IdBean {
	private String name;

	@ManyToMany(mappedBy = "roles")
	protected List<User> users;

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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
