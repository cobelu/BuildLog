package com.cobelu.build_log.view;

import com.cobelu.build_log.controller.NavigationController;
import com.cobelu.build_log.entity.Entry;
import com.cobelu.build_log.model.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class EntryPane extends BorderPane {

	/*
	 * Fields
	 */

	private NavigationController navCon;
	private Model model;
	private TableView<Entry> tableView;

	/*
	 * Constructor
	 */

	public EntryPane(NavigationController navCon, Model model) {
		super();

		this.navCon = navCon;
		this.model = model;

		// Content inside the tab
		BorderPane borderPane = new BorderPane();
		tableView = new TableView<Entry>();
		TableColumn<Entry, String> dateCol = new TableColumn<Entry, String>("Date");
		dateCol.setCellValueFactory(new PropertyValueFactory<Entry, String>("date"));
		tableView.getColumns().add(dateCol);
		TableColumn<Entry, String> minutesCol = new TableColumn<Entry, String>("Minutes");
		minutesCol.setCellValueFactory(new PropertyValueFactory<Entry, String>("minutes"));
		tableView.getColumns().add(minutesCol);
		TableColumn<Entry, String> categoryCol = new TableColumn<Entry, String>("Category");
		categoryCol.setCellValueFactory(new PropertyValueFactory<Entry, String>("category"));
		tableView.getColumns().add(categoryCol);
		TableColumn<Entry, String> titleCol = new TableColumn<Entry, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<Entry, String>("title"));
		tableView.getColumns().add(titleCol);
		TableColumn<Entry, String> descCol = new TableColumn<Entry, String>("Description");
		descCol.setCellValueFactory(new PropertyValueFactory<Entry, String>("description"));
		tableView.getColumns().add(descCol);

		// Add table view to pane
		borderPane.setCenter(tableView);

		// Populate with data
		ObservableList<Entry> entries = FXCollections.observableList(model.getEntryModel().findAll());
		tableView.setItems(entries);

		// Double click feature
		tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				/*
				 * Double click event
				 * 
				 * https://stackoverflow.com/a/26564412
				 */
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					onEntryDoubleClick();
				}
			}
		});

		setCenter(borderPane);
	}

	/*
	 * Helpers
	 */

	private void onEntryDoubleClick() {
		// Get selected entry that was double clicked
		Entry selectedEntry = tableView.getSelectionModel().getSelectedItem();
		// Display an editor for that entry
		displayEntryEditor(selectedEntry, model);
	}

	private void displayEntryEditor(Entry entry, Model model) {
		// Update selected entry in model
		model.getEntryModel().setSelectedEntry(entry);
		// Stage changes scene to editing scene
		navCon.openEntryEditorStage(entry);
	}

	/*
	 * Getters and Setters
	 */

	public Model getModel() {
		return model;
	}

	public TableView<Entry> getTableView() {
		return tableView;
	}

}
