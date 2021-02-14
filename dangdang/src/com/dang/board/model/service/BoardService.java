package com.dang.board.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dang.board.model.dao.BoardDao;
import com.dang.board.model.vo.Board;
import com.dang.common.jdbc.JDBCTemplate;

public class BoardService {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	private BoardDao boardDao = new BoardDao();
	
	public BoardService() {
		
	}
	
//	public int getNext() {
//		
//		Connection conn = jdt.getConnection();
//		int res = 0;
//		try {
//			res = boardDao.getNext(conn);
//		} finally {
//			jdt.close(conn);
//		}
//		return res;
//	}
	
	public int addBoard(String title, String kgName, String content) {
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = boardDao.addBoard(conn, title, kgName, content);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	
	public ArrayList<Board> listBoard(){
		Connection conn = jdt.getConnection();
		ArrayList<Board> boardList = new ArrayList<>();
		try {
			boardList = boardDao.listBoard(conn);
		} finally {
			jdt.close(conn);
		}
		return boardList;
	}
	
//	public boolean nextPage(int pageNumber) {
//		Connection conn = jdt.getConnection();
//		boolean res;
//		try {
//			res = boardDao.nextPage(conn, pageNumber);
//		} finally {
//			jdt.close(conn);
//			
//		}
//		return res;
//	}
	
	public int modifyBoard(int bdIdx, String title, String content) {
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = boardDao.modifyBoard(conn, bdIdx, title, content);
		} finally {
			jdt.close(conn);
		}
		return res;
	}
	
	public int deleteBoard(int bdIdx) {
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = boardDao.deleteBoard(conn, bdIdx);
			jdt.commit(conn);
		} finally {
			jdt.close(conn);
		}
		return res;
	}
	
	public Board viewBoard(int bdIdx){
		Connection conn = jdt.getConnection();
		Board board;
		try {
			board = boardDao.viewBoard(conn, bdIdx);
		} finally {
			jdt.close(conn);
		}
		return board;
	}

}
