package au.com.jcloud.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Handle common database functions, using the {@link StatementParameterMapping}.
 *
 * @author davidv
 */
public class DatabaseUtilsExtended {

	/**
	 * See {@link #executeInsertQuery(Connection, String, Object, StatementMapping, boolean)} with default closeConnection=false
	 */
	public static <T extends IdBeanIF> T executeInsertQuery(Connection conn, String sql, T object, StatementParameterMapping<T> mapping) throws SQLException {
		return executeInsertQuery(conn, sql, object, mapping, false);
	}

	/**
	 * Execute an update query, populating the Statement using a {@link StatementMapping}
	 *
	 * @param conn
	 * @param sql
	 * @param object
	 * @param mapping
	 * @return
	 * @throws SQLException
	 */
	public static <T extends IdBeanIF> T executeInsertQuery(Connection conn, String sql, T object, StatementParameterMapping<T> mapping, boolean closeConnection) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			mapping.mapParams(object, stmt);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				object.setId(rs.getLong(1));
			}
		} finally {
			DatabaseUtils.close(stmt);
			if (closeConnection) {
				DatabaseUtils.close(conn);
			}
		}
		return object;
	}

	/**
	 * See {@link #executeUpdateQuery(Connection, String, Object, StatementMapping, boolean)} with default closeConnection=false
	 */
	public static <T> int executeUpdateQuery(Connection conn, String sql, T object, StatementParameterMapping<T> mapping) throws SQLException {
		return executeUpdateQuery(conn, sql, object, mapping, false);
	}

	/**
	 * Execute an update query, populating the Statement using a {@link StatementMapping}
	 *
	 * @param conn
	 * @param sql
	 * @param object
	 * @param mapping
	 * @return
	 * @throws SQLException
	 */
	public static <T> int executeUpdateQuery(Connection conn, String sql, T object, StatementParameterMapping<T> mapping, boolean closeConnection) throws SQLException {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			stmt = conn.prepareStatement(sql);
			mapping.mapParams(object, stmt);
			result = stmt.executeUpdate();
		} finally {
			DatabaseUtils.close(stmt);
			if (closeConnection) {
				DatabaseUtils.close(conn);
			}
		}
		return result;
	}

	/**
	 * See {@link #executeUpdateQuery(Connection, String, List, StatementMapping, boolean)} with default closeConnection=false
	 */
	public static <T> void executeUpdateQuery(Connection conn, String sql, List<T> object, StatementParameterMapping<T> mapping) throws SQLException {
		executeUpdateQuery(conn, sql, object, mapping, false);
	}

	/**
	 * Execute an update query on a list of objects, populating the Statement using a {@link StatementMapping}
	 *
	 * @param conn
	 * @param sql
	 * @param objectList
	 * @param mapping
	 * @param closeConnection
	 * @throws SQLException
	 */
	public static <T> void executeUpdateQuery(Connection conn, String sql, List<T> objectList, StatementParameterMapping<T> mapping, boolean closeConnection) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			for (T object : objectList) {
				mapping.mapParams(object, stmt);
				stmt.addBatch();
			}
			stmt.executeBatch();
		} finally {
			DatabaseUtils.close(stmt, conn);
			if (closeConnection) {
				DatabaseUtils.close(conn);
			}
		}
	}

}
