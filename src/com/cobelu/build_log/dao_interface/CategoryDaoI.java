package com.cobelu.build_log.dao_interface;

import java.util.List;

public interface CategoryDaoI {

	public List<String> findAll();

	public String find(String category);

	public void insert(String category);

	public void delete(String category);

}
