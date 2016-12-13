package au.com.jcloud.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class TestDAOImpl {

	private Connection connection;

	public TestDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public TestBean getBrand(long id) throws SQLException {
		String sql = "SELECT UIDPK, TEST_NAME, TEST_IMAGE FROM TBRAND WHERE UIDPK = ?";
		TestBean brand = DatabaseUtils.executeQuerySingle(getConnection(), sql, new BrandResultSetMapping(), id);
		return brand;
	}

	public TestBean getBrandByBrandName(String brandName) throws SQLException {
		String sql = "SELECT UIDPK, TEST_NAME, TEST_IMAGE FROM TBRAND WHERE TEST_NAME = ?";
		TestBean brand = DatabaseUtils.executeQuerySingle(getConnection(), sql, new BrandResultSetMapping(), brandName);
		return brand;
	}

	public List<TestBean> getAllTest() throws SQLException {
		String sql = "SELECT UIDPK, TEST_NAME, TEST_IMAGE FROM TBRAND";
		List<TestBean> brandList = DatabaseUtils.executeQueryList(getConnection(), sql, new BrandResultSetMapping());
		return brandList;
	}

	public TestBean create(String testName, String testImage) throws SQLException {
		String sql = "INSERT INTO TBRAND (TEST_NAME, TEST_IMAGE) VALUES (?,?)";
		long newId = DatabaseUtils.executeInsertQuery(getConnection(), sql, testName, testImage);
		TestBean brand = new TestBean();
		brand.setTestName(testName);
		brand.setTestImage(testImage);
		brand.setId(newId);
		return brand;
	}

	public TestBean create(TestBean test) throws SQLException {
		String sql = "INSERT INTO TBRAND (TEST_NAME, TEST_IMAGE) VALUES (?,?)";
		test = DatabaseUtils.executeInsertQuery(getConnection(), sql, test, new TestStatementMapping());
		return test;
	}

	public void update(TestBean test) throws SQLException {
		String sql = "UPDATE TBRAND SET TEST_NAME=?, TEST_IMAGE=? WHERE UIDPK=?";
		DatabaseUtils.executeUpdateQuery(getConnection(), sql, test, new TestStatementMapping());
	}

	public void update(Collection<TestBean> brandList) throws SQLException {
		String sql = "UPDATE TEST SET TEST_NAME=?, TEST_IMAGE=? WHERE UIDPK=?";
		DatabaseUtils.executeUpdateQuery(getConnection(), sql, brandList, new TestStatementMapping());
	}

	private class BrandResultSetMapping implements ResultSetMapping<TestBean> {
		@Override
		public TestBean mapRow(ResultSet rs) throws SQLException {
			TestBean brand = new TestBean();
			brand.setId(rs.getLong("UIDPK"));
			brand.setTestName(rs.getString("TEST_NAME"));
			brand.setTestImage(rs.getString("TEST_IMAGE"));
			return brand;
		}
	}

	private class TestStatementMapping implements StatementParameterMapping<TestBean> {
		@Override
		public int mapParams(TestBean brand, PreparedStatement stmt) throws SQLException {
			int i = 0;
			stmt.setString(++i, brand.getTestName());
			stmt.setString(++i, brand.getTestImage());
			return ++i;
		}
	}

	public Connection getConnection() {
		return connection;
	}
}
