package com.cobelu.build_log.model;

import java.util.List;

import com.cobelu.build_log.dao_interface.PictureDaoI;
import com.cobelu.build_log.dao_jdbc.PictureDaoJdbc;
import com.cobelu.build_log.entity.Entry;
import com.cobelu.build_log.entity.Picture;

import javafx.scene.image.Image;

public class PictureModel {

	/*
	 * Fields
	 */

	private PictureDaoI pictureDao;
	private Picture selectedPicture;
	private Image selectedImage;

	/*
	 * Constructor
	 */

	public PictureModel() {
		pictureDao = new PictureDaoJdbc();
	}

	public List<Picture> findAllByEntry(Entry entry) {
		return pictureDao.findAllByEntry(entry);
	}

	public Picture find(Picture picture) {
		return pictureDao.find(picture);
	}

	public void insert(Picture picture) {
		pictureDao.insert(picture);
	}

	public void update(Picture picture) {
		pictureDao.update(picture);
	}

	public void delete(Picture picture) {
		pictureDao.delete(picture);
	}

	/*
	 * Getters and Setters
	 */

	public Picture getSelectedPicture() {
		return selectedPicture;
	}

	public Image getSelectedImage() {
		return selectedImage;
	}

	public void setSelectedPicture(Picture selectedPicture) {
		this.selectedPicture = selectedPicture;
	}

	public void setSelectedImage(Image selectedImage) {
		this.selectedImage = selectedImage;
	}

}
