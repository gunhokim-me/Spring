package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

public class UserServiceTest extends ModelTestConfig{

	@Resource(name="userService")
	private UserService userService;
	
	@Before
	public void setup() {
		UserVo vo = new UserVo("testUser", "테스트사용자", "testUserPass", new Date(),"대덕", "대전 중구 중앙로 76", "4층", "34940","brown.png","uudi-generated-filename.png");
		
		userService.registUser(vo);
		
		userService.deleteUser("ddit");
	}
	
	@After
	public void tearDown() {
		userService.deleteUser("testUser");
	}
	
	
	@Test
	public void getUserTest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userService.selectUser(userid);
		/***Then***/
		assertEquals("브라운", userVo.getUsernm());
	}
	
	@Test
	public void selectAllUserTest() {
		/***Given***/

		/***When***/
		List<UserVo> userList = userService.selectAllUser();
		
		/***Then***/
		assertEquals(18, userList.size());
	}
	//사용자 아이디가 존재하지 않을 때 특정 사용자 조회
	@Test
	public void selectUserNotExsistTest() {
		/***Given***/
		String userid = "brownn";
		
		/***When***/
		UserVo user = userService.selectUser(userid);
		
		/***Then***/
		assertNull(user);
	}
	
//	@Test
//	public void PagingUserList() {
//		/***Given***/
//		UserServiceI userService = new UserService();
//		PageVo vo = new PageVo();
//		vo.setPage(1);
//		vo.setPageSize(5);
//		/***When***/
//		Map<String, Object>map = userService.selectPagingUser(vo);
//		List<UserVo> list = (List<UserVo>)map.get("userList");
//		
//		
//		/***Then***/
//		assertEquals(5, list.size());
//	}
	
	//사용자 페이징 조회 테스트
	@Test
	public void selectPagingUserTest() {
		/***Given***/
		PageVo vo = new PageVo(1,5);
		
		/***When***/
		//List<UserVo> pagingUser = userService.selectPagingUser(vo);
		Map<String, Object> map = userService.selectPagingUser(vo);
		List<UserVo> list = (List<UserVo>)map.get("userList");
		//int userCnt = (int)map.get("userCnt");
		
		/***Then***/
		assertEquals(5, list.size());
		//assertEquals(18, userCnt);
		
	}
	
	//사용자 페이징 조회 테스트
	@Test
	public void modifyUserTest() {
		/***Given***/
		//userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		UserVo vo = new UserVo("ddit","대덕인재","dditpass",new Date(),"개발원 m","대전시 중구 중앙로 76","4층 대덕인재개발원","34940","brown.png","uudi-generated-filename.png");
		
		/***When***/
		int cnt = userService.registUser(vo);
		
		/***Then***/
		assertEquals(1, cnt);
		
	}
	
	@Test
	public void countUserTest() {
		/***Given***/
		String userid = "moon";
		
		/***When***/
		int cnt = userService.countUser(userid);
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void deleteUserTest() {
		/***Given***/
		String userid = "testUser";
		
		/***When***/
		int cnt = userService.deleteUser(userid);
		
		/***Then***/
		assertEquals(1, cnt);
	}
	

}
