package com.cobelu.build_log.entity;

import java.awt.image.BufferedImage;

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
	private BufferedImage image;
	private String description;

	/*
	 * Constructors
	 */

	public Picture() {
		super();
	}

	public Picture(Long entryId, BufferedImage image, String description) {
		this();
		this.entryId = entryId;
		this.image = image;
		this.description = description;
	}

	/*
	 * Getters and Setters
	 */

	public Long getEntryId() {
		return entryId;
	}

	public BufferedImage getImage() {
		return image;
	}

	public String getDescription() {
		return description;
	}

	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * To String
	 */
	@Override
	public String toString() {
		return description;
	}

}
