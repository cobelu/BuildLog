package com.cobelu.build_log;

import com.cobelu.build_log.controller.NavigationController;
import com.cobelu.build_log.model.Model;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Launch point of the Bully application.
 * 
 * @author cobelu
 */
public class BuildLogApplication extends Application {

	@Override
	public void start(Stage ignoredStage) {
		try {
			Model model = new Model();
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
