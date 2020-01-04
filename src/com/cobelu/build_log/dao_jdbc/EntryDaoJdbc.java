package com.cobelu.build_log.dao_jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import com.cobelu.build_log.dao_interface.EntryDaoI;
import com.cobelu.build_log.entity.Entry;

public class EntryDaoJdbc extends BaseDaoJdbc implements EntryDaoI {

	private final String entryTable = "ENTRY";
	private final String idCol = "ID";
	private final String dateCol = "DATE";
	private final String minutesCol = "MINUTES";
	private final String categoryCol = "CATEGORY";
	private final String titleCol = "TITLE";
	private final String descCol = "DESCRIPTION";

	public EntryDaoJdbc() {
		super();
	}

	public List<Entry> findAll() {
		List<Entry> entries = null;
		String query = "SELECT * FROM " + entryTable + ";";
		ResultSet rs;
		try {
			rs = openAndQuery(query);
			entries = parseEntriesFrom(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAfterQuery();
		}
		return entries;
	}

	public Entry find(Entry entry) {
		String query = "";
		query += "SELECT * FROM ";
		query += entryTable;
		query += " WHERE ";
		query += idCol;
		query += "=";
		query += entry.getId();
		query += ";";
		List<Entry> entries = null;
		Entry rtnEntry = null;
		ResultSet rs;
		try {
			rs = openAndQuery(query);
			entries = parseEntriesFrom(rs);
			entry = entries.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} finally {
			closeAfterQuery();
		}
		return rtnEntry;
	}

	public void insert(Entry entry) {
		String insert = "";
		insert += "INSERT INTO ";
		insert += entryTable;
		insert += "(";
//		insert += idCol;
//		insert += ", ";
		insert += dateCol;
		insert += ", ";
		insert += minutesCol;
		insert += ", ";
		insert += categoryCol;
		insert += ", ";
		insert += titleCol;
		insert += ", ";
		insert += descCol;
		insert += ") VALUES(\"";
//		insert += entry.getId().toString();
//		insert += ", \"";
		insert += entry.getDate().toString();
		insert += "\", ";
		insert += entry.getMinutes().toString();
		insert += ", \"";
		insert += entry.getCategory();
		insert += "\", \"";
		insert += entry.getTitle();
		insert += "\", \"";
		insert += entry.getDescription();
		insert += "\");";
		openAndUpdate(insert);
		closeAfterUpdate();
	}

	public void update(Entry entry) {
		String update = "";
		update += "UPDATE ";
		update += entryTable;
		update += " SET ";
//		update += idCol;
//		update += "=";
//		update += entry.getId();
//		update += ", ";
		update += dateCol;
		update += "=\"";
		update += entry.getDate().toString();
		update += "\", ";
		update += minutesCol;
		update += "=";
		update += entry.getMinutes().toString();
		update += ", ";
		update += categoryCol;
		update += "=\"";
		update += entry.getCategory();
		update += "\", ";
		update += titleCol;
		update += "=\"";
		update += entry.getTitle();
		update += "\", ";
		update += descCol;
		update += "=\"";
		update += entry.getDescription();
		update += "\" WHERE ";
		update += idCol;
		update += "=";
		update += entry.getId();
		update += ";";
		System.err.println(update);
		openAndUpdate(update);
		closeAfterUpdate();
	}

	public void delete(Entry entry) {
		String delete = "";
		delete += "DELETE FROM ";
		delete += entryTable;
		delete += " WHERE ";
		delete += idCol;
		delete += "=";
		delete += entry.getId();
		delete += ";";
		openAndUpdate(delete);
		closeAfterUpdate();
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

}
