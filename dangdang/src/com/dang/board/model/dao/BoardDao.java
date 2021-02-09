package com.dang.board.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dang.board.model.vo.Board;
import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;

public class BoardDao {

	public BoardDao() {

	}

	JDBCTemplate jdt = JDBCTemplate.getInstance();

	public ArrayList<Board> addBoard(Connection conn, Board board) {
		// 작성 완료시 게시판 테이블에 들어갈 데이터
		PreparedStatement pstm = null;
		ArrayList<Board> boardList = new ArrayList<>();
		try {
			String query = "insert into BD_NOTICE(BD_NO_IDX, KG_NAME, TITLE, REG_DATE, CONTENT)"
					+ "values (?, ?, ?, ?, ?)";
			pstm = conn.prepareStatement(query);

			pstm.setInt(1, board.getBdIdx());
			pstm.setString(2, board.getKgName());
			pstm.setString(3, board.getTitle());
			pstm.setDate(4, board.getRegDate());
			pstm.setString(5, board.getContent());

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.BA01, e);
		} finally {
			jdt.close(pstm);
		}
		return boardList;
	}

	public ArrayList<Board> listBoard(Connection conn) {
		// 게시판 목록에 출력될 데이터
		ArrayList<Board> boardList = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rSet = null;

		try {
			conn = jdt.getConnection();
			String query = "select * from BD_NOTICE";
			pstm = conn.prepareStatement(query);
			rSet = pstm.executeQuery();

			while (rSet.next()) {
				Board board = new Board();
				board.setBdIdx(rSet.getInt("BD_NO_IDX"));
				board.setKgName(rSet.getString("KG_NAME"));
				board.setTitle(rSet.getString("TITLE"));
				board.setRegDate(rSet.getDate("REG_DATE"));
				board.setContent(rSet.getString("CONTENT"));

				boardList.add(board);
			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.BL01, e);
		} finally {
			jdt.close(rSet, pstm);
		}
		return boardList;
	}

	public int modifyBoard(Connection conn, String bdIdx) {

		int res = 0;
		PreparedStatement pstm = null;

		try {
			// modifyBoard.do 경로에서 수정 버튼을 누를시 수정,
//    		String query = "update BD_NOTICE set CONTENT = ? set TITLE = ? where BD_NO_IDX = ?";
//    		pstm = conn.prepareStatement(query);
//    		pstm.setString(1, board.getContent());
//    		pstm.setString(2, board.getTitle());
//    		pstm.setInt(3, board.getBdIdx());
//			res = pstm.executeUpdate();

			// modifyBoard.do 경로에서 삭제 버튼을 누를시 삭제
			String query = "delete * from BD_NOTICE where BD_NO_IDX";
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, Integer.parseInt(bdIdx));
			res = pstm.executeUpdate();
			// BD_NOTICE 테이블에서 해당 번호의 게시글 삭제

		} catch (Exception e) {
			throw new DataAccessException(ErrorCode.BM01, e);
			// throw new DataAccessException(ErrorCode.BM02, e);
		} finally {
			jdt.close(pstm);
		}
		return res;
	}

	public Board viewBoard(Connection conn, String bdIdx) {

		Board board = null;
		PreparedStatement pstm = null;
		ResultSet rSet = null;

		try {
			String query = "select * from BD_NOTICE where BD_NO_IDX = ?";
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, Integer.parseInt(bdIdx));
			rSet = pstm.executeQuery();

			if (rSet.next()) {
				board.setBdIdx(rSet.getInt("BD_NO_IDX"));
				board.setKgName(rSet.getString("KG_NAME"));
				board.setTitle(rSet.getString("TITLE"));
				board.setRegDate(rSet.getDate("REG_DATE"));
				board.setContent(rSet.getString("CONTENT"));
				
				board = new Board();
			}

		} catch (Exception e) {
			throw new DataAccessException(ErrorCode.BV01, e);
		}
		return board;
	}

}
