package com.cobelu.build_log.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.cobelu.build_log.controller.NavigationController;
import com.cobelu.build_log.entity.Picture;
import com.cobelu.build_log.model.Model;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class PictureEditor extends GridPane {

	public final String PLACEHOLDER = "resources/placeholder.jpg";

	private NavigationController navCon;
	private Picture picture;
	private Model model;
	private Button uploadButton;
	private Button removeButton;
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
		add(imageLabel, 0, 0);

		// Upload Button
		uploadButton = new Button("Upload");
		uploadButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				onUploadButtonPress();
			}
		});
		add(uploadButton, 1, 0);

		// Remove Button
		removeButton = new Button("remove");
		removeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				onRemoveButtonPress();
			}
		});
		add(removeButton, 2, 0);

		// Image View
		// TODO: Implement image
		Image image = new Image(PLACEHOLDER);
		imageView = new ImageView(image);
		add(imageView, 0, 1);

		// Description
		Label descriptionLabel = new Label("Description: ");
		add(descriptionLabel, 2, 1);
		descriptionTextField = new TextField();
		add(descriptionTextField, 2, 2);

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

	private void onUploadButtonPress() {
		System.out.println("Upload"); // TODO: Remove
		Stage stage = navCon.getCurrentStage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Add a Picture");
		fileChooser.getExtensionFilters()
				.addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", ".jpeg"));
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			BufferedImage image = fileToBufferedImage(selectedFile);
		}
	}

	private void onRemoveButtonPress() {
		System.out.println("Remove"); // TODO: Remove

	}

	private BufferedImage fileToBufferedImage(File file) {
		// https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
		BufferedImage img = null;
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

}
