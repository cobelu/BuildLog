package com.cobelu.build_log.view;

import com.cobelu.build_log.controller.NavigationController;
import com.cobelu.build_log.entity.Picture;
import com.cobelu.build_log.model.Model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PictureEditor extends GridPane {

	private NavigationController navCon;
	private Picture picture;
	private Model model;

	public PictureEditor(NavigationController stage, Model model) {
		super();
		this.navCon = stage;
		this.model = model;

		// Geometry
		setAlignment(Pos.CENTER);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(25, 25, 25, 25));

		// Image Preview
		Label imageLabel = new Label("Image: ");
		add(imageLabel, 0, 1);
		// TODO: Implement image
		// Image image = SwingFXUtils.toFXImage(capture, null);
		// add(image, 1, 1);

		// Description
		Label descriptionLabel = new Label("Description: ");
		add(descriptionLabel, 0, 1);
		TextField descriptionTextField = new TextField();
		add(descriptionTextField, 1, 2);

	}

	public PictureEditor(NavigationController stage, Model model, Picture picture) {
		this(stage, model);
		this.picture = picture;

		// TODO: Fill in data from given picture
	}

}
