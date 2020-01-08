package com.cobelu.build_log.dao_jdbc;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

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
			pictures = parsePicturesFromResultSet(rs);
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
			pictures = parsePicturesFromResultSet(rs);
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
			pictures = parsePicturesFromResultSet(rs);
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
		String insert = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?);", pictureTable, entryCol,
				pictureCol, descriptionCol);
		PreparedStatement pstmt = prepareStatement(insert);
		try {
			pstmt.setLong(1, picture.getEntryId());
			pstmt.setBlob(2, bufferdImageToBlob(picture.getImage()));
			pstmt.setString(3, picture.getDescription());
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		update += bufferdImageToBlob(picture.getImage());
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

	private List<Picture> parsePicturesFromResultSet(ResultSet rs) {
		List<Picture> pictures = new LinkedList<Picture>();
		try {
			// TODO: Fix ID
			Long entryId = rs.getLong(entryCol);
			BufferedImage image = blobToImage(rs.getBlob(pictureCol));
			String description = rs.getString(descriptionCol);
			Picture picture = new Picture(entryId, image, description);
			pictures.add(picture);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pictures;
	}

	private BufferedImage blobToImage(Blob blob) {
		InputStream in;
		BufferedImage image = null;
		try {
			in = blob.getBinaryStream();
			image = ImageIO.read(in);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	private Blob bufferdImageToBlob(BufferedImage image) {
		Blob blob = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", baos);
			byte[] imageByteArray = baos.toByteArray();
			blob = new SerialBlob(imageByteArray);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return blob;
	}

}
