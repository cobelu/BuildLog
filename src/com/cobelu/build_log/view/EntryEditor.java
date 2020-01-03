package com.cobelu.build_log.view;

import com.cobelu.build_log.entity.Entry;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class EntryEditor extends GridPane {

	private Entry entry;

	public EntryEditor(Entry entry) {
		super();
		this.entry = entry;

		// Geometry
		setAlignment(Pos.CENTER);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(25, 25, 25, 25));

		// Date
		Label dateLabel = new Label("Date:");
		add(dateLabel, 0, 1);
		DatePicker datePicker = new DatePicker(entry.getDate());
		add(datePicker, 1, 1);

		// Minutes
		Label minutesLabel = new Label("Minutes:");
		add(minutesLabel, 0, 2);
		TextField minutesTextField = new TextField(entry.getMinutes().toString());
		add(minutesTextField, 1, 2);

		// Category
		Label categoryLabel = new Label("Category:");
		add(categoryLabel, 0, 3);
		TextField categoryTextField = new TextField(entry.getCategory());
		add(categoryTextField, 1, 3);

		// Title
		Label titleLabel = new Label("Title:");
		add(titleLabel, 0, 4);
		TextField titleTextField = new TextField(entry.getTitle());
		add(titleTextField, 1, 4);

		// Description
		Label descriptionLabel = new Label("Description:");
		add(descriptionLabel, 0, 5);
		TextField descriptionTextField = new TextField(entry.getDescription());
		add(descriptionTextField, 1, 5);
		
		// Submit button
		Button saveButton = new Button("Save");
		// TODO: Save button action
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        System.out.println("Save Button Pressed!");
		    }
		});
		add(saveButton, 0, 6);
		
		// Cancel Button
		Button cancelButton = new Button("Cancel");
		// TODO: Cancel button action
		add(cancelButton, 1, 6);

	}

	public Entry getEntry() {
		return entry;
	}

}
