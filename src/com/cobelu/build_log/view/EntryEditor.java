package com.cobelu.build_log.view;

import java.time.LocalDate;

import com.cobelu.build_log.controller.NavigationController;
import com.cobelu.build_log.entity.Entry;
import com.cobelu.build_log.entity.Picture;
import com.cobelu.build_log.model.Model;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class EntryEditor extends GridPane {

	/*
	 * Fields
	 */

	private NavigationController navCon;
	private Entry entry;
	private Model model;
	private DatePicker datePicker;
	private TextField minutesTextField;
	private ComboBox<String> categoryComboBox;
	private TextField titleTextField;
	private TextArea descriptionTextArea;
	private ObservableList<Picture> pictures;
	private ListView<Picture> pictureList;
	private Button addButton;
	private Button removeButton;
	private Button saveButton;
	private Button cancelButton;

	/*
	 * Constructors
	 */

	public EntryEditor(NavigationController stage, Model model) {
		super();
		this.navCon = stage;
		this.model = model;

		// Geometry
		setAlignment(Pos.CENTER);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(25, 25, 25, 25));

		// Date
		Label dateLabel = new Label("Date:");
		add(dateLabel, 0, 1);
		datePicker = new DatePicker(LocalDate.now());
		add(datePicker, 1, 1);

		// Minutes
		Label minutesLabel = new Label("Minutes:");
		add(minutesLabel, 0, 2);
		minutesTextField = new TextField();
		add(minutesTextField, 1, 2);

		// Category
		Label categoryLabel = new Label("Category:");
		add(categoryLabel, 0, 3);
		ObservableList<String> categoriesList = model.getEntryModel().getCategoriesList();
		categoryComboBox = new ComboBox<String>(categoriesList);
		add(categoryComboBox, 1, 3);

		// Title
		Label titleLabel = new Label("Title:");
		add(titleLabel, 0, 4);
		titleTextField = new TextField();
		add(titleTextField, 1, 4);

		// Description
		Label descriptionLabel = new Label("Description:");
		add(descriptionLabel, 0, 5);
		descriptionTextArea = new TextArea();
		add(descriptionTextArea, 1, 5);

		// Image selection
		Label imagesLabel = new Label("Images:");
		add(imagesLabel, 0, 6);
		addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				onAddButtonPress();
			}
		});
		add(addButton, 0, 7);
		// Remove button
		removeButton = new Button("Remove");
		removeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				onRemoveButtonPress();
			}
		});
		add(removeButton, 0, 8);

		// List of images
		pictureList = new ListView<Picture>();
		pictureList.setItems(pictures);
		pictureList.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					onPictureListDoublePress();
				}
			}
		});
		add(pictureList, 1, 6, 1, 3);

		// Submit button
		saveButton = new Button("Save");
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				onSaveButtonPress();
			}
		});
		add(saveButton, 0, 9);

		// Cancel button
		cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				onCancelButtonPress();
			}
		});
		add(cancelButton, 1, 9);
	}

	public EntryEditor(NavigationController stage, Model model, Entry entry) {
		this(stage, model);
		this.entry = entry;

		// Populate with the values of a given entry
		datePicker.setValue(entry.getDate());
		minutesTextField.setText(entry.getMinutes().toString());
		categoryComboBox.getSelectionModel().select(entry.getCategory());
		titleTextField.setText(entry.getTitle());
		descriptionTextArea.setText(entry.getDescription());
	}

	/*
	 * Methods
	 */

	private void onAddButtonPress() {
		navCon.openNewPictureStage();

		// TODO: Insert Picture
		// model.getPictureModel().insert(picture);
	}

	private void onRemoveButtonPress() {
		Picture selectedPicture = pictureList.getSelectionModel().getSelectedItem();
		// TODO: Ask if sure
		if (selectedPicture != null) {
			pictures.remove(selectedPicture);
		}
	}

	private void onPictureListDoublePress() {
		// TODO: Write this method
		// Get selected picture from the list
		// Picture picture = pictureList.getSelectionModel().getSelectedItem();
		Picture picture = new Picture(); // TODO: Remove
		// Open a picture editor for the selected picture
		navCon.openPictureEditorStage(picture);
	}

	private void onSaveButtonPress() {
		// Harvest the data from the fields
		Entry newEntry = harvestFields(); // TODO: Add error handling
		// Check if it's a new Entry or if it is an update
		if (entry == null) {
			// New so insert
			model.getEntryModel().insert(newEntry);
		} else {
			// Update so update
			Long id = entry.getId();
			newEntry.setId(id);
			model.getEntryModel().update(newEntry);
		}
		// Hide the scene and go back to main stage
		navCon.openMainStage();
	}

	private void onCancelButtonPress() {
		// Hide the scene and go back to main stage
		navCon.openMainStage();
	}

	private Entry harvestFields() {
		/*
		 * We MUST create a new entry with the same ID as the old one. Solely editing
		 * the entry will not work because of the case of adding a new entry.
		 */
		// Harvest fields
		LocalDate date = datePicker.getValue();
		Integer minutes = Integer.parseInt(minutesTextField.getText());
		String category = categoryComboBox.getSelectionModel().getSelectedItem();
		String title = titleTextField.getText();
		String description = descriptionTextArea.getText();
		Entry entry = new Entry(date, minutes, category, title, description);
		return entry;
	}

	/*
	 * Getters and Setters
	 */

	public NavigationController getStage() {
		return navCon;
	}

	public Model getModel() {
		return model;
	}

	public Entry getEntry() {
		return entry;
	}

}
