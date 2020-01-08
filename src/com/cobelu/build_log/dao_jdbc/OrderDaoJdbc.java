package com.cobelu.build_log.dao_jdbc;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.cobelu.build_log.dao_interface.OrderDaoI;
import com.cobelu.build_log.entity.Order;

public class OrderDaoJdbc extends BaseDaoJdbc implements OrderDaoI {

	private final String orderTable = "ORDERS";
	private final String idCol = "ID";
	private final String dateCol = "ORDERDATE";
	private final String supplierCol = "SUPPLIER";
	private final String costCol = "COST";

	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Order find(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(Order order) {
		// TODO Auto-generated method stub
	}

	public void update(Order order) {
		// TODO Auto-generated method stub
	}

	public void delete(Order order) {
		// TODO Auto-generated method stub
	}

	/*
	 * Helper Methods
	 */

	/**
	 * Parses orders obtained from a ResultSet.
	 * 
	 * @param rset A ResultSet full of entries
	 * @return A List of Entry objects
	 * @throws SQLException
	 */
	private List<Order> parseOrdersFrom(ResultSet rs) throws SQLException {
		List<Order> orders = new LinkedList<Order>();
		while (rs.next()) {
			Order order = new Order();
			order.setId(rs.getLong(idCol));
			order.setDate(new Date(rs.getLong(dateCol)));
			order.setSupplier(rs.getString(supplierCol));
			order.setCost(rs.getFloat(costCol));
			orders.add(order);
		}
		return orders;
	}

}
