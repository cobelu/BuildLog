package com.cobelu.build_log.model;

/**
 * The head Model of the BullyApplication. Holds onto daos, and all information
 * pertaining to the app.
 * 
 * @author cobelu
 */
public class Model {

	/*
	 * Fields
	 */

	private EntryModel entryModel;
	private OrderModel orderModel;

	/*
	 * Constructor
	 */

	public Model() {
		entryModel = new EntryModel();
		orderModel = new OrderModel();
	}

	/*
	 * Getters and Setters
	 */

	public EntryModel getEntryModel() {
		return entryModel;
	}

	public OrderModel getOrderModel() {
		return orderModel;
	}

}
