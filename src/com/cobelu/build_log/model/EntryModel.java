package com.cobelu.build_log.model;

import java.util.List;
import java.util.Map;

import com.cobelu.build_log.dao_interface.EntryDaoI;
import com.cobelu.build_log.dao_jdbc.EntryDaoJdbc;
import com.cobelu.build_log.entity.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EntryModel {

	/*
	 * Fields
	 */

	private EntryDaoI entryDao;
	private Entry selectedEntry;
	private ObservableList<String> categoriesList;

	/*
	 * Constructor
	 */

	public EntryModel() {
		entryDao = new EntryDaoJdbc();
		// TODO: Replace with empty for deploy
		String[] categories = { "Wing", "Tail", "Fuselage", "Finish" };
		categoriesList = FXCollections.observableArrayList(categories);
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

	public Integer findNumberOfEntries() {
		return entryDao.findNumberOfEntries();
	}

	public Map<String, String> findHoursByCategory() {
		return entryDao.findHoursByCategory();
	}

	public String findTotalHours() {
		return entryDao.findTotalHours();
	}

	public void addCategory(String category) {
		categoriesList.add(category);
	}

	/*
	 * Getters and Setters
	 */

	public Entry getSelectedEntry() {
		return selectedEntry;
	}

	public ObservableList<String> getCategoriesList() {
		return categoriesList;
	}

	public void setSelectedEntry(Entry entry) {
		selectedEntry = entry;
	}

}
