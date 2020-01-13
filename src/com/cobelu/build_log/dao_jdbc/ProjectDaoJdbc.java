package com.cobelu.build_log.dao_jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.cobelu.build_log.dao_interface.ProjectDaoI;
import com.cobelu.build_log.entity.Project;

public class ProjectDaoJdbc extends BaseDaoJdbc implements ProjectDaoI {

	/*
	 * Fields
	 */

	private final String projectTable = "PROJECT";
	private final String idCol = "ID";
	private final String nameCol = "NAME";
	private final String rootCol = "ROOT";

	/*
	 * Methods
	 */

	@Override
	public List<Project> findAll() {
		String query = String.format("SELECT * FROM %s;", projectTable);
		List<Project> categories = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			categories = parseProjectsFrom(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return categories;
	}

	@Override
	public Project find(Project project) {
		String query = String.format("SELECT * FROM %s WHERE %s=?;", projectTable, idCol);
		Project foundProject = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, project.getId());
			rs = pstmt.executeQuery();
			List<Project> projects = parseProjectsFrom(rs);
			foundProject = projects.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return foundProject;
	}

	@Override
	public void insert(Project project) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Project project) {
		// TODO Auto-generated method stub

	}

	/*
	 * Helpers
	 */

	private List<Project> parseProjectsFrom(ResultSet rs) throws SQLException {
		List<Project> projects = new LinkedList<Project>();
		while (rs.next()) {
			String name = rs.getString(nameCol);
			String root = rs.getString(rootCol);
			Project project = new Project(name, new File(root));
			projects.add(project);
		}
		return projects;
	}

	/*
	 * Getters and Setters
	 */

	public String getProjectTable() {
		return projectTable;
	}

	public String getRootCol() {
		return rootCol;
	}

	public String getNameCol() {
		return nameCol;
	}

}
