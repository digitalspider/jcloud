package au.com.jcloud.util.db;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Map the object to a {@link Statement}
 *
 * @author davidv
 */
public interface StatementParameterMapping<T> {

	/**
	 * Map the object to a {@link Statement}
	 *
	 * @param object
	 * @param stmt
	 */
	void mapParams(T object, Statement stmt) throws SQLException;
}
