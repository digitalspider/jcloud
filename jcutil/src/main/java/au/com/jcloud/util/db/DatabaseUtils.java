package au.com.jcloud.util.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Handle common database functions
 *
 * @author davidv
 */
public class DatabaseUtils {

	private static Logger LOG = Logger.getLogger(DatabaseUtils.class);
	private static final boolean CLOSE_CONNECTION = false;

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
		return executeQuerySingle(conn, sql, mapping, CLOSE_CONNECTION, params);
	}

	/**
	 * See {@link #executeQueryList(Connection, String, ResultSetMapping, boolean, Object...)} with default closeConnection=false
	 */
	public static <T> List<T> executeQueryList(Connection conn, String sql, ResultSetMapping<T> mapping, Object... params) throws SQLException {
		return executeQueryList(conn, sql, mapping, CLOSE_CONNECTION, params);
	}

	/**
	 * See {@link #executeInsertQuery(Connection, String, boolean, Object...)} with default closeConnection=false
	 */
	public static long executeInsertQuery(Connection conn, String sql, Object... params) throws SQLException {
		return executeInsertQuery(conn, sql, CLOSE_CONNECTION, params);
	}

	/**
	 * See {@link #executeUpdateQuery(Connection, String, boolean, Object...)} with default closeConnection=false
	 */
	public static int executeUpdateQuery(Connection conn, String sql, Object... params) throws SQLException {
		return executeUpdateQuery(conn, sql, CLOSE_CONNECTION, params);
	}

	/**
	 * See {@link #executeInsertQuery(Connection, String, Object, StatementMapping, boolean)} with default closeConnection=false
	 */
	public static <T extends IdBeanIF> T executeInsertQuery(Connection conn, String sql, T object, StatementParameterMapping<T> mapping) throws SQLException {
		return executeInsertQuery(conn, sql, object, mapping, CLOSE_CONNECTION);
	}

	/**
	 * See {@link #executeUpdateQuery(Connection, String, Object, StatementMapping, boolean)} with default closeConnection=false
	 */
	public static <T extends IdBeanIF> int executeUpdateQuery(Connection conn, String sql, T object, StatementParameterMapping<T> mapping) throws SQLException {
		return executeUpdateQuery(conn, sql, object, mapping, CLOSE_CONNECTION);
	}

	/**
	 * See {@link #executeUpdateQuery(Connection, String, Collection, StatementMapping, boolean)} with default closeConnection=false
	 */
	public static <T extends IdBeanIF> void executeUpdateQuery(Connection conn, String sql, Collection<T> object, StatementParameterMapping<T> mapping) throws SQLException {
		executeUpdateQuery(conn, sql, object, mapping, CLOSE_CONNECTION);
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
	static <T> T executeQuerySingle(Connection conn, String sql, ResultSetMapping<T> mapping, boolean closeConnection, Object... params) throws SQLException {
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
			close(rs, stmt);
			if (closeConnection) {
				close(conn);
			}
		}
		return result;
	}

	/**
	 * Execute a SQL query and return a list of objects, using the method {@link ResultSetMapping#map(ResultSet)} to map each item.
	 *
	 * @param conn
	 * @param sql
	 * @param mapping
	 * @param closeConnection
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	static <T> List<T> executeQueryList(Connection conn, String sql, ResultSetMapping<T> mapping, boolean closeConnection, Object... params) throws SQLException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<T> resultList = new ArrayList<>();
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
			close(rs, stmt);
			if (closeConnection) {
				close(conn);
			}
		}
		return resultList;
	}

	/**
	 * Execute an insert query with a given list of parameters and return the new id created
	 *
	 * @param conn
	 * @param sql
	 * @param closeConnection
	 * @param params
	 * @throws SQLException
	 */
	static long executeInsertQuery(Connection conn, String sql, boolean closeConnection, Object... params) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		long newId = 0;
		try {
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			for (Object param : params) {
				setStatementParameterByType(stmt, ++i, param);
			}
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				newId = rs.getLong(1);
			}
		} finally {
			close(rs, stmt);
			if (closeConnection) {
				close(conn);
			}
		}
		return newId;
	}

	/**
	 * Execute a SQL update query with a list of parameters.
	 *
	 * @param conn
	 * @param sql
	 * @param closeConnection
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	static int executeUpdateQuery(Connection conn, String sql, boolean closeConnection, Object... params) throws SQLException {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			stmt = conn.prepareStatement(sql);
			int i = 0;
			for (Object param : params) {
				setStatementParameterByType(stmt, ++i, param);
			}
			result = stmt.executeUpdate();
		} finally {
			close(stmt);
			if (closeConnection) {
				close(conn);
			}
		}
		return result;
	}

	/**
	 * Execute an insert query, populating the Statement using a {@link StatementParameterMapping}
	 *
	 * @param conn
	 * @param sql
	 * @param object
	 * @param mapping
	 * @return
	 * @throws SQLException
	 */
	static <T extends IdBeanIF> T executeInsertQuery(Connection conn, String sql, T object, StatementParameterMapping<T> mapping, boolean closeConnection) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			mapping.mapParams(object, stmt);
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				object.setId(rs.getLong(1));
			}
		} finally {
			DatabaseUtils.close(rs, stmt);
			if (closeConnection) {
				DatabaseUtils.close(conn);
			}
		}
		return object;
	}

	/**
	 * Execute an update query on an object, populating the Statement using a {@link StatementParameterMapping}
	 *
	 * @param conn
	 * @param sql
	 * @param object
	 * @param mapping
	 * @return
	 * @throws SQLException
	 */
	static <T extends IdBeanIF> int executeUpdateQuery(Connection conn, String sql, T object, StatementParameterMapping<T> mapping, boolean closeConnection) throws SQLException {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			stmt = conn.prepareStatement(sql);
			int index = mapping.mapParams(object, stmt);
			stmt.setLong(index, object.getId());
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
	 * Execute an update query on a collection of {@link IdBeanIF} objects, populating the Statement using a {@link StatementParameterMapping}
	 *
	 * @param conn
	 * @param sql
	 * @param objectList
	 * @param mapping
	 * @param closeConnection
	 * @throws SQLException
	 */
	static <T extends IdBeanIF> void executeUpdateQuery(Connection conn, String sql, Collection<T> objectList, StatementParameterMapping<T> mapping, boolean closeConnection) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			for (T object : objectList) {
				int index = mapping.mapParams(object, stmt);
				stmt.setLong(index, object.getId());
				stmt.addBatch();
			}
			stmt.executeBatch();
		} finally {
			DatabaseUtils.close(stmt);
			if (closeConnection) {
				DatabaseUtils.close(conn);
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
		if (param == null) {
			stmt.setString(index, (String) param);
		}
		else if (param instanceof String) {
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
			throw new SQLException("Unknown class of type: " + param.getClass().getSimpleName());
		}
	}
}
