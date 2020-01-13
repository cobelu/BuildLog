package com.cobelu.build_log.view;

import java.util.Map;

import com.cobelu.build_log.controller.NavigationController;
import com.cobelu.build_log.model.Model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class MainPane extends BorderPane {

	// Awareness of the model
	private NavigationController navCon;
	private Model model;
	private EntryPane entryPane;

	public MainPane(NavigationController navCon, Model model) {
		super();

		this.navCon = navCon;
		this.model = model;

		// Menu bar
		MenuBar menuBar = new MenuBar();

		// File menu items
		final Menu file = new Menu("File");
		// New entry
		MenuItem newEntry = new MenuItem("New Entry");
		newEntry.setOnAction(e -> {
			onNewEntryPress();
		});
		file.getItems().add(newEntry);
		MenuItem newProject = new MenuItem("New Project");
		newProject.setOnAction(e -> {
			onNewProjectPress();
		});
		file.getItems().add(newProject);
		MenuItem openProject = new MenuItem("Open Project");
		openProject.setOnAction(e -> {
			onOpenProjectPress();
		});
		file.getItems().add(openProject);
		MenuItem editProject = new MenuItem("Edit Project");
		editProject.setOnAction(e -> {
			onEditProjectPress();
		});
		file.getItems().add(editProject);
		// Report
		MenuItem report = new MenuItem("Generate Report");
		report.setOnAction(e -> {
			onReportPress();
		});
		file.getItems().add(report);
		// Quit
		MenuItem quit = new MenuItem("Quit");
		quit.setOnAction(e -> {
			onQuitPress();
		});
		file.getItems().add(quit);
		menuBar.getMenus().add(file);

		// Help menu items
		final Menu help = new Menu("Help");
		// About
		MenuItem about = new MenuItem("About");
		about.setOnAction(e -> {
			onAboutPress();
		});
		help.getItems().add(about);

		menuBar.getMenus().add(help);
		setTop(menuBar);

		// Entry Pane
		entryPane = new EntryPane(this.navCon, this.model);
		setCenter(entryPane);
	}

	private void onNewEntryPress() {
		navCon.openNewEntryStage();
	}

	private void onNewProjectPress() {
		// TODO: Do this
		System.out.println("New Project Button Pressed!");
		// Check for a location and name
		// Create the location and DB with appropriate logic
		// Ask for a save if needed
	}

	private void onReportPress() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Report");
		alert.setHeaderText(null);
		String contextText = "Number of Entries: " + model.getEntryModel().findNumberOfEntries() + "\n";
		contextText += "Total Time: " + model.getEntryModel().findTotalHours() + "\n";
		Map<String, String> categoryTimes = model.getEntryModel().findHoursByCategory();
		for (Map.Entry<String, String> mapElement : categoryTimes.entrySet()) {
			contextText += "Total Time for " + mapElement.getKey() + ": " + mapElement.getValue() + "\n";
		}
		alert.setContentText(contextText);
		alert.showAndWait(); // Popup on press
	}

	private void onQuitPress() {
		System.exit(0); // Quit on press
	}

	private void onEditProjectPress() {
		System.out.println("Edit Project Pressed");
	}

	private void onOpenProjectPress() {
		System.out.println("Open Project Pressed");
	}

	private void onAboutPress() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Bully");
		alert.setHeaderText(null);
		alert.setContentText("Bully - The Builder's Logging Tool - Connor Luckett");
		alert.showAndWait(); // Pop up on press
	}

	public Model getModel() {
		return model;
	}

	public NavigationController getNavigationController() {
		return navCon;
	}

}
