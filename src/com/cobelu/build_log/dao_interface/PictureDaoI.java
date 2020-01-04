package com.cobelu.build_log.dao_interface;

import java.util.List;

import com.cobelu.build_log.entity.Picture;

public interface PictureDaoI {

	public List<Picture> findAll();

	public Picture find(Picture picture);
	
	public void insert(Picture picture);
	
	public void update(Picture picture);

	public void delete(Picture picture);

}
