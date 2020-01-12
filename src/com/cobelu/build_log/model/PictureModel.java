package com.cobelu.build_log.model;

import java.io.File;
import java.util.List;

import com.cobelu.build_log.dao_interface.PictureDaoI;
import com.cobelu.build_log.dao_jdbc.PictureDaoJdbc;
import com.cobelu.build_log.entity.Entry;
import com.cobelu.build_log.entity.Picture;

public class PictureModel {

	/*
	 * Fields
	 */

	private PictureDaoI pictureDao;
	private File selectedFile;

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

	public void update(Picture picture) {
		pictureDao.update(picture);
	}

	public void insert(Picture picture) {
		pictureDao.insert(picture);
	}

	/*
	 * Getters and Setters
	 */

	public File getSelectedPicture() {
		return selectedFile;
	}

	public void setSelectedFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}

}
