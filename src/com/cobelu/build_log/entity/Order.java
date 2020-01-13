package com.cobelu.build_log.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represents an Order entry.
 * 
 * @author cobelu
 */
@Entity
@Table(name = "order")
public class Order extends BaseEntity {

	/*
	 * Fields
	 */

	private Date date;
	private Float cost;
	private String supplier;

	/*
	 * Constructors
	 */

	public Order() {
		super();
	}

	public Order(Date date, Float cost, String supplier) {
		super();
		this.date = date;
		this.cost = cost;
		this.supplier = supplier;
	}

	/*
	 * Getters and Setters
	 */

	public Date getDate() {
		return date;
	}

	public Float getCost() {
		return cost;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

}
