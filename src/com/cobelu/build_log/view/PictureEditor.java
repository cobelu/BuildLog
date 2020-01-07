package com.cobelu.build_log.view;

import com.cobelu.build_log.controller.NavigationController;
import com.cobelu.build_log.entity.Picture;
import com.cobelu.build_log.model.Model;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PictureEditor extends GridPane {

	public final String PLACEHOLDER = "resources/placeholder.jpg";

	private NavigationController navCon;
	private Picture picture;
	private Model model;
	private ImageView imageView;
	private TextField descriptionTextField;

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
		// TODO: Add Upload button
		// TODO: Implement image
		Image image = new Image(PLACEHOLDER);
		imageView = new ImageView(image);

		// add(image, 1, 1);

		// Description
		Label descriptionLabel = new Label("Description: ");
		add(descriptionLabel, 0, 1);
		descriptionTextField = new TextField();
		add(descriptionTextField, 1, 2);

	}

	public PictureEditor(NavigationController stage, Model model, Picture picture) {
		this(stage, model);
		this.picture = picture;

		// Load picture's image into view
		Image image = SwingFXUtils.toFXImage(picture.getImage(), null);
		imageView = new ImageView(image);
		// Update text field with given picture's description
		descriptionTextField.setText(picture.getDescription());
	}

}
