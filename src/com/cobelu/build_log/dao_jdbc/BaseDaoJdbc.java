package com.cobelu.build_log.dao_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.DbUtils;

public class BaseDaoJdbc {

	/*
	 * Fields
	 */

	protected final String DB_DRIVER = "org.sqlite.JDBC";
	protected final String DB_PREFIX = "jdbc:sqlite:src/resources/databases/"; // TODO: Dynamic
	protected final String DB_NAME = "buildlog.db"; // TODO: Dynamic
	protected final String DB_URL = DB_PREFIX + DB_NAME;

	/*
	 * Constructor
	 */

	public Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/*
	 * Methods
	 */

	public void close(Connection conn) {
		DbUtils.closeQuietly(conn);
	}

	public void close(Statement stmt) {
		DbUtils.closeQuietly(stmt);
	}

	public void close(ResultSet rs) {
		DbUtils.closeQuietly(rs);
	}

	public void close(Connection conn, Statement stmt, ResultSet rs) {
		DbUtils.closeQuietly(conn, stmt, rs);
	}

}
