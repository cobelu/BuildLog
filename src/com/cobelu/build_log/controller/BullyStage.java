package com.cobelu.build_log.controller;

import com.cobelu.build_log.model.Model;
import com.cobelu.build_log.view.EntryEditor;
import com.cobelu.build_log.view.MainPane;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class BullyStage extends Stage {

	private Model model;
	private Stage currentStage;

	public BullyStage(Model model) {
		super();
		this.model = model;
		currentStage = new Stage();
	}

	public void openMainStage() {
		currentStage.hide();
		currentStage = new Stage();
		MainPane mainPane = new MainPane(this, model);
		Scene scene = new Scene(mainPane, 400, 400);
		currentStage.setScene(scene);
		currentStage.setTitle("Bully");
		currentStage.show();
	}

	public void openEntryEditorStage() {
		currentStage.hide();
		currentStage = new Stage();
		EntryEditor entryEditor = new EntryEditor(this, model);
		Scene scene = new Scene(entryEditor, 400, 400);
		currentStage.setScene(scene);
		currentStage.setTitle("Bully");
		currentStage.show();
	}

	public Stage getCurrentStage() {
		return currentStage;
	}

}
