package com.cobelu.build_log.view;

import com.cobelu.build_log.controller.NavigationController;
import com.cobelu.build_log.model.Model;

import javafx.scene.layout.GridPane;

public class ProjectOpener extends GridPane {

	/*
	 * Fields
	 */

	private NavigationController navCon;
	private Model model;

	/*
	 * Constructor
	 */

	public ProjectOpener(NavigationController navCon, Model model) {
		super();
		this.navCon = navCon;
		this.model = model;
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

}
