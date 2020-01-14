package com.cobelu.build_log.view;

import com.cobelu.build_log.controller.NavigationController;
import com.cobelu.build_log.entity.Project;
import com.cobelu.build_log.model.Model;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ProjectEditor extends GridPane {

	/*
	 * Fields
	 */

	private NavigationController navCon;
	private Model model;
	private Project project;
	private TextField nameTextField;
	private ListView<String> categoryListView;
	private Button addCategoryButton;
	private Button deleteCategoryButton;
	private Button saveButton;
	private Button cancelButton;

	/*
	 * Constructors
	 */

	public ProjectEditor(NavigationController stage, Model model) {
		super();
		this.navCon = stage;
		this.model = model;

		// Geometry
		setAlignment(Pos.CENTER);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(25, 25, 25, 25));

		// Name
		Label nameLabel = new Label("Project Name:");
		add(nameLabel, 0, 0);
		nameTextField = new TextField();
		add(nameTextField, 1, 0);

		// Category
		Label categoryLabel = new Label("Categories:");
		add(categoryLabel, 0, 1);
		addCategoryButton = new Button("Add");
		addCategoryButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				onAddCategoryButtonPress();
			}
		});
		add(addCategoryButton, 0, 2);
		deleteCategoryButton = new Button("Remove");
		deleteCategoryButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				onDeleteCategoryButtonPress();
			}
		});
		add(deleteCategoryButton, 0, 3);

		// Category
		ObservableList<String> categoriesList = model.getEntryModel().getCategoriesList();
		categoryListView = new ListView<String>(categoriesList);
		add(categoryListView, 1, 1, 1, 3);

		// Submit button
		saveButton = new Button("Save");
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				onSaveButtonPress();
			}
		});
		add(saveButton, 0, 4);

		// Cancel button
		cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				onCancelButtonPress();
			}
		});
		add(cancelButton, 1, 4);
	}

	public ProjectEditor(NavigationController stage, Model model, Project project) {
		this(stage, model);
		this.project = project;
		// TODO: Fill out with details
	}

	/*
	 * Helpers
	 */

	private void onSaveButtonPress() {
		// TODO: DO
		navCon.openMainStage();
	}

	private void onCancelButtonPress() {
		navCon.openMainStage();
	}

	private void onAddCategoryButtonPress() {
		// TODO: DO
	}

	private void onDeleteCategoryButtonPress() {
		// TODO: DO
	}

	/*
	 * Getters and Setters
	 */

	public NavigationController getNavCon() {
		return navCon;
	}

	public Model getModel() {
		return model;
	}

	public Project getProject() {
		return project;
	}

}
