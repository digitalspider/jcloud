package au.com.booktopia.utils.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Handle common database functions
 *
 * @author davidv
 */
public class DatabaseUtils {

	private static Logger LOG = Logger.getLogger(DatabaseUtils.class);

	/**
	 * Close a Connection
	 *
	 * @param conn the Connection to close
	 */
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			LOG.error(e, e);
		}
	}

	/**
	 * Close a ResultSet
	 *
	 * @param rs the ResultSet to close
	 */
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				LOG.error(e, e);
			}
		}
	}

	/**
	 * Close a Statement
	 *
	 * @param stmt the Statement to close
	 */
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				LOG.error(e, e);
			}
		}
	}

	/**
	 * Close a ResultSet and Statement, in that order.
	 *
	 * @param rs the ResultSet to close
	 * @param stmt the Statement to close
	 */
	public static void close(ResultSet rs, Statement stmt) {
		close(rs);
		close(stmt);
	}

	/**
	 * Close a Statement and Connection, in that order.
	 *
	 * @param stmt the Statement to close
	 * @param conn the Connection to close
	 */
	public static void close(Statement stmt, Connection conn) {
		close(stmt);
		close(conn);
	}

	/**
	 * Close a ResultSet, Statement and Connection, in that order.
	 *
	 * @param rs the ResultSet to close
	 * @param stmt the Statement to close
	 * @param conn the Connection to close
	 */
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		close(rs);
		close(stmt);
		close(conn);
	}

	/**
	 * See {@link #executeQuerySingle(Connection, String, ResultSetMapping, boolean, Object...)} with default closeConnection=false
	 */
	public static <T> T executeQuerySingle(Connection conn, String sql, ResultSetMapping<T> mapping, Object... params) throws SQLException {
		return executeQuerySingle(conn, sql, mapping, false, params);
	}

	/**
	 * Execute a SQL query and return an object populating it using the method {@link ResultSetMapping#map(ResultSet)}
	 *
	 * @param conn
	 * @param sql
	 * @param mapping
	 * @param closeConnection
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static <T> T executeQuerySingle(Connection conn, String sql, ResultSetMapping<T> mapping, boolean closeConnection, Object... params) throws SQLException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		T result = null;
		try {
			stmt = conn.prepareStatement(sql);
			int i = 0;
			for (Object param : params) {
				setStatementParameterByType(stmt, ++i, param);
			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				result = mapping.mapRow(rs);
			}
		} finally {
			DBConnection.close(rs, stmt);
			if (closeConnection) {
				DBConnection.close(conn);
			}
		}
		return result;
	}

	/**
	 * See {@link #executeQueryList(Connection, String, ResultSetMapping, boolean, Object...)} with default closeConnection=false
	 */
	public static <T> List<T> executeQueryList(Connection conn, String sql, ResultSetMapping<T> mapping, Object... params) throws SQLException {
		return executeQueryList(conn, sql, mapping, false, params);
	}

	/**
	 * Execute a SQL query and return a list of objects, using the method {@link ResultSetMapping#map(ResultSet)} to map each item.
	 *
	 * @param conn
	 * @param sql
	 * @param mapping
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static <T> List<T> executeQueryList(Connection conn, String sql, ResultSetMapping<T> mapping, boolean closeConnection, Object... params) throws SQLException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<T> resultList = new ArrayList<T>();
		try {
			stmt = conn.prepareStatement(sql);
			int i = 0;
			for (Object param : params) {
				setStatementParameterByType(stmt, ++i, param);
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				T result = null;
				try {
					result = mapping.mapRow(rs);
				} catch (Exception e) {
					LOG.error(e, e); // Log exception and continue to next row
				}
				if (result != null) {
					resultList.add(result);
				}
			}
		} finally {
			DBConnection.close(rs, stmt);
			if (closeConnection) {
				DBConnection.close(conn);
			}
		}
		return resultList;
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
			DBConnection.close(stmt);
			if (closeConnection) {
				DBConnection.close(conn);
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
			DBConnection.close(stmt, conn);
			if (closeConnection) {
				DBConnection.close(conn);
			}
		}
	}

	/**
	 * Set a statement parameter given a generic object, at location index.
	 *
	 * @param stmt
	 * @param index
	 * @param param
	 * @throws SQLException
	 */
	private static void setStatementParameterByType(PreparedStatement stmt, int index, Object param) throws SQLException {
		if (param instanceof String) {
			stmt.setString(index, (String) param);
		}
		else if (param instanceof Boolean) {
			stmt.setBoolean(index, (Boolean) param);
		}
		else if (param instanceof Long) {
			stmt.setLong(index, (Long) param);
		}
		else if (param instanceof Integer) {
			stmt.setInt(index, (Integer) param);
		}
		else if (param instanceof Double) {
			stmt.setDouble(index, (Double) param);
		}
		else if (param instanceof Float) {
			stmt.setFloat(index, (Float) param);
		}
		else if (param instanceof BigDecimal) {
			stmt.setBigDecimal(index, (BigDecimal) param);
		}
		else {
			throw new SQLException("Unknown class of type: " + (param != null ? param.getClass().getSimpleName() : "null"));
		}
	}
}