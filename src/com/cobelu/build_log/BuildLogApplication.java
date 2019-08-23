package com.cobelu.build_log;

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

	@Override
	public void start(Stage primaryStage) {
		try {
			MainPane mainPane = new MainPane(primaryStage);
			Scene scene = new Scene(mainPane, 400, 400);
//			scene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
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
