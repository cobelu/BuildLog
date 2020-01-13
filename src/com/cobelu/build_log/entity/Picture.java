package com.cobelu.build_log.entity;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
	 * Fields
	 */

	private Long entryId;
	private Image image;
	private String description;

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
		System.out.println(bytes);
		this.image = byteArrayToImage(bytes);
		System.out.println(image.toString());
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
		System.out.println("data: " + data.toString());
		return data;
	}

	/*
	 * Helpers
	 */

	private Image byteArrayToImage(byte[] bytes) {
		Image image = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
			System.out.println(bufferedImage.toString());
			image = SwingFXUtils.toFXImage(bufferedImage, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		return String.format("Picture: {entrydD: %d, description: %s}", entryId, description);
	}

}
