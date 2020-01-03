package com.cobelu.build_log.view;

import com.cobelu.build_log.entity.Entry;

import javafx.scene.layout.Pane;

public class EntryEditor extends Pane {
	
	private Entry entry;
	
	public EntryEditor(Entry entry) {
		super();
		this.entry = entry;
	}

	public Entry getEntry() {
		return entry;
	}

}
