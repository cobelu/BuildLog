package com.cobelu.build_log;

import com.cobelu.build_log.controller.NavigationController;
import com.cobelu.build_log.model.Model;
import com.cobelu.build_log.view.MainPane;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Launch point of the Bully application.
 * 
 * @author cobelu
 */
public class BuildLogApplication extends Application {

	Model model;
	MainPane mainPane;

	@Override
	public void start(Stage ignoredStage) {
		try {
			model = new Model();
			NavigationController stage = new NavigationController(model);
			
			stage.openMainStage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
