package au.com.booktopia.utils.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Map a ResultSet to an object.
 *
 * @author davidv
 */
public interface ResultSetMapping<T> {

	/**
	 * Populate an object with the values from a {@link ResultSet}
	 *
	 * @param rs the ResultSet from the SQL query
	 * @return the new object
	 */
	T mapRow(ResultSet rs) throws SQLException;
}
