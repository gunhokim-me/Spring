package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

//스프링 환경에서 junit 코드를 실행 ==> junit 코드도 스프링 빈으로 등록
//필요한것 (아래 2개)

public class UserDaoTest extends ModelTestConfig{
	
	@Resource(name="userDao")
	private UserDao userDao;

	@Before
	public void setup() {
		
		//테스트에서 사용할 신규 사용자 추가
		UserVo vo = new UserVo("testUser", "테스트사용자", "testUserPass", new Date(),"대덕", "대전 중구 중앙로 76", "4층", "34940","brown.png","uudi-generated-filename.png");
		
		//데이터가 있는데 추가되면 에러가 발생 꼭 삭제를 해주어야한다.
		userDao.registUser(vo);
		
		//신규입력 테스트를 위해 테스트 과정에서 입력된 데이터를 삭제하겠다.
		userDao.deleteUser("ddit");
	}
	
	//테스트용으로 등록한 데이터를 삭제한다.
	@After
	public void tesrDown() {
		userDao.deleteUser("testUser");
	}
	
	//전체 사용자 조회 테스트
	@Test
	public void selectAllUserTest() {
		/***Given***/

		/***When***/
		List<UserVo> userList = userDao.selectAllUser();
		
		/***Then***/
		assertEquals(18, userList.size());
	}
	
	//사용자 아이디를 이용하여 특정 사용자 정보 조회
	@Test
	public void getUserTest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userDao.selectUser(userid);
		
		/***Then***/
		assertEquals("브라운", userVo.getUsernm());
	}
	
	//사용자 페이징 조회 테스트
	@Test
	public void selectPagingUserTest() {
		/***Given***/
		PageVo vo = new PageVo(1,5);
		
		/***When***/
		List<UserVo> pagingUser = userDao.selectPagingUser(vo);
		
		/***Then***/
		assertNotNull(pagingUser);
		assertEquals(5, pagingUser.size());
		
	}
	
	@Test
	public void selectUserCnt() {
		/***Given***/
		
		/***When***/
		int userCnt = userDao.selectAllUserCnt();
		
		/***Then***/
		assertEquals(18, userCnt);
		
	}
	
//	@Test
//	public void modifyUserTest() {
//		/***Given***/
//		//userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
//		UserVo vo = new UserVo("ddit","대덕인재","dditpass",new Date(),"개발원 m","대전시 중구 중앙로 76","4층 대덕인재개발원","34940");
//		
//		/***When***/
//		int cnt = userDao.modifyUser(vo);
//		
//		/***Then***/
//		assertEquals(1, cnt);
//		
//
//	}
	
	@Test
	public void insertUserTest() {

		
		/***Given***/
		//userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		UserVo vo = new UserVo("ddit","대덕인재","dditpass",new Date(),"개발원 m","대전시 중구 중앙로 76","4층 대덕인재개발원","34940","brown.png","uudi-generated-filename.png");
		
		/***When***/
		int cnt = userDao.registUser(vo);
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void countUserTest() {
		/***Given***/
		//userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		String userid = "moon";
		
		/***When***/
		int cnt = userDao.countUser(userid);
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void deleteUserTest() {
		/***Given***/
		//해당 테스트가 실행될 때는 testUser라는 사용자가 before메소드에 등록인 된 상태
		String userid = "testUser";
		
		/***When***/
		int cnt = userDao.deleteUser(userid);
		
		/***Then***/
		assertEquals(1, cnt);
	}


}
