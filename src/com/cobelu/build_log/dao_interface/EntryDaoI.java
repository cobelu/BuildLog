package com.cobelu.build_log.dao_interface;

import java.util.List;

import com.cobelu.build_log.entity.Entry;

public interface EntryDaoI {

	/**
	 * Fetches all Entries from the database.
	 * 
	 * @return A List of all Entries
	 */
	public List<Entry> findAll();

	/**
	 * Fetches a specific Entry from the database.
	 * 
	 * @return The desired Entry (if exists)
	 */
	public Entry find(Entry entry);

	/**
	 * Adds an Entry to the database.
	 * 
	 * @param Entry An Entry to be added
	 */
	public void insert(Entry entry);

	/**
	 * Updates the given Entry with the database's Entry.
	 * 
	 * @param entry The newly updated Entry
	 */
	public void update(Entry entry);

	/**
	 * Removes an Entry from the database.
	 * 
	 * @param Entry The Entry to be removed
	 */
	public void delete(Entry entry);

}
