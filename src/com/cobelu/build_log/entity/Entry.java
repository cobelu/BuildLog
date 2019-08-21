package com.cobelu.build_log.entity;

import java.sql.Date;
import java.sql.Time;

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

	private Date startDate;
	private Date endDate;
	private Time startTime;
	private Time endTime;
	private String chapter;
	private String title;
	private String description;

	/*
	 * Constructors
	 */

	public Entry() {
		super();
	}

	public Entry(Date startDate, Date endDate, Time startTime, Time endTime, String chapter, String title, String description) {
		this();
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.chapter = chapter;
		this.title = title;
		this.description = description;
	}

	/*
	 * Getters
	 */

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}
	
	public String getChapter() {
		return chapter;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}

	/*
	 * Setters
	 */
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
	public void setChapter(String chapter) {
		this.chapter = chapter;
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
		rtnString += startDate.toString();
		// rtnString += " ";
		// rtnString += startTime.toString();
		// rtnString += " to ";
		// rtnString += endDate.toString();
		// rtnString += " ";
		// rtnString += startTime.toString();
		return rtnString;
	}

}
