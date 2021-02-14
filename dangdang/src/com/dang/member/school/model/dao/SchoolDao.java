package com.dang.member.school.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.common.util.file.FileVo;
import com.dang.map.model.vo.Service;
import com.dang.member.school.model.vo.SchoolMember;
import com.dang.member.user.model.vo.UserMember;



public class SchoolDao {
	
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	
	
	public SchoolMember memberAuthenticate(Connection conn, String kgId, String kgPw) {
		SchoolMember schoolMember = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from kindergarden where kg_id =? and kg_pw = ?";
		
		try{

			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgId);
			pstm.setString(2, kgPw);
			
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				
				schoolMember = new SchoolMember();
				schoolMember.setKgName(rset.getString("kg_name"));
				schoolMember.setKgId(rset.getString("kg_id"));
				schoolMember.setKgIdx(rset.getString("kg_idx"));
				schoolMember.setKgPw(rset.getString("kg_pw"));
				schoolMember.setKgAddress(rset.getString("kg_address"));
				schoolMember.setKgTell(rset.getString("kg_tell"));
				schoolMember.setKgOperateTime(rset.getString("kg_operate_time"));
				schoolMember.setKgNotice(rset.getString("kg_notice"));
				schoolMember.setKgGrade(rset.getString("kg_grade"));
				schoolMember.setKgEmail(rset.getString("kg_email"));
			
	
			} //select문은 commit이나 rollback 필요없음
			
			
		}catch(SQLException e) {
			throw new DataAccessException(ErrorCode.LM01, e);
		}finally {
			jdt.close(rset, pstm);
		}

		return schoolMember; 
		
	}
	
	
	public Service SchoolproService(Connection conn, String kgName) {
		Service schoolProService = null;
		PreparedStatement  pstm = null;
		ResultSet rset = null;
		
		String query = "select * from services where kg_name = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgName);
			
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				schoolProService = new Service();
				schoolProService.setKgName(rset.getString("kg_name"));
				schoolProService.setIsKg(rset.getInt("is_kg"));
				schoolProService.setIsCafe(rset.getInt("is_cafe"));
				schoolProService.setIsHotel(rset.getInt("is_hotel"));
				schoolProService.setIsPickup(rset.getInt("is_pickup"));
				schoolProService.setIsMedic(rset.getInt("is_medic"));
				schoolProService.setIsAcademy(rset.getInt("is_academy"));
				schoolProService.setIsSpa(rset.getInt("is_spa"));
				
			}		
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SE01, e);
		}finally {
			jdt.close(rset, pstm);
		}

		return schoolProService;
	}
	
	
	
	public SchoolMember findSchoolId(Connection conn, String schoolName, String schoolPhone) {
		SchoolMember schoolMember = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select * from kindergarden where kg_name = ? and kg_tell = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, schoolName);
			pstm.setString(2, schoolPhone);
			
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				schoolMember = new SchoolMember();
				schoolMember.setKgName(rset.getString("kg_name"));
				schoolMember.setKgId(rset.getString("kg_id"));
				schoolMember.setKgPw(rset.getString("kg_pw"));
				schoolMember.setKgAddress(rset.getString("kg_address"));
				schoolMember.setKgTell(rset.getString("kg_tell"));
				schoolMember.setKgOperateTime(rset.getString("kg_operate_time"));
				schoolMember.setKgNotice(rset.getString("kg_notice"));
				schoolMember.setKgGrade(rset.getString("kg_grade"));
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01, e);
		}finally {
			jdt.close(rset, pstm);;
		}
		
		return schoolMember;
		
	}
	
	
	
	/*
	
	//해당 정보에 맞는 회원검색
		public SchoolMember findSchoolPw(Connection conn, String kgId, String kgEmail) {
			
			SchoolMember schoolMember = null;
			PreparedStatement pstm = null;
			ResultSet rset = null;
			
			String query = "select * from kindergarden where kg_id = ? and kg_email = ?";
			
			try {
				pstm = conn.prepareStatement(query);
				pstm.setString(1, kgId);
				pstm.setString(2, kgEmail);
				
				rset = pstm.executeQuery();
				
				//결과값이 있다면
				if(rset.next()) {
					schoolMember = new SchoolMember();
					schoolMember.setKgName(rset.getString("kgName"));
					schoolMember.setKgIdx(rset.getString("kgIdx"));
					schoolMember.setKgId(rset.getString("kgId"));
					schoolMember.setKgPw(rset.getString("kgPw"));
					schoolMember.setKgAddress(rset.getString("kgAddress"));
					schoolMember.setKgTell(rset.getString("kgTell"));
					schoolMember.setKgOperateTime(rset.getString("kgOperateTime"));
					schoolMember.setKgNotice(rset.getString("kgNotice"));
					schoolMember.setKgGrade(rset.getString("kgGrade"));
					schoolMember.setKgEmail(rset.getString("kgEmail"));
				}
				
			} catch (SQLException e) {
				throw new DataAccessException(ErrorCode.SM01, e);
				
			} finally {
				jdt.close(rset, pstm);
			}
			System.out.println(schoolMember);
			return schoolMember;	
		
		}
		
	
	*/
	
	
	
	
	
	public int modifySchoolInfo(Connection conn, String kgId, String kgName, String kgAddress, String kgTell, String kgOperateTime, String kgNotice, String kgEmail ) {
		
		int res = 0;
		PreparedStatement pstm = null;
		String query = "update kindergarden set kg_name =?, kg_address=?, kg_tell=?, kg_operate_time=?, kg_notice=?, kg_email=? where kg_id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgName);
			pstm.setString(2, kgAddress);
			pstm.setString(3, kgTell);
			pstm.setString(4, kgOperateTime);
			pstm.setString(5, kgNotice);
			pstm.setString(6, kgEmail);
			pstm.setString(7, kgId);
			
			res = pstm.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.UM01, e);
			
		}finally {
			jdt.close(pstm);
		}
		
		return res;
		
	}
	
	
public int modifySchoolService(Connection conn, String kgName, int isKg, int isCafe, int isHotel, int isPickup, int isMedic, int isAcademy, int isSpa ) {
		
		int res = 0;
		PreparedStatement pstm = null;
		String query = "update services set is_kg =?, is_cafe=?, is_hotel=?, is_pickup=?, is_medic =?, is_academy =?, is_spa = ? where kg_name = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, isKg);
			pstm.setInt(2, isCafe);
			pstm.setInt(3, isHotel);
			pstm.setInt(4, isPickup);
			pstm.setInt(5, isMedic);
			pstm.setInt(6, isAcademy);
			pstm.setInt(7, isSpa);
			pstm.setString(8, kgName);
			
			res = pstm.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.UM01, e);
			
		}finally {
			jdt.close(pstm);
		}
		
		return res;
		
	}


	public int uploadSchoolPhoto(Connection conn, FileVo schoolPhotoData, String kgIdx) {
		int res = 0;
		String fIdx = "";
		
		
		/* 1. 새로 등록되는 게시글의 파일 정보 저장
		typeIdx값이 시퀀스 currval
		if(fileData.getTypeIdx() == null) {
			fIdx = "'k'||kgIdx";
		//2. 수정할 때 사용자가 파일을 추가 등록해서 파일 정보 저장
		//	수정할 게시글의 bdIdx값
		}else {
			
		}*/
		
		fIdx = "'k'||" + kgIdx;
		
		
		
		String query = "insert into d_file (f_idx,type_idx,origin_file_name,rename_file_name,save_path) "
				+ "values(sc_f_idx.nextval," + fIdx + ",?,?,?)";
		
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, schoolPhotoData.getOriginFileName());
			pstm.setString(2, schoolPhotoData.getRenameFileName());
			pstm.setString(3, schoolPhotoData.getSavePath());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.IF01, e);
		}finally {
			jdt.close(pstm);
		}
		
		return res;
	}
		
	
	
	
	
	
	
	

}
