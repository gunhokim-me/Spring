package kr.or.ddit.test.config;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


//@WebAppConfiguration => 스프링 환경을 Web기반의 application Context로 생성
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/kr/or/ddit/config/spring/applicarion-context.xml",
		                           "classpath:/kr/or/ddit/config/spring/root-context.xml",
		                           "classpath:/kr/or/ddit/config/spring/datasource-context_dev.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class WebTestConfig {
	
	//@Resource(name="helloController")
	//스프링 빈중에 대입 가능한 타입의 스프링 빈을 주입한다.
	//만약 동일한 타입의 스프링 빈이 여러개 있을 경우 @Qulifier 어노테이션을 통해 
	//특정 스프링 빈의 이름을 지정할 수 있다.
	// ==> @Resource 어노테이션 하나를 사용 했을 때와 동일하다.
	
	@Autowired 
	private WebApplicationContext context;
	
	protected MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test @Ignore
	public void dummy() {
		
	}
}
