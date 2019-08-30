package com.cobelu.build_log.view;

import com.cobelu.build_log.model.Model;

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
