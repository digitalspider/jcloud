package au.com.jcloud.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class TestDAOImpl {

	private static final String SELECT_QUERY = "SELECT UIDPK, TEST_NAME, TEST_IMAGE FROM TEST";
	private static final String INSERT_QUERY = "INSERT INTO TEST (TEST_NAME, TEST_IMAGE) VALUES (?,?)";
	private static final String UPDATE_QUERY = "UPDATE TEST SET TEST_NAME=?, TEST_IMAGE=? WHERE UIDPK=?";

	private Connection connection;

	public TestDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public TestBean getTest(long id) throws SQLException {
		String sql = SELECT_QUERY + " WHERE UIDPK = ?";
		TestBean test = DatabaseUtils.executeQuerySingle(getConnection(), sql, new TestResultSetMapping(), id);
		return test;
	}

	public TestBean getTestByTestName(String testName) throws SQLException {
		String sql = SELECT_QUERY + " WHERE TEST_NAME = ?";
		TestBean test = DatabaseUtils.executeQuerySingle(getConnection(), sql, new TestResultSetMapping(), testName);
		return test;
	}

	public List<TestBean> getAllTest() throws SQLException {
		String sql = SELECT_QUERY;
		List<TestBean> testList = DatabaseUtils.executeQueryList(getConnection(), sql, new TestResultSetMapping());
		return testList;
	}

	public TestBean create(String testName, String testImage) throws SQLException {
		String sql = INSERT_QUERY;
		long newId = DatabaseUtils.executeInsertQuery(getConnection(), sql, testName, testImage);
		TestBean test = new TestBean();
		test.setTestName(testName);
		test.setTestImage(testImage);
		test.setId(newId);
		return test;
	}

	public TestBean create(TestBean test) throws SQLException {
		String sql = INSERT_QUERY;
		test = DatabaseUtils.executeInsertQuery(getConnection(), sql, test, new TestStatementMapping());
		return test;
	}

	public void update(TestBean test) throws SQLException {
		String sql = UPDATE_QUERY;
		DatabaseUtils.executeUpdateQuery(getConnection(), sql, test, new TestStatementMapping());
	}

	public void update(Collection<TestBean> testList) throws SQLException {
		String sql = UPDATE_QUERY;
		DatabaseUtils.executeUpdateQuery(getConnection(), sql, testList, new TestStatementMapping());
	}

	private class TestResultSetMapping implements ResultSetMapping<TestBean> {
		@Override
		public TestBean mapRow(ResultSet rs) throws SQLException {
			TestBean test = new TestBean();
			test.setId(rs.getLong("UIDPK"));
			test.setTestName(rs.getString("TEST_NAME"));
			test.setTestImage(rs.getString("TEST_IMAGE"));
			return test;
		}
	}

	private class TestStatementMapping implements StatementParameterMapping<TestBean> {
		@Override
		public int mapParams(TestBean test, PreparedStatement stmt) throws SQLException {
			int i = 0;
			stmt.setString(++i, test.getTestName());
			stmt.setString(++i, test.getTestImage());
			return ++i;
		}
	}

	public Connection getConnection() {
		return connection;
	}
}
