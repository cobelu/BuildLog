package com.cobelu.build_log.dao_jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDaoJdbc {

	/**
	 * The Data Access Object super class for all JDBC Data Access Objects. All data
	 * JDBC DAOs should inherit from this class. Provides basic connection opening
	 * and closing functionality to ensure ALL SQL ACTIVITY IS CLOSED.
	 * 
	 * @author cobelu
	 */

	private final String DB_DRIVER = "org.sqlite.JDBC";
	private final String DB_PREFIX = "jdbc:sqlite:src/resources/databases/";
	private final String DATABASE_NAME = "buildlog.db";
	private final String DB_URL = DB_PREFIX + DATABASE_NAME;

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rset = null;

	/**
	 * Opens a Connection, Statement, and ResultSet for performing and executing a
	 * SQL query.
	 * 
	 * @param query An SQL query to perform
	 * @return A ResultSet from the query
	 * @throws SQLException
	 * @throws IOException
	 */
	public ResultSet openAndQuery(String query) throws SQLException {
		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL);
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rset;
	}

	/**
	 * Opens a Connection and Statement to make an update.
	 * 
	 * @param update An SQL statement representing some kind of database update
	 */
	public void openAndUpdate(String update) {
		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL);
			stmt = conn.createStatement();
			stmt.executeUpdate(update);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public PreparedStatement prepareStatement(String sql) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}

	/**
	 * Closes the Connection, Statement, and ResultSet when processing the query is
	 * finished. This is the clean up method to be called every time when a query is
	 * done.
	 */
	public void closeAfterQuery() {
		try {
			stmt.close();
			conn.close();
			rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes the Connection and Statement when processing the update is finished.
	 * This is the clean up method to be called every time when an update is done.
	 */
	public void closeAfterUpdate() {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
