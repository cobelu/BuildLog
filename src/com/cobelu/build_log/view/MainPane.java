package com.cobelu.build_log.view;

import com.cobelu.build_log.model.Model;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPane extends BorderPane {

	// Awareness of the model
	private Model model;
	private Stage stage;
	private EntryPane entryPane;

	public MainPane(Stage primaryStage, Model model) {
		super();

		this.stage = primaryStage;
		this.model = model;

		// Menu bar
		MenuBar menuBar = new MenuBar();
		
		// File menu items
		final Menu file = new Menu("File");

		// Quit
		MenuItem quit = new MenuItem("Quit");
		quit.setOnAction(e -> {
		    System.exit(0); // Quit on press
		});
		
		file.getItems().addAll(quit);

		final Menu help = new Menu("Help");
		menuBar.getMenus().addAll(file, help);
		setTop(menuBar);
		
		// Entry Pane
		entryPane = new EntryPane(model.getEntryModel());
		setCenter(entryPane);

	}

	public Model getModel() {
		return model;
	}

	public Stage getStage() {
		return stage;
	}

}
