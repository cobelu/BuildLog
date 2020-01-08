package com.cobelu.build_log.entity;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

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

	public Picture(Long entryId, Blob blob, String description) {
		this();
		this.entryId = entryId;
		this.image = blobToImage(blob);
		this.description = description;
	}

	/*
	 * Methods
	 */

	private Blob bufferedImageToBlob(BufferedImage image) {
		Blob blob = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", baos);
			byte[] imageByteArray = baos.toByteArray();
			blob = new SerialBlob(imageByteArray);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return blob;
	}

	private BufferedImage blobToImage(Blob blob) {
		InputStream in;
		BufferedImage image = null;
		try {
			in = blob.getBinaryStream();
			image = ImageIO.read(in);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	private byte[] bufferedImageAsByteArray(BufferedImage image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] imageInByte = null;
		try {
			ImageIO.write(image, "jpg", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return imageInByte;
	}

	private BufferedImage byteArrayAsBufferedImage(byte[] byteArray) {
		ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
		try {
			BufferedImage bufferedImage = ImageIO.read(bis);
			ImageIO.write(bufferedImage, "jpg", new File("output.jpg"));
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

	public Blob getImageAsBlob() {
		return bufferedImageToBlob(image);
	}

	public byte[] getImageAsByteArray() {
		return bufferedImageAsByteArray(image);
	}

	/*
	 * To String
	 */

	@Override
	public String toString() {
		return String.format("Picture: {entrydD: %d, description: %s}", entryId, description);
	}

}
