package kr.or.ddit.user.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.test.config.WebTestConfig;
import kr.or.ddit.user.service.UserService;

public class UserContollerTest extends WebTestConfig{

	@Resource(name="userService")
	private UserService userService;
	
	@Test
	public void allUserTest() throws Exception{
		mockMvc.perform(post("/user/allUser"))
		.andExpect(view().name("user/allUser"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void PagingUserTest() throws Exception{
		mockMvc.perform(post("/user/pagingUser"))
						.andExpect(view().name("user/pagingUser"))
						.andExpect(status().isOk())
						.andExpect(model().attributeExists("pagination"))
						.andExpect(model().attributeExists("userList"))
						.andExpect(model().attributeExists("pageVo"))
						.andDo(print());
	}	
	
	@Test
	public void PagingUser2PageTest() throws Exception{
		mockMvc.perform(post("/user/pagingUser").param("page", "2"))
		.andExpect(view().name("user/pagingUser"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("pagination"))
		.andExpect(model().attributeExists("userList"))
		.andExpect(model().attributeExists("pageVo"))
		.andDo(print());
	}
	
	@Test
	public void userDetailTest() throws Exception{
		mockMvc.perform(post("/user/userDetail").param("userid", "sally"))
		.andExpect(view().name("user/user"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("user"))
		.andDo(print());
	}
	
	@Test
	public void userModifyTest() throws Exception{
		mockMvc.perform(post("/user/userModify").param("userid", "sally"))
		.andExpect(view().name("user/userModify"))
		.andExpect(model().attributeExists("uservo"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void userModifySubmitTest() throws Exception{
		mockMvc.perform(post("/user/userModifySubmit").param("page", "test"))
		.andExpect(view().name("user/userModifySubmit"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void userRegistTest() throws Exception{
		mockMvc.perform(post("/user/userRegist"))
		.andExpect(view().name("user/registUser"))
		.andExpect(status().isOk())
		.andDo(print());
	}	
	
	@Test
	public void userRegistsubmitTest() throws Exception{
		mockMvc.perform(post("/user/userRegistsubmit"))
		.andExpect(view().name("user/pagingUser"))
		.andExpect(status().isOk())
		.andDo(print());
	}	
	
	@Test
	public void userDeleteTest() throws Exception{
		mockMvc.perform(post("/user/userDelete").param("userid", "test"))
		.andExpect(view().name("user/pagingUser"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("pagination"))
		.andExpect(model().attributeExists("userList"))
		.andExpect(model().attributeExists("pageVo"))
		.andDo(print());
	}	
}
