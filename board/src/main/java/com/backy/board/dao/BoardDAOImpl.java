package com.backy.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.backy.board.BoardVO;
import com.backy.board.DButil;

public class BoardDAOImpl implements BoardDAO{

	@Override
	public int save(BoardVO vo) {
		int result = 0;
		Connection con = DButil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("insert into board values( boardSeq.nextval, ?, ?, ?, sysdate, 0 )");
			ps.setString(1, vo.getWriter());
			ps.setString(2, vo.getBoardTitle());
			ps.setString(3, vo.getBoardContent());
			
			result = ps.executeUpdate();//실행 결과 result에 저장
			
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DButil.rC(ps, con);
		}
		return result;
	}

	@Override
	public int delete(long boardNo) {
		int result = 0;
		Connection con = DButil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from board where boardNo = ?");
			ps.setLong(1, boardNo);
			
			
			result = ps.executeUpdate();//실행 결과 result에 저장
			
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DButil.rC(ps, con);
		}
		return result;
	}

	@Override
	public int update(BoardVO vo) {
		int result = 0;
		Connection con = DButil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("update board set Writer = ?, boardTitle = ?, boardContent = ? where boardNo = 20");
			ps.setString(1, vo.getWriter());
			ps.setString(2, vo.getBoardTitle());
			ps.setString(3, vo.getBoardContent());
			ps.setLong(4, vo.getBoardNo());
			result = ps.executeUpdate();//실행 결과 result에 저장
			
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DButil.rC(ps, con);
		}
		return result;
	}

	@Override
	public long getCount() {
		long result =0;
		Connection con = DButil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
 		try {
			ps = con.prepareStatement("select count(*) as cnt from board");
			rs = ps.executeQuery();//실행 결과 result에 저장
			while(rs.next()){//반환되는 결과값이 있냐 없을때까지 동작
			result = rs.getLong("cnt");//컬럼명을 문자열로 가져와서 Int로
			}
 		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DButil.rC(rs, ps, con);
		}
		return result;
	}
	//select * from board; => 조회결과물이 다중행(List 배열) 다중컬럼(vo Class)의 결과를 얻는다.
	@Override
	public List<BoardVO> getAll() {
		List<BoardVO> list = null;
		Connection con = DButil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
 		try {
			ps = con.prepareStatement("select * from board");
			rs = ps.executeQuery();//실행 결과 result에 저장
			list = new ArrayList<BoardVO>();//list 안에 정보 -> 다중으로 vo객체로 만들겠다
			while(rs.next()){//반환되는 결과값이 있냐 없을때까지 동작
				BoardVO vo = new BoardVO();
				vo.setBoardNo( rs.getLong("boardNo") );//컬럼개수 5개 ,5개 필요
				vo.setWriter( rs.getString("writer") );
				vo.setBoardTitle( rs.getString("boardTitle") );
				vo.setBoardContent( rs.getString("boardContent") );
				vo.setRegiDate(rs.getString("regiDate"));
				vo.setReadCount(rs.getInt("readCount"));
				list.add(vo);//만들어질때마다 리스트에 추가
			}
 		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DButil.rC(rs, ps, con);
		}
		
		return list;
	}

	@Override
	public BoardVO getOne(long boardNo) {
		BoardVO vo = null;//반환되는 타입
		Connection con = DButil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from board where boardNo = ?");
			ps.setLong(1, boardNo);
			rs= ps.executeQuery();
			while(rs.next()) {
				vo = new BoardVO();
				vo.setBoardNo(rs.getLong("boardNo"));
				vo.setWriter( rs.getString("writer") );
				vo.setBoardTitle( rs.getString("boardTitle") );
				vo.setBoardContent( rs.getString("boardContent") );
				vo.setRegiDate(rs.getString("regiDate"));
				vo.setReadCount(rs.getInt("readCount"));
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.rC(rs, ps, con);
		}
		return vo;
	}
	//쿼리문
	//
	@Override
	public List<BoardVO> getPage(int startNum, int endNum) {
		List<BoardVO> list = null;
		String query = "select * from ("
	            + "select rownum  numData , middle.* from (select * from board order by boardNo desc) middle"
	            + ") where numData >= ? and numData <=?";
		Connection con = DButil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, startNum);
			ps.setInt(2, endNum);
			rs= ps.executeQuery();
			list = new ArrayList<BoardVO>();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBoardNo(rs.getLong("boardNo"));
				vo.setWriter( rs.getString("writer") );
				vo.setBoardTitle( rs.getString("boardTitle") );
				vo.setBoardContent( rs.getString("boardContent") );
				vo.setRegiDate(rs.getString("regiDate"));
				vo.setReadCount(rs.getInt("readCount"));
				list.add(vo);

			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.rC(rs, ps, con);
		}
		return list;
	}

}
