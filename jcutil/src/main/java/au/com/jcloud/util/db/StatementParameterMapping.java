package au.com.jcloud.util.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Map the object to a {@link Statement}
 * 
 * See {@link DatabaseUtils}
 *
 * @author davidv
 */
public interface StatementParameterMapping<T> {

	/**
	 * Map the object to a {@link PreparedStatement}, return the index of the next insertion
	 *
	 * @param object the object to be inserted into the statement
	 * @param stmt the statement to be populated from the object
	 * @return
	 */
	int mapParams(T object, PreparedStatement stmt) throws SQLException;
}
