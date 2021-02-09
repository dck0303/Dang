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
	
	public void BoardService() {
		
	}
	
	public ArrayList<Board> addBoard(Board board) {
		Connection conn = jdt.getConnection();
		ArrayList<Board> boardList = boardDao.listBoard(conn);
		try {
			boardList = boardDao.addBoard(conn, board);
		}finally {
			jdt.close(conn);
		}
		return boardList;
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
	
	public int modifyBoard(String bdIdx) throws SQLException{
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = boardDao.modifyBoard(conn, bdIdx);
			jdt.commit(conn);
		} finally {
			jdt.close(conn);
		}
		return res;
	}
	
	public Board viewBoard(String bdIdx){
		Connection conn = jdt.getConnection();
		Board board = boardDao.viewBoard(conn, bdIdx);
		return board;
	}

}
