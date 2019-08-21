package com.cobelu.build_log.model;

import java.util.List;

import com.cobelu.build_log.dao_jdbc.OrderDaoJdbc;
import com.cobelu.build_log.entity.Order;
import com.cobelu.build_log.dao_interface.OrderDaoI;

public class OrderModel {

	private OrderDaoI orderDao;

	/*
	 * Constructor
	 */

	public OrderModel() {
		orderDao = new OrderDaoJdbc();
	}

	/*
	 * Methods
	 */

	public List<Order> findAll() {
		return orderDao.findAll();
	}

	public Order find(Order order) {
		return orderDao.find(order);
	}

	public void insert(Order order) {
		orderDao.insert(order);
	}

	public void update(Order order) {
		orderDao.update(order);
	}

	public void delete(Order order) {
		orderDao.delete(order);
	}

}
