package com.cobelu.build_log.dao_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.cobelu.build_log.dao_interface.EntryDaoI;
import com.cobelu.build_log.entity.Entry;

public class EntryDaoJdbc extends BaseDaoJdbc implements EntryDaoI {

	/*
	 * Fields
	 */

	private final String entryTable = "ENTRY";
	private final String idCol = "ID";
	private final String dateCol = "DATE";
	private final String minutesCol = "MINUTES";
	private final String categoryCol = "CATEGORY";
	private final String titleCol = "TITLE";
	private final String descCol = "DESCRIPTION";

	/*
	 * Methods
	 */

	public List<Entry> findAll() {
		String query = String.format("SELECT * FROM %s;", entryTable);
		List<Entry> entries = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			entries = parseEntriesFrom(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return entries;
	}

	public Entry find(Entry entry) {
		String query = String.format("SELECT * FROM %s WHERE %s=?;", entryTable, idCol);
		Entry foundEntry = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, entry.getId());
			rs = pstmt.executeQuery();
			List<Entry> entries = parseEntriesFrom(rs);
			foundEntry = entries.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return foundEntry;
	}

	public void insert(Entry entry) {
		String query = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?);", entryTable, dateCol,
				minutesCol, categoryCol, titleCol, descCol);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, entry.getDate().toString());
			pstmt.setInt(2, entry.getMinutes());
			pstmt.setString(3, entry.getCategory());
			pstmt.setString(4, entry.getTitle());
			pstmt.setString(5, entry.getDescription());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
	}

	public void update(Entry entry) {
		String query = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?;", entryTable, dateCol,
				minutesCol, categoryCol, titleCol, descCol, idCol);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, entry.getDate().toString());
			pstmt.setInt(2, entry.getMinutes());
			pstmt.setString(3, entry.getCategory());
			pstmt.setString(4, entry.getTitle());
			pstmt.setString(5, entry.getDescription());
			pstmt.setLong(6, entry.getId());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
	}

	public void delete(Entry entry) {
		String query = String.format("DELETE FROM %s WHERE %s=?;", entryTable, idCol);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, entry.getId());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
	}

	public String findTotalHours() {
		String query = String.format("SELECT SUM(%s) AS TOTAL FROM %s;", minutesCol, entryTable);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String prettyHours = null;
		try {
			conn = connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			Integer minutes = rs.getInt("TOTAL");
			prettyHours = prettyHoursMinutes(minutes);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, rs);
		}
		return prettyHours;
	}

	public Map<String, String> findHoursByCategory() {
		String query = String.format("SELECT %s, SUM(%s) AS TOTAL FROM %s GROUP BY %s;", categoryCol, minutesCol,
				entryTable, categoryCol);
		Map<String, String> hoursByCategory = new HashMap<String, String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			String category = rs.getString(categoryCol);
			Integer minutes = rs.getInt("TOTAL");
			String prettyHours = prettyHoursMinutes(minutes);
			hoursByCategory.put(category, prettyHours);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, rs);
		}
		return hoursByCategory;
	}

	public Integer findNumberOfEntries() {
		String query = String.format("SELECT COUNT(*) AS TOTAL FROM %s;", entryTable);
		Integer numEntries = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			numEntries = rs.getInt("TOTAL");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, rs);
		}
		return numEntries;
	}

	/*
	 * Helper Methods
	 */

	/**
	 * Parses entries obtained from a ResultSet.
	 * 
	 * @param rset A ResultSet full of entries
	 * @return A List of Entry objects
	 * @throws SQLException
	 */
	private List<Entry> parseEntriesFrom(ResultSet rs) throws SQLException {
		List<Entry> entries = new LinkedList<Entry>();
		while (rs.next()) {
			Entry entry = new Entry();
			entry.setId(rs.getLong(idCol));
			entry.setDate(LocalDate.parse(rs.getString(dateCol)));
			entry.setMinutes(rs.getInt(minutesCol));
			entry.setCategory(rs.getString(categoryCol));
			entry.setTitle(rs.getString(titleCol));
			entry.setDescription(rs.getString(descCol));
			entries.add(entry);
		}
		return entries;
	}

	private String prettyHoursMinutes(Integer minutes) {
		// Take minutes and find the number of hours
		Integer hours = minutes / 60;
		// Find the number of remaining minutes
		Integer leftoverMinutes = minutes % 60;
		String pretty = "";
		pretty += hours;
		pretty += " hours, ";
		pretty += leftoverMinutes;
		pretty += " minutes";
		return pretty;
	}

}
