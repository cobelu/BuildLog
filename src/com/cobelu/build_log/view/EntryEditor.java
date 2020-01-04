package com.cobelu.build_log.view;

import java.time.LocalDate;

import com.cobelu.build_log.entity.Entry;
import com.cobelu.build_log.model.Model;

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

	/*
	 * Fields
	 */

	private BullyStage stage;
	private Entry entry;
	private Model model;
	private DatePicker datePicker;
	private TextField minutesTextField;
	private TextField categoryTextField;
	private TextField titleTextField;
	private TextField descriptionTextField;
	private Button saveButton;
	private Button cancelButton;

	/*
	 * Constructors
	 */

	public EntryEditor(BullyStage stage, Model model) {
		super();
		this.stage = stage;
		this.model = model;

		// Geometry
		setAlignment(Pos.CENTER);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(25, 25, 25, 25));

		// Date
		Label dateLabel = new Label("Date:");
		add(dateLabel, 0, 1);
		datePicker = new DatePicker(LocalDate.now());
		add(datePicker, 1, 1);

		// Minutes
		Label minutesLabel = new Label("Minutes:");
		add(minutesLabel, 0, 2);
		minutesTextField = new TextField();
		add(minutesTextField, 1, 2);

		// Category
		Label categoryLabel = new Label("Category:");
		add(categoryLabel, 0, 3);
		categoryTextField = new TextField();
		add(categoryTextField, 1, 3);

		// Title
		Label titleLabel = new Label("Title:");
		add(titleLabel, 0, 4);
		titleTextField = new TextField();
		add(titleTextField, 1, 4);

		// Description
		Label descriptionLabel = new Label("Description:");
		add(descriptionLabel, 0, 5);
		descriptionTextField = new TextField();
		add(descriptionTextField, 1, 5);

		// Submit button
		saveButton = new Button("Save");
		// TODO: Save button action
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				onSaveButtonPress();
			}
		});
		add(saveButton, 0, 6);

		// Cancel button
		cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				onCancelButtonPress();
			}
		});
		add(cancelButton, 1, 6);
	}

	public EntryEditor(BullyStage stage, Model model, Entry entry) {
		this(stage, model);
		this.entry = entry;

		// Populate with the values of a given entry
		datePicker.setValue(entry.getDate());
		minutesTextField.setText(entry.getMinutes().toString());
		categoryTextField.setText(entry.getCategory());
		titleTextField.setText(entry.getTitle());
		descriptionTextField.setText(entry.getDescription());
	}

	/*
	 * Methods
	 */

	private void onSaveButtonPress() {
		// Harvest the data from the fields
		harvestFields(); // TODO: Add error handling
		// Alert the model
		model.getEntryModel().update(entry); // TODO: Fix SQL error
	}

	private void onCancelButtonPress() {
		// Hide the scene and go back to main stage
		stage.openMainStage();
	}

	private void harvestFields() {
		entry.setDate(datePicker.getValue());
		entry.setMinutes(Integer.parseInt(minutesTextField.getText()));
		entry.setCategory(categoryTextField.getText());
		entry.setTitle(titleTextField.getText());
		entry.setDescription(descriptionTextField.getText());
	}

	/*
	 * Getters and Setters
	 */

	public BullyStage getStage() {
		return stage;
	}

	public Model getModel() {
		return model;
	}

	public Entry getEntry() {
		return entry;
	}

}
