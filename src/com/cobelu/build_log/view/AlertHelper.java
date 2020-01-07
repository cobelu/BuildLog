package com.cobelu.build_log.view;

import javafx.scene.control.Alert;
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

	public static void askIfSure(String wantTo) {
		showAlert(Alert.AlertType.CONFIRMATION, null, "Are you sure?", wantTo);
	}

}
