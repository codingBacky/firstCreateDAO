package com.backy.board.dao;

import java.util.List;

import com.backy.board.BoardVO;

public interface BoardDAO {
	public int save(BoardVO vo);//보드안에 저장하겠다. 'C '데이터 만들어 넣기
	public int delete(long boardNo);//'D' 데이터 삭제 public int delete(BoardVO vo)-> BoardVO vo안에 boardNo이 들어있음 하지만 메모리낭비
	public int update(BoardVO vo);//'U' 테이블에 데이터 수정
	//'R'!!중요!! 테이블에 저장된 자료 읽기 -> 자료의 개수,한번에 전부 읽어오거나, 전체 자료의 개수 하나의 게시글을 읽어오거나, 한번에 20개씩 (한 페이지 나타냄) 읽어올때도 있다.
	public long getCount();//자료 개수 읽어오기
	public List<BoardVO> getAll();//BoardVO로 이루어진 배열 모두 가져온다 -> 자료를 다 가져오면 메모리가 터진다...
	public BoardVO getOne(long boardNo);//하나만 읽어오기
	public List<BoardVO> getPage(int startNum, int endNum);//getAll은 한번 사용후 getPage로 변환해야함
}
