package com.cobelu.build_log.dao_interface;

import java.util.List;

import com.cobelu.build_log.entity.Project;

public interface ProjectDaoI {

	public List<Project> findAll();

	public Project find(Project project);

	public void insert(Project project);

	public void delete(Project project);

}
