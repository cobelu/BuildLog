package com.cobelu.build_log.view;

import com.cobelu.build_log.model.Model;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPane extends BorderPane {

	// Awareness of the model
	private Stage stage;
	private Model model;
	private EntryPane entryPane;

	public MainPane(Stage primaryStage, Model model) {
		super();

		this.stage = primaryStage;
		this.model = model;

		// Menu bar
		MenuBar menuBar = new MenuBar();

		// File menu items
		final Menu file = new Menu("File");
		// New entry
		MenuItem newEntry = new MenuItem("New Entry");
		newEntry.setOnAction(e -> {
			displayNewEntryWindow(); // Create a new entry on press
		});
		// Quit
		MenuItem quit = new MenuItem("Quit");
		quit.setOnAction(e -> {
			System.exit(0); // Quit on press
		});
		file.getItems().addAll(newEntry, quit);

		// Help menu items
		final Menu help = new Menu("Help");
		// About
		MenuItem about = new MenuItem("About");
		about.setOnAction(e -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Bully");
			alert.setHeaderText(null);
			alert.setContentText("Bully - The Builder's Logging Tool - Connor Luckett");
			alert.showAndWait(); // Popup on press
		});
		help.getItems().addAll(about);

		menuBar.getMenus().addAll(file, help);
		setTop(menuBar);

		// Entry Pane
		entryPane = new EntryPane(model.getEntryModel());
		setCenter(entryPane);

	}

	private void displayNewEntryWindow() {
		EntryEditor entryEditor = new EntryEditor();
		Stage stage = new Stage();
		stage.setTitle("Create an Entry");
		stage.setScene(new Scene(entryEditor, 450, 450));
		stage.show();
	}

	public Model getModel() {
		return model;
	}

	public Stage getStage() {
		return stage;
	}

}
