package com.cobelu.build_log.model;

import java.util.List;

import com.cobelu.build_log.dao_interface.CategoryDaoI;

public class CategoryModel {

	/*
	 * Fields
	 */

	private CategoryDaoI categoryDao;

	/*
	 * Methods
	 */

	public List<String> findAll() {
		return categoryDao.findAll();
	}

	public String find(String category) {
		return categoryDao.find(category);
	}

	public void insert(String category) {
		categoryDao.insert(category);
	}

	public void delete(String category) {
		categoryDao.delete(category);
	}

}
