package com.cobelu.build_log.view;

import java.io.IOException;

import com.cobelu.build_log.entity.Entry;
import com.cobelu.build_log.model.EntryModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EntryPane extends BorderPane {

	private EntryModel entryModel;
	private TableView<Entry> tableView;

	@SuppressWarnings("unchecked")
	public EntryPane(EntryModel entryModel) {
		super();

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
					displayEntryEditor(selectedEntry);
					System.out.println("You selected: " + selectedEntry.toString());
				}
			}
		});

		setCenter(borderPane);
	}

	private void displayEntryEditor(Entry entry) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/resources/fxml/entry_editor.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Registration Form FXML Application");
			stage.setScene(new Scene(root, 800, 500));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public EntryModel getEntryModel() {
		return entryModel;
	}

	public TableView<Entry> getTableView() {
		return tableView;
	}

}
