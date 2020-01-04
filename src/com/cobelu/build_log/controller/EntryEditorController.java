package com.cobelu.build_log.controller;

import java.time.LocalDate;

import com.cobelu.build_log.entity.Entry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class EntryEditorController {

	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField minutesTextField;
	@FXML
	private ComboBox<String> categoryTextField;
	@FXML
	private TextField titleTextField;
	@FXML
	private TextField descriptionTextField;
	@FXML
	private Button submitButton;

	@FXML
	public void initialize() {
		// Populate with the values of a given entry
		datePicker.setValue(LocalDate.parse("2019-01-01"));
		minutesTextField.setText("60");
		titleTextField.setText("Entry Title");
		descriptionTextField.setText("This is a description.");
//		datePicker.setValue(entry.getDate());
//		minutesTextField.setText(entry.getMinutes().toString());
//		categoryTextField.setText(entry.getCategory());
//		titleTextField.setText(entry.getTitle());
//		descriptionTextField.setText(entry.getDescription());
	}

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		Window owner = submitButton.getScene().getWindow();

		// TODO: Edit error messages

		// Check valid responses
		if (minutesTextField.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your email id");
			return;
		}
		if (categoryTextField.getSelectionModel().getSelectedItem().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter a password");
			return;
		}
		if (titleTextField.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter a password");
			return;
		}
		if (descriptionTextField.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter a password");
			return;
		}

		// Process fields to make a new entry
		LocalDate date = datePicker.getValue();
		Integer minutes = Integer.parseInt(minutesTextField.getText());
		String category = categoryTextField.getSelectionModel().getSelectedItem();
		String title = titleTextField.getText();
		String description = descriptionTextField.getText();
		Entry newEntry = new Entry(date, minutes, category, title, description);

		AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Done!", newEntry.toString());

	}

}
