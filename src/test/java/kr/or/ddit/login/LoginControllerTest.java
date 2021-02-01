package kr.or.ddit.login;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Ignore;
import org.junit.Test;

import kr.or.ddit.test.config.WebTestConfig;

public class LoginControllerTest extends WebTestConfig {

	@Test
	public void viewTest() throws Exception {
		mockMvc.perform(get("/login/view"))
		.andExpect(status().isOk())
		.andExpect(view().name("login"));
	}
	
	@Test @Ignore
	public void processTest() throws Exception {
		mockMvc.perform(post("/login/process")
				      .param("userid", "brown")
				      .param("pass", "brownPass")
				      .param("price", "1000"))
					  .andExpect(view().name(""));
		
	}
	
	@Test @Ignore
	public void processTest2() throws Exception {
		mockMvc.perform(post("/login/process")
				.param("userid", "brown")
				.param("pass", "brownPass")
				.param("price", "1000"))
		.andExpect(view().name(""));
		
	}
	
	@Test
	public void processSuccessTest() throws Exception {
		mockMvc.perform(post("/login/process")
				.param("userid", "sally")
				.param("pass", "sallyPass")
				.param("price", "1000"))
		.andExpect(view().name("main"))
		.andDo(print());
		
	}
	
	@Test
	public void processFailTest() throws Exception {
		mockMvc.perform(post("/login/process")
				.param("userid", "brown")
				.param("pass", "failPassword")
				.param("price", "1000"))
		.andExpect(view().name("redirect:/login/view"));
		
	}

}
