package com.cobelu.build_log.dao_jdbc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.cobelu.build_log.dao_interface.PictureDaoI;
import com.cobelu.build_log.entity.Entry;
import com.cobelu.build_log.entity.Picture;

public class PictureDaoJdbc extends BaseDaoJdbc implements PictureDaoI {

	private final String pictureTable = "PICTURE";
	private final String idCol = "ID";
	private final String entryCol = "ENTRY_ID";
	private final String pictureCol = "DATA";
	private final String descriptionCol = "DESCRIPTION";

	@Override
	public List<Picture> findAll() {
		List<Picture> pictures = null;
		String query = "SELECT * FROM " + pictureTable + ";";
		ResultSet rs;
		try {
			rs = openAndQuery(query);
			pictures = parsePicturesFrom(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAfterQuery();
		}
		return pictures;
	}

	@Override
	public List<Picture> findAllByEntry(Entry entry) {
		String query = "";
		query += "SELECT * FROM ";
		query += pictureTable;
		query += " WHERE ";
		query += entryCol;
		query += "=";
		query += entry.getId();
		query += ";";
		List<Picture> pictures = null;
		ResultSet rs;
		try {
			rs = openAndQuery(query);
			pictures = parsePicturesFrom(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} finally {
			closeAfterQuery();
		}
		return pictures;
	}

	@Override
	public Picture find(Picture picture) {
		String query = "";
		query += "SELECT * FROM ";
		query += pictureTable;
		query += " WHERE ";
		query += idCol;
		query += "=";
		query += picture.getId();
		query += ";";
		List<Picture> pictures = null;
		Picture rtnEntry = null;
		ResultSet rs;
		try {
			rs = openAndQuery(query);
			pictures = parsePicturesFrom(rs);
			picture = pictures.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} finally {
			closeAfterQuery();
		}
		return rtnEntry;
	}

	@Override
	public void insert(Picture picture) {
		String insert = "";
		insert += "INSERT INTO ";
		insert += pictureTable;
		insert += "(";
		insert += entryCol;
		insert += ", ";
		insert += pictureCol;
		insert += ", ";
		insert += descriptionCol;
		insert += ") VALUES(";
		insert += picture.getEntryId();
		insert += ", ";
		insert += picture.getFile();
		insert += ", \"";
		insert += picture.getDescription();
		insert += "\");";
		openAndUpdate(insert);
		closeAfterUpdate();
	}

	@Override
	public void update(Picture picture) {
		String update = "";
		update += "UPDATE ";
		update += pictureTable;
		update += " SET ";
		update += entryCol;
		update += "=";
		update += picture.getEntryId();
		update += "\", ";
		update += pictureCol;
		update += "=\"";
		update += fileToBlob(picture.getFile());
		update += "\", ";
		update += descriptionCol;
		update += "=\"";
		update += picture.getDescription();
		update += "\" WHERE ";
		update += idCol;
		update += "=";
		update += picture.getId();
		update += ";";
		openAndUpdate(update);
		closeAfterUpdate();
	}

	@Override
	public void delete(Picture picture) {
		String delete = "";
		delete += "DELETE FROM ";
		delete += pictureTable;
		delete += " WHERE ";
		delete += idCol;
		delete += "=";
		delete += picture.getId();
		delete += ";";
		openAndUpdate(delete);
		closeAfterUpdate();
	}

	/**
	 * Parses pictures obtained from a ResultSet.
	 * 
	 * @param rset A ResultSet full of pictures
	 * @return A List of Picture objects
	 * @throws SQLException
	 */
	private List<Picture> parsePicturesFrom(ResultSet rs) throws SQLException {
		List<Picture> pictures = new LinkedList<Picture>();
		while (rs.next()) {
			Picture picture = new Picture();
			picture.setId(rs.getLong(idCol));
			// TODO: Fix BLOB
			picture.setEntryId(rs.getLong(entryCol));
			picture.setDescription(rs.getString(descriptionCol));
			pictures.add(picture);
		}
		return pictures;
	}

	/**
	 * Read the file and returns the byte array.
	 * 
	 * https://www.sqlitetutorial.net/sqlite-java/jdbc-read-write-blob/
	 * 
	 * @param file
	 * @return the bytes of the file
	 */
	private byte[] fileToBlob(File file) {
		ByteArrayOutputStream bos = null;
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			bos = new ByteArrayOutputStream();
			for (int len; (len = fileInputStream.read(buffer)) != -1;) {
				bos.write(buffer, 0, len);
			}
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return bos != null ? bos.toByteArray() : null;
	}

	/**
	 * read the picture file and insert into the material master table
	 *
	 * @param id
	 * @param filename
	 */
	public void readPicture(int id, String filename) {
		// update sql
		String selectSQL = "SELECT picture FROM materials WHERE id=?";
		ResultSet rs = null;
		FileOutputStream fos = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = null;
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			// write binary stream into file
			File file = new File(filename);
			fos = new FileOutputStream(file);

			System.out.println("Writing BLOB to file " + file.getAbsolutePath());
			while (rs.next()) {
				InputStream input = rs.getBinaryStream("picture");
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					fos.write(buffer);
				}
			}
		} catch (SQLException | IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
				if (fos != null) {
					fos.close();
				}

			} catch (SQLException | IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
