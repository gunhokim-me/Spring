package kr.or.ddit.user.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(path="allUser")
	public String userall(Model model) {
		
		List<UserVo> userList = userService.selectAllUser();
		model.addAttribute("userList", userList);
		return "user/allUser";
	}
	
	@RequestMapping("pagingUser")
	public String pagingUser(@RequestParam(defaultValue = "1") int page, 
							 @RequestParam(defaultValue = "5") int pageSize, Model model) {
		PageVo pageVo = new PageVo(page, pageSize); 
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		return "user/pagingUser";
	}
	
	//@RequestMapping("pagingUser")
	public String pagingUser(PageVo pageVo, Model model) {
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		return "user/pagingUser";
	}
	
	@RequestMapping("userDetail")
	public String userDetail(String userid, Model model) {
		model.addAttribute("user",userService.selectUser(userid));
		return "user/user";
	}
	
	@RequestMapping("userModify")
	public String userModify(String userid, Model model) {
		model.addAttribute("uservo",userService.selectUser(userid));
		return "user/userModify";
	}
	
	@RequestMapping(path="userModifySubmit", method = {RequestMethod.POST})
	public String userModifySubmit(UserVo userVo, MultipartFile profile, Model model, RedirectAttributes ar) {
		logger.debug("userVo : {} ",userVo);
		logger.debug("profile : {} ",profile.getOriginalFilename());
		
		if(profile.getSize() > 0) {
			try {
				profile.transferTo(new File("D:/upload/" + profile.getOriginalFilename()));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		int cnt = userService.modifyUser(userVo);
		if(cnt == 1) {
			ar.addAttribute("userid",userVo.getUserid());
			return "redirect:/user/userDetail";
		}else {
			ar.addAttribute("userid",userVo.getUserid());
			return "redirect:/user/userModify";
		}
	}
	
	@RequestMapping("userDelete")
	public String userDelete(String userid,  Model model, RedirectAttributes ar) {
		int cnt = userService.deleteUser(userid);
		
		if(cnt == 1) {
			return "redirect:/user/pagingUser";
		}else {
			ar.addAttribute("userid",userid);
			return "redirect:/user/userDelete";
		}
	}
	
	
	
	@RequestMapping(path="userRegist", method = {RequestMethod.GET})
	public String userRegistGet() {
		return "user/registUser";
	}
	
	@RequestMapping(path="userRegistsubmit",  method = {RequestMethod.POST})
	public String userRegist(UserVo userVo, MultipartFile profile, RedirectAttributes ar) {
		logger.debug("profile : {} ",profile);
		String realfilename = UUID.randomUUID().toString() + ".png";
		userVo.setRealfilename(realfilename);
		userVo.setFilename(profile.getOriginalFilename());
		int cnt = userService.registUser(userVo);
		if(cnt == 1) {
			return "redirect:/user/pagingUser";
		}else {
			ar.addFlashAttribute("vo2",userVo);
			return "redirect:/user/userRegist";
		}
	}
	
	
}
