package com.cobelu.build_log.view;

import com.cobelu.build_log.controller.NavigationController;
import com.cobelu.build_log.entity.Project;
import com.cobelu.build_log.model.Model;

import javafx.scene.layout.GridPane;

public class ProjectEditor extends GridPane {

	/*
	 * Fields
	 */

	private NavigationController navCon;
	private Model model;
	private Project project;

	/*
	 * Constructors
	 */

	public ProjectEditor(NavigationController stage, Model model) {
		super();
		this.navCon = stage;
		this.model = model;
	}

	public ProjectEditor(NavigationController stage, Model model, Project project) {
		this(stage, model);
		this.project = project;
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
