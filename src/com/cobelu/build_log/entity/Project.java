package com.cobelu.build_log.entity;

import java.io.File;

public class Project extends BaseEntity {

	/*
	 * Fields
	 */

	private String name;
	private File root;

	/*
	 * Constructors
	 */

	public Project() {
		super();
	}

	public Project(String name, File root) {
		this();
		this.name = name;
		this.root = root;
	}

	/*
	 * Getters and Setters
	 */

	public String getName() {
		return name;
	}

	public File getRoot() {
		return root;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRoot(File root) {
		this.root = root;
	}

	/*
	 * To String
	 */

	@Override
	public String toString() {
		return String.format("Project: {name: %s}", name);
	}

}
