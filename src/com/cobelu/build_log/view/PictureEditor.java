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
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class PictureEditor extends GridPane {

	/*
	 * Fields
	 */

	public final String PLACEHOLDER = "resources/placeholder.jpg";

	private NavigationController navCon;
	private Picture picture;
	private Model model;
	private FileChooser fileChooser;
	private Button uploadButton;
	private ImageView imageView;
	private TextField descriptionTextField;
	private Button okButton;
	private Button cancelButton;

	/*
	 * Constructors
	 */

	public PictureEditor(NavigationController navCon, Model model) {
		super();
		this.navCon = navCon;
		this.model = model;

		// Geometry
		setAlignment(Pos.CENTER);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(25, 25, 25, 25));

		// Image Preview
		Label imageLabel = new Label("Image: ");
		add(imageLabel, 1, 1);

		// Upload Button
		uploadButton = new Button("Upload");
		uploadButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				onUploadButtonPress();
			}
		});
		add(uploadButton, 1, 2);

		// Image View
		// TODO: Implement image
		Image image = new Image(PLACEHOLDER);
		imageView = new ImageView(image);
		add(imageView, 0, 0, 1, 6);

		// Description
		Label descriptionLabel = new Label("Description: ");
		add(descriptionLabel, 1, 4);
		descriptionTextField = new TextField();
		add(descriptionTextField, 1, 5);

		// OK Button
		okButton = new Button("OK");
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				onOkButtonPress();
			}
		});
		add(okButton, 1, 6);

		// Cancel Button
		cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				onCancelButtonPress();
			}
		});
		add(cancelButton, 2, 6);
	}

	public PictureEditor(NavigationController stage, Model model, Picture picture) {
		this(stage, model);
		this.picture = picture;

		// Load picture's image into view
		imageView = new ImageView(picture.getImage());
		// Update text field with given picture's description
		descriptionTextField.setText(picture.getDescription());
	}

	/*
	 * Helpers
	 */

	private void onUploadButtonPress() {
		System.out.println("Upload"); // TODO: Remove
		Stage stage = navCon.getCurrentStage();
		fileChooser = new FileChooser();
		fileChooser.setTitle("Add a Picture");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.jpg", ".jpeg"));
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			/*
			 * NOTE: The file is restricted to image files by the file chooser extension
			 * filters.
			 */
			BufferedImage bufferedImage = null;
			try {
				bufferedImage = ImageIO.read(selectedFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			model.getPictureModel().setSelectedImage(image);
			// TODO: Crop image to fit inside view
			imageView.setImage(model.getPictureModel().getSelectedImage());
		}
	}

	private void onOkButtonPress() {
		// TODO: Fix
		// The associated entry is the current entry we are editing
		Long entryId = model.getEntryModel().getSelectedEntry().getId();
		// Get the image from the view
		Image image = model.getPictureModel().getSelectedImage();
		// Get the description from the text field
		String description = descriptionTextField.getText();
		// Create a picture
		Picture picture = new Picture(entryId, description, image);
		picture.setId(model.getPictureModel().getSelectedPicture().getId());
		// Add new picture to the list of pictures
		if (this.picture == null) {
			model.getPictureModel().insert(picture);
		} else {
			model.getPictureModel().update(picture);
		}
		navCon.openEntryEditorStage(model.getEntryModel().getSelectedEntry());
	}

	private void onCancelButtonPress() {
		// Go back to the last menu
		navCon.openEntryEditorStage(model.getEntryModel().getSelectedEntry());
	}

	/*
	 * Getters and Setters
	 */

	public Picture getPicture() {
		return picture;
	}

}
