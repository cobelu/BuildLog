package com.cobelu.build_log.view;

import com.cobelu.build_log.entity.Entry;
import com.cobelu.build_log.model.EntryModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

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
		TableColumn<Entry, String> startDateCol = new TableColumn<Entry, String>("Start Date");
		startDateCol.setCellValueFactory(new PropertyValueFactory<Entry, String>("startDate"));
		TableColumn<Entry, String> startTimeCol = new TableColumn<Entry, String>("End Date");
		startTimeCol.setCellValueFactory(new PropertyValueFactory<Entry, String>("endDate"));
		TableColumn<Entry, String> endDateCol = new TableColumn<Entry, String>("Start Time");
		endDateCol.setCellValueFactory(new PropertyValueFactory<Entry, String>("startTime"));
		TableColumn<Entry, String> endTimeCol = new TableColumn<Entry, String>("End Time");
		endTimeCol.setCellValueFactory(new PropertyValueFactory<Entry, String>("endTime"));
		TableColumn<Entry, String> chapterCol = new TableColumn<Entry, String>("Chapter");
		chapterCol.setCellValueFactory(new PropertyValueFactory<Entry, String>("chapter"));
		TableColumn<Entry, String> descCol = new TableColumn<Entry, String>("Description");
		descCol.setCellValueFactory(new PropertyValueFactory<Entry, String>("description"));
		tableView.getColumns().addAll(startDateCol, startTimeCol, endDateCol, endTimeCol, chapterCol, descCol);

		// Add table view to pane
		borderPane.setCenter(tableView);

		// Populate with data
		ObservableList<Entry> entries = FXCollections.observableList(entryModel.findAll());
		tableView.setItems(entries);

		setCenter(borderPane);
	}

	public EntryModel getEntryModel() {
		return entryModel;
	}

	public TableView<Entry> getTableView() {
		return tableView;
	}

}
