package com.cobelu.build_log.model;

import com.cobelu.build_log.dao_interface.PictureDaoI;
import com.cobelu.build_log.dao_jdbc.PictureDaoJdbc;
import com.cobelu.build_log.entity.Entry;
import com.cobelu.build_log.entity.Picture;

public class PictureModel {

	/*
	 * Fields
	 */

	private Entry entry;
	private PictureDaoI pictureDao;
	private Picture selectedPicture;

	/*
	 * Constructor
	 */

	public PictureModel() {
		// TODO Auto-generated constructor stub
		pictureDao = new PictureDaoJdbc();
	}

	/*
	 * Getters and Setters
	 */

}
