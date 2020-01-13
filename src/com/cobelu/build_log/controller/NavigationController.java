package com.cobelu.build_log.controller;

import com.cobelu.build_log.entity.Entry;
import com.cobelu.build_log.entity.Picture;
import com.cobelu.build_log.model.Model;
import com.cobelu.build_log.view.EntryEditor;
import com.cobelu.build_log.view.MainPane;
import com.cobelu.build_log.view.PictureEditor;
import com.cobelu.build_log.view.ProjectEditor;
import com.cobelu.build_log.view.ProjectOpener;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NavigationController {

	/*
	 * Fields
	 */

	private Model model;
	private Stage currentStage;

	/*
	 * Constructor
	 */

	public NavigationController(Model model) {
		super();
		this.model = model;
		currentStage = new Stage();
	}

	/*
	 * Methods
	 */

	public void openMainStage() {
		MainPane mainPane = new MainPane(this, model);
		stageTransition(mainPane, 800, 400, "Bully");
	}

	public void openEntryEditorStage(Entry entry) {
		EntryEditor entryEditor = new EntryEditor(this, model, entry);
		stageTransition(entryEditor, 400, 400, "Edit an Entry");
	}

	public void openNewEntryStage() {
		EntryEditor entryEditor = new EntryEditor(this, model);
		stageTransition(entryEditor, 400, 400, "Add an Entry");
	}

	public void openPictureEditorStage(Picture picture) {
		PictureEditor pictureEditor = new PictureEditor(this, model, picture);
		stageTransition(pictureEditor, 400, 400, "Edit a Picture");
	}

	public void openNewPictureStage() {
		PictureEditor pictureEditor = new PictureEditor(this, model);
		stageTransition(pictureEditor, 400, 400, "Add a Picture");
	}

	public void openProjectEditorStage() {
		ProjectEditor projectEditor = new ProjectEditor(this, model);
		stageTransition(projectEditor, 400, 400, "Edit a Project");
	}

	public void openNewProjectStage() {
		ProjectEditor projectEditor = new ProjectEditor(this, model);
		stageTransition(projectEditor, 400, 400, "Add a Project");
	}

	public void openOpenProjectStage() {
		ProjectOpener projectOpener = new ProjectOpener(this, model);
		stageTransition(projectOpener, 400, 400, "Open a Project");
	}

	/*
	 * Helpers
	 */

	private void stageTransition(Pane newPane, int width, int height, String title) {
		currentStage.hide();
		currentStage = new Stage();
		Scene scene = new Scene(newPane, width, height);
		currentStage.setScene(scene);
		currentStage.setTitle(title);
		currentStage.show();
	}

	/*
	 * Getters and Setters
	 */

	public Stage getCurrentStage() {
		return currentStage;
	}

}
