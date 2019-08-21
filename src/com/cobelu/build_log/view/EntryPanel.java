package com.cobelu.build_log.view;

import javax.swing.JPanel;

import com.cobelu.build_log.entity.Entry;
import com.cobelu.build_log.model.EntryModel;
import javax.swing.JTable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;

public class EntryPanel extends JPanel {

	private EntryModel entryModel;
	private JList<Entry> list;

	/**
	 * Create the panel.
	 */
	public EntryPanel(EntryModel entryModel) {
		this.entryModel = entryModel;

		list = new JList<Entry>();
		updateEntryList();
		add(list);
	}

	private void updateEntryList() {
		/*
		 * Get the entries from the model.
		 * 
		 * Note how the toArray() method is gross and requires an input of a useless,
		 * blank array.
		 */
		Entry[] entries = entryModel.findAll().toArray(new Entry[0]);
		// Update the list
		list.setListData(entries);
	}

}
