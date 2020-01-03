package com.cobelu.build_log.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class EntryEditorController {

	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField minutesTextField;
	@FXML
	private TextField categoryTextField;
	@FXML
	private TextField titleTextField;
	@FXML
	private TextField descriptionTextField;
	@FXML
	private Button submitButton;
	@FXML
	private Button cancelButton;

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		Window owner = submitButton.getScene().getWindow();
		if (minutesTextField.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your email id");
			return;
		}
		if (categoryTextField.getText().isEmpty()) {
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

		AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
				"Welcome!");
	}

}
