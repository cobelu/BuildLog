package com.cobelu.build_log.view;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.JPanel;

import com.cobelu.build_log.entity.Entry;
import com.cobelu.build_log.model.EntryModel;

public class EntryPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private EntryModel entryModel;
	private JList<Entry> list;

	/**
	 * Create the panel.
	 */
	public EntryPanel(EntryModel entryModel) {
		this.entryModel = entryModel;
		setLayout(new BorderLayout(0, 0));

		list = new JList<Entry>();
		/*
		 * On a double-click, a dialog box for that selected entry will appear
		 * 
		 * https://www.comp.nus.edu.sg/~cs3283/ftp/Java/swingConnect/tech_topics/jlist_1/jlist.html
		 */
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					// int index = list.locationToIndex(e.getPoint());
					Entry selectedEntry = list.getSelectedValue();
					EntryFrame entryFrame = new EntryFrame(selectedEntry);
					entryFrame.setVisible(true);
				}
			}
		};
		list.addMouseListener(mouseListener);
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
