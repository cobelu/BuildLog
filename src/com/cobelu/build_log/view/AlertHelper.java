package com.cobelu.build_log.view;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

public class AlertHelper {

	public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}

	public static boolean askIfSure(String wantTo) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		showAlert(Alert.AlertType.CONFIRMATION, null, "Are you sure?", wantTo);
		alert.setTitle("Confirm");
		alert.setHeaderText("Are you sure?");
		alert.setContentText(wantTo);
		alert.initOwner(null);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true; // ... user chose OK
		} else {
			return false; // ... user chose CANCEL or closed the dialog
		}
	}

}
