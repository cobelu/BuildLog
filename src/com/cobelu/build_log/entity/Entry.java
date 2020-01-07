package com.cobelu.build_log.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represents a build log Entry.
 * 
 * @author cobelu
 */
@Entity
@Table(name = "entry")
public class Entry extends BaseEntity {

	/*
	 * Fields
	 */

	private LocalDate date;
	private Integer minutes;
	private String category;
	private String title;
	private String description;
	private List<Picture> pictures;

	/*
	 * Constructors
	 */

	public Entry() {
		super();
	}

	public Entry(LocalDate date, Integer minutes, String category, String title, String description) {
		this();
		this.date = date;
		this.minutes = minutes;
		this.category = category;
		this.title = title;
		this.description = description;
	}

	/*
	 * Getters
	 */

	public LocalDate getDate() {
		return date;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public String getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	/*
	 * Setters
	 */

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * Others
	 */

	@Override
	public String toString() {
		String rtnString = "";
		rtnString += date.toString();
		// rtnString += " ";
		// rtnString += startTime.toString();
		// rtnString += " to ";
		// rtnString += endDate.toString();
		// rtnString += " ";
		// rtnString += startTime.toString();
		return rtnString;
	}

}
