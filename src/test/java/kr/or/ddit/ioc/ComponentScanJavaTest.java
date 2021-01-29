package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.config.ComponentScanJavaConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ComponentScanJavaConfig.class})
public class ComponentScanJavaTest {

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	//@Repository 어노페이션을 적용한 UserDaoImpl 스프링 빈이 정상적으로 컨테이너에 등록 되었는지 확인
	@Test
	public void UserDaoImplSpringBeanTest() {
		assertNotNull(userDao);
		UserVo userVo = userDao.getUser("brown");
		assertEquals("브라운", userVo.getUsernm());
	}
	
	//userServiceImpl 스프링 빈이 정상적으로 컨테이너에 등록 되었는지 확인
	@Test
	public void UserServiceImplSpringBeanTest() {
		assertNotNull(userDao);
		UserVo userVo = userService.getUser("brown");
		assertEquals("브라운", userVo.getUsernm());
	}

}
