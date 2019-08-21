package com.cobelu.build_log.entity;

import java.io.File;

/**
 * Represents a JPG picture to be parsed from a blob of bytes in the database.
 * 
 * @author cobelu
 */
public class Picture extends BaseEntity {

	/*
	 * Fields
	 */

	private Long entryId;
	private File file;
	private String description;

	/*
	 * Constructors
	 */

	public Picture() {
		super();
	}

	public Picture(Long entryId, File file, String description) {
		this();
		this.entryId = entryId;
		this.file = file;
		this.description = description;
	}

	/*
	 * Getters and Setters
	 */
	
	public Long getEntryId() {
		return entryId;
	}

	public File getFile() {
		return file;
	}

	public String getDescription() {
		return description;
	}
	
	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/*
	 * To String
	 */
	@Override
	public String toString() {
		return file.toString();
	}

}
