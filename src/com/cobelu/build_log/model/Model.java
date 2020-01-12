package com.cobelu.build_log.model;

import java.io.File;

/**
 * The head Model of the BullyApplication. Holds onto daos, and all information
 * pertaining to the app.
 * 
 * @author cobelu
 */
public class Model {

	/*
	 * Fields
	 */

	private File rootDirectory;
	private EntryModel entryModel;
	private PictureModel pictureModel;

	/*
	 * Constructor
	 */

	public Model() {
		rootDirectory = new File("~/");
		entryModel = new EntryModel();
		pictureModel = new PictureModel();
	}

	/*
	 * Getters and Setters
	 */

	public File getRootDirectory() {
		return rootDirectory;
	}

	public EntryModel getEntryModel() {
		return entryModel;
	}

	public PictureModel getPictureModel() {
		return pictureModel;
	}

}
