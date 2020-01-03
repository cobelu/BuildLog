package com.cobelu.build_log.model;

import java.util.List;

import com.cobelu.build_log.dao_jdbc.EntryDaoJdbc;
import com.cobelu.build_log.entity.Entry;
import com.cobelu.build_log.dao_interface.EntryDaoI;

public class EntryModel {

	private EntryDaoI entryDao;
	private Entry selectedEntry;

	/*
	 * Constructor
	 */

	public EntryModel() {
		entryDao = new EntryDaoJdbc();
	}

	/*
	 * Methods
	 */

	public List<Entry> findAll() {
		 return entryDao.findAll();
	}

	public Entry find(Entry entry) {
		return entryDao.find(entry);
	}

	public void insert(Entry entry) {
		entryDao.insert(entry);
	}

	public void update(Entry entry) {
		entryDao.update(entry);
	}

	public void delete(Entry entry) {
		entryDao.delete(entry);
	}
	
	/*
	 * Getters and Setters
	 */

	public Entry getSelectedEntry() {
		return selectedEntry;
	}
	
	public void setSelectedEntry(Entry entry) {
		selectedEntry = entry;
	}
	
}
