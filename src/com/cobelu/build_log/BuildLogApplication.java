package com.cobelu.build_log;

import com.cobelu.build_log.model.Model;
import com.cobelu.build_log.view.MainPane;

import javafx.application.Application;
import javafx.scene.Scene;
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
	public void start(Stage primaryStage) {
		try {
			model = new Model();
			mainPane = new MainPane(primaryStage, model);
			Scene scene = new Scene(mainPane, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Bully");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
