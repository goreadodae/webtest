package file.model.service;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

import common.JDBCTemplate;
import file.model.dao.FileDao;
import file.model.vo.DataFile;
import file.model.vo.DataFile2;

public class FileService {

	public int uploadFile(DataFile df) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FileDao().uploadFile(conn,df);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<DataFile> selectAllFile() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<DataFile> list = new FileDao().selectAllFile(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public DataFile fileSelectDownload(String fileName, Timestamp uploadTime) {
		Connection conn = JDBCTemplate.getConnection();
		DataFile df = new FileDao().fileSelectDownload(conn, fileName, uploadTime);
		JDBCTemplate.close(conn);
		return df;
	}

	public int uploadFile2(DataFile2 df) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FileDao().uploadFile2(conn,df);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<DataFile2> selectAllFile2() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<DataFile2> list = new FileDao().selectAllFile2(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public DataFile2 fileSelectDownload2(String fileName) {
		Connection conn = JDBCTemplate.getConnection();
		DataFile2 df = new FileDao().fileSelectDownload2(conn, fileName);
		JDBCTemplate.close(conn);
		return df;
	}

	public DataFile removeFile(String fileName, Timestamp uploadTime) {
		Connection conn = JDBCTemplate.getConnection();
		DataFile df = new FileDao().fileSelectDownload(conn, fileName, uploadTime);
		int result = new FileDao().deleteFile(conn, fileName, uploadTime);
		if(result>0) {
			JDBCTemplate.commit(conn);
			return df;
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return null;
		
	}

	public DataFile2 removeFile2(String afterFileName) {
		Connection conn = JDBCTemplate.getConnection();
		DataFile2 df = new FileDao().fileSelectDownload2(conn, afterFileName);
		int result = new FileDao().deleteFile2(conn, afterFileName);
		if(result>0) {
			JDBCTemplate.commit(conn);
			return df;
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return null;
	}

}
