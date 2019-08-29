package com.cobelu.build_log.view;

import com.cobelu.build_log.model.Model;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPane extends BorderPane {
	
	// Awareness of the model
	private Model model;
	private Stage stage;
	private TabPane tabPane;
	private Tab entryTab;
	private Tab orderTab;
	
	public MainPane(Stage primaryStage, Model model) {
		super();
		this.stage = primaryStage;
		this.model = model;
	}

}
