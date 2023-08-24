package test.com.backy;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.backy.board.BoardVO;
import com.backy.board.DButil;
import com.backy.board.dao.BoardDAO;
import com.backy.board.dao.BoardDAOImpl;

public class Test {
	@org.junit.Test
	public void connectionTest() {
		assertNotNull(DButil.getConnection());
		
		
		/*
		if ( null == DButil.getConnection()) {
			System.out.println("데이터 베이스 연결에 실패하였습니다");
		}else {
			System.out.println("데이터 베이스 연결 성공\n축");
		}
		*/
		
	}
	@org.junit.Test//자바언어에게 알려주는 주석문
	public void daoSaveTest() {
		//데이터 만들어서 데이터를 넘기면서 입력을 지시 => 입력 성공적으로 이루어졌으면 1 반환
		BoardVO vo = new BoardVO("backy","title test 1","contents test 1");
		BoardDAO dao = new BoardDAOImpl();
		int rtnValue = dao.save(vo);
		assertEquals(rtnValue,1);
	}
	@org.junit.Test//자바언어에게 알려주는 주석문
	public void daoDeleteTest() {
		BoardDAO dao = new BoardDAOImpl();
		assertEquals(dao.delete(32),1);
	}
	@org.junit.Test//자바언어에게 알려주는 주석문
	public void daoUpdateTest() {
		//데이터 만들어서 데이터를 넘기면서 입력을 지시 => 입력 성공적으로 이루어졌으면 1 반환
		BoardVO vo = new BoardVO("backy","title test 1","contents test 1");
		vo.setBoardNo(22);
		BoardDAO dao = new BoardDAOImpl();//게시판에
		int rtnValue = dao.update(vo);//업데이트를 사용해서
		assertEquals(rtnValue,1);
	}
	@org.junit.Test
	public void daoGetCountTest() {
		BoardDAO dao = new BoardDAOImpl();//게시판에
		assertEquals(dao.getCount(),30);
		System.out.println(dao.getCount());
		}
	@org.junit.Test
	public void daoGetAllTest() {
		BoardDAO dao = new BoardDAOImpl();//게시판에
		
		List<BoardVO> list = dao.getAll();
		assertNotNull(list);
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());//메소드 get을 통해 배열 인덱스에 접근 tostring으로 출력
		}
	}
	@org.junit.Test
	public void daoGetOneTest() {
		BoardDAO dao = new BoardDAOImpl();
		BoardVO vo = dao.getOne(20L);
		assertNotNull(vo);
		System.out.println(vo);
	}
	@org.junit.Test
	public void daoGetPageTest() {
		BoardDAO dao = new BoardDAOImpl();
		int currentPage = 1; 
		int pageListCount = 10;
		int boardCount = (int)dao.getCount();
		int totalPage = (int)(boardCount/(double)pageListCount + 0.999999);
		int endNum = currentPage * pageListCount;
		int startNum = endNum - ( pageListCount - 1 );//endNum -  pageListCount + 1 
		endNum = (endNum > boardCount)? boardCount : endNum;
		
		List<BoardVO> list = dao.getPage(startNum, endNum);//시작과 끝, 1페이지를 보겠다.
		assertNotNull(list);
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());//메소드 get을 통해 배열 인덱스에 접근 tostring으로 출력
		}
	}
}
