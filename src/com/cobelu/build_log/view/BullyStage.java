package com.cobelu.build_log.view;

import com.cobelu.build_log.model.Model;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class BullyStage extends Stage {

	private Model model;

	public BullyStage(Model model) {
		super();
		this.model = model;
	}

	public void openMainStage() {
		MainPane mainPane = new MainPane(this, model);
		Scene scene = new Scene(mainPane, 400, 400);
		setScene(scene);
		setTitle("Bully");
		show();
	}

	public void openEntryEditorStage() {
		EntryEditor entryEditor = new EntryEditor(this, model);
		setTitle("Create an Entry");
		setScene(new Scene(entryEditor, 450, 450));
		show();
	}

}
