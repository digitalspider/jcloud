package au.com.jcloud.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.avaje.ebean.Model;

/**
 * Created by david.vittor on 5/08/16.
 */
@MappedSuperclass
public class IdBean extends Model {
	@Id
	protected Long id;

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [" + id + "]:";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
