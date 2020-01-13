package com.cobelu.build_log.dao_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.cobelu.build_log.dao_interface.CategoryDaoI;

public class CategoryDaoJdbc extends BaseDaoJdbc implements CategoryDaoI {

	/*
	 * Fields
	 */

	private final String categoryTable = "CATEGORY";
	private final String categoryCol = "CATEGORY";

	/*
	 * Methods
	 */

	@Override
	public List<String> findAll() {
		String query = String.format("SELECT * FROM %s;", categoryTable);
		List<String> categories = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			categories = parseCategoriesFrom(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return categories;
	}

	@Override
	public String find(String category) {
		String query = String.format("SELECT * FROM %s WHERE %s=?;", categoryTable, categoryCol);
		String foundCategory = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			List<String> categories = parseCategoriesFrom(rs);
			foundCategory = categories.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return foundCategory;
	}

	@Override
	public void insert(String category) {
		String query = String.format("INSERT INTO %s (%s) VALUES (?);", categoryTable, categoryCol);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, category);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
	}

	@Override
	public void delete(String category) {
		String query = String.format("DELETE FROM %s WHERE %s=?;", categoryTable, categoryCol);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, category);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
	}

	/*
	 * Helpers
	 */

	private List<String> parseCategoriesFrom(ResultSet rs) throws SQLException {
		List<String> categories = new LinkedList<String>();
		while (rs.next()) {
			String category = rs.getString(categoryCol);
			categories.add(category);
		}
		return categories;
	}

}
