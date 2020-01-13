package com.cobelu.build_log.model;

import java.util.List;

import com.cobelu.build_log.dao_interface.ProjectDaoI;
import com.cobelu.build_log.dao_jdbc.ProjectDaoJdbc;
import com.cobelu.build_log.entity.Project;

public class ProjectModel {

	/*
	 * Fields
	 */

	private ProjectDaoI projectDao;
	private Project selectedProject;

	/*
	 * Constructor
	 */

	public ProjectModel() {
		projectDao = new ProjectDaoJdbc();
	}

	/*
	 * Methods
	 */

	public List<Project> findAll() {
		return projectDao.findAll();
	}

	public Project find(Project project) {
		return projectDao.find(project);
	}

	public void insert(Project project) {
		projectDao.insert(project);
	}

	public void delete(Project project) {
		projectDao.delete(project);
	}

	/*
	 * Getters and Setters
	 */

	public ProjectDaoI getProjectDao() {
		return projectDao;
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

}
