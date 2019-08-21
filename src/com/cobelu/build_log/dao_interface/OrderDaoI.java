package com.cobelu.build_log.dao_interface;

import java.util.List;

import com.cobelu.build_log.entity.Order;

public interface OrderDaoI {

	/**
	 * Fetches all Orders from the database.
	 * 
	 * @return A List of all Orders
	 */
	public List<Order> findAll();

	/**
	 * Fetches a specific Order from the database.
	 * 
	 * @return The desired Order (if exists)
	 */
	public Order find(Order order);

	/**
	 * Adds an Order to the database.
	 * 
	 * @param Order An Order to be added
	 */
	public void insert(Order order);

	/**
	 * Updates the given Order with the database's Order.
	 * 
	 * @param order The newly updated Order
	 */
	public void update(Order order);

	/**
	 * Removes an Order from the database.
	 * 
	 * @param Order The Order to be removed
	 */
	public void delete(Order order);

}
