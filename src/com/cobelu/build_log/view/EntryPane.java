package com.cobelu.build_log.view;

import com.cobelu.build_log.entity.Entry;
import com.cobelu.build_log.model.EntryModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EntryPane extends BorderPane {

	private Stage stage;
	private EntryModel entryModel;
	private TableView<Entry> tableView;

	@SuppressWarnings("unchecked")
	public EntryPane(Stage stage, EntryModel entryModel) {
		super();

		this.stage = stage;
		this.entryModel = entryModel;

		// Content inside the tab
		BorderPane borderPane = new BorderPane();
		tableView = new TableView<Entry>();
		TableColumn<Entry, String> dateCol = new TableColumn<Entry, String>("Date");
		dateCol.setCellValueFactory(new PropertyValueFactory<Entry, String>("date"));
		TableColumn<Entry, String> minutesCol = new TableColumn<Entry, String>("Minutes");
		minutesCol.setCellValueFactory(new PropertyValueFactory<Entry, String>("minutes"));
		TableColumn<Entry, String> categoryCol = new TableColumn<Entry, String>("Category");
		categoryCol.setCellValueFactory(new PropertyValueFactory<Entry, String>("category"));
		TableColumn<Entry, String> titleCol = new TableColumn<Entry, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<Entry, String>("title"));
		TableColumn<Entry, String> descCol = new TableColumn<Entry, String>("Description");
		descCol.setCellValueFactory(new PropertyValueFactory<Entry, String>("description"));
		tableView.getColumns().addAll(dateCol, minutesCol, categoryCol, titleCol, descCol);

		// Add table view to pane
		borderPane.setCenter(tableView);

		// Populate with data
		ObservableList<Entry> entries = FXCollections.observableList(entryModel.findAll());
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
					// TODO: Open a new entry window
					Entry selectedEntry = tableView.getSelectionModel().getSelectedItem();
					displayEntryEditor(selectedEntry, entryModel);
					System.out.println("You selected: " + selectedEntry.toString());
				}
			}
		});

		setCenter(borderPane);
	}

	private void displayEntryEditor(Entry entry, EntryModel entryModel) {
		EntryEditor entryEditor = new EntryEditor(stage, entryModel, entry);
		Stage stage = new Stage();
		stage.setTitle("Edit an Entry");
		stage.setScene(new Scene(entryEditor, 450, 450));
		stage.show();
	}

	public EntryModel getEntryModel() {
		return entryModel;
	}

	public TableView<Entry> getTableView() {
		return tableView;
	}

}
