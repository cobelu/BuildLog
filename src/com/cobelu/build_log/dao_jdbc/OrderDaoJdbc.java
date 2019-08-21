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
		List<Order> orders = null;
		String query = "SELECT * FROM " + orderTable + ";";
		ResultSet rs;
		try {
			rs = openAndQuery(query);
			orders = parseOrdersFrom(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAfterQuery();
		}
		return orders;
	}

	public Order find(Order order) {
		String query = "";
		query += "SELECT * FROM ";
		query += orderTable;
		query += " WHERE ";
		query += idCol;
		query += "=";
		query += order.getId();
		query += ";";
		List<Order> orders = null;
		Order rtnOrder = null;
		ResultSet rs;
		try {
			rs = openAndQuery(query);
			orders = parseOrdersFrom(rs);
			order = orders.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} finally {
			closeAfterQuery();
		}
		return rtnOrder;
	}

	public void insert(Order order) {
		// TODO Auto-generated method stub

	}

	public void update(Order order) {
		// TODO Auto-generated method stub

	}

	public void delete(Order order) {
		String update = "DELETE FROM " + orderTable + " ";
		update += "WHERE " + idCol + "=" + order.getId() + ";";
		openAndUpdate(update);
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
