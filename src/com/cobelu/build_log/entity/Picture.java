package com.cobelu.build_log.entity;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 * Represents a JPG picture to be parsed from a blob of bytes in the database.
 * 
 * @author cobelu
 */
public class Picture extends BaseEntity {

	/*
	 * Constants
	 */
	public final int WIDTH = 128;
	public final int HEIGHT = 128;

	/*
	 * Fields
	 */

	private Long entryId;
	private String description;
	private Image image;

	/*
	 * Constructors
	 */

	public Picture() {
		super();
	}

	public Picture(Long entryId, String description, Image image) {
		this();
		this.entryId = entryId;
		this.description = description;
		this.image = image;
	}

	public Picture(Long entryId, String description, byte[] bytes) {
		this();
		this.entryId = entryId;
		this.description = description;
		this.image = bytesToImage(bytes);
	}

	/*
	 * Methods
	 */

	public byte[] getImageAsBytes() {
		byte[] data = null;
		try {
			BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", bos);
			data = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("data: " + data.toString()); // TODO: Remove
		return data;
	}

	private Image bytesToImage(byte[] bytes) {
		InputStream inputStream = new ByteArrayInputStream(bytes);
		Image image = new Image(inputStream);
		return image;
	}

	/*
	 * Getters and Setters
	 */

	public Long getEntryId() {
		return entryId;
	}

	public Image getImage() {
		return image;
	}

	public String getDescription() {
		return description;
	}

	public void setEntryId(Long entryId) {
		this.entryId = entryId;
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
