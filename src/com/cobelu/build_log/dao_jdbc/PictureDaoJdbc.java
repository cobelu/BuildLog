package com.cobelu.build_log.dao_jdbc;

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

	/*
	 * Fields
	 */

	private final String pictureTable = "PICTURE";
	private final String idCol = "ID";
	private final String entryCol = "ENTRY_ID";
	private final String descriptionCol = "DESCRIPTION";
	private final String pictureCol = "DATA";

	/*
	 * Methods
	 */

	@Override
	public List<Picture> findAll() {
		String query = String.format("SELECT * FROM %s;", pictureTable);
		List<Picture> pictures = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			pictures = parsePicturesFrom(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return pictures;
	}

	@Override
	public List<Picture> findAllByEntry(Entry entry) {
		String query = String.format("SELECT * FROM %s WHERE %s=?;", pictureTable, entryCol);
		List<Picture> pictures = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, entry.getId());
			rs = pstmt.executeQuery();
			pictures = parsePicturesFrom(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return pictures;
	}

	@Override
	public Picture find(Picture picture) {
		String query = String.format("SELECT * FROM %s WHERE %s=?;", pictureTable, idCol);
		Picture foundPicture = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, picture.getId());
			rs = pstmt.executeQuery();
			List<Picture> pictures = parsePicturesFrom(rs);
			picture = pictures.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return foundPicture;
	}

	@Override
	public void insert(Picture picture) {
		String query = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?);", pictureTable, entryCol,
				descriptionCol, pictureCol);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, picture.getEntryId());
			pstmt.setString(2, picture.getDescription());
			pstmt.setBytes(3, picture.getImageAsBytes());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
	}

	@Override
	public void update(Picture picture) {
		String query = String.format("UPDATE %s SET %s=?, %s=?, %s=? WHERE %s=?;", pictureTable, entryCol,
				descriptionCol, pictureCol, idCol);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, picture.getEntryId());
			pstmt.setString(2, picture.getDescription());
			pstmt.setBytes(3, picture.getImageAsBytes());
			pstmt.setLong(4, picture.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
	}

	@Override
	public void delete(Picture picture) {
		String query = String.format("DELETE FROM %s WHERE %s=?;", pictureTable, idCol);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, picture.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
	}

	/*
	 * Helpers
	 */

	private List<Picture> parsePicturesFrom(ResultSet rs) {
		List<Picture> pictures = new LinkedList<Picture>();
		try {
			Long entryId = rs.getLong(entryCol);
			byte[] bytes = rs.getBytes(pictureCol);
			String description = rs.getString(descriptionCol);
			Picture picture = new Picture(entryId, description, bytes);
			pictures.add(picture);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pictures;
	}

}
