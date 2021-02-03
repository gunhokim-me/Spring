package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.validator.UserVoValidator;

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
	
	@RequestMapping(path="allUserTiles")
	public String allUserTiles(Model model) {
		
		List<UserVo> userList = userService.selectAllUser();
		model.addAttribute("userList", userList);
		return "tiles.user.allUser";
	}
	
	@RequestMapping("pagingUser")
	public String pagingUser(@RequestParam(defaultValue = "1") int page, 
							 @RequestParam(defaultValue = "5") int pageSize, Model model) {
		PageVo pageVo = new PageVo(page, pageSize); 
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		return "user/pagingUser";
	}
	
	@RequestMapping("pagingUserTiles")
	public String pagingUserTiles(@RequestParam(defaultValue = "1") int page, 
								  @RequestParam(defaultValue = "5") int pageSize, Model model) {
		PageVo pageVo = new PageVo(page, pageSize); 
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		
		//tiles-definition에서 설정한 name
		return "tiles.user.pagingUser";
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
	
	@RequestMapping("userDetailTiles")
	public String userDetaiTilesl(String userid, Model model) {
		model.addAttribute("user",userService.selectUser(userid));
		return "tiles.user.user";
	}
	
	@RequestMapping("userModify")
	public String userModify(String userid, Model model) {
		model.addAttribute("uservo",userService.selectUser(userid));
		return "user/userModify";
	}
	
	@RequestMapping("userModifyTiles")
	public String userModifyTiles(String userid, Model model) {
		model.addAttribute("uservo",userService.selectUser(userid));
		return "tiles.user.userModify";
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
	
	@RequestMapping(path="userModifySubmitTiles", method = {RequestMethod.POST})
	public String userModifySubmitTiles(UserVo userVo, MultipartFile profile, Model model, RedirectAttributes ar) {
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
			return "redirect:/user/userDetailTiles";
		}else {
			ar.addAttribute("userid",userVo.getUserid());
			return "redirect:/user/userModifyTiles";
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
	
	@RequestMapping("userDeleteTiles")
	public String userDeleteTiles(String userid,  Model model, RedirectAttributes ar) {
		int cnt = userService.deleteUser(userid);
		
		if(cnt == 1) {
			return "redirect:/user/pagingUserTiles";
		}else {
			ar.addAttribute("userid",userid);
			return "redirect:/user/userDeleteTiles";
		}
	}
	
	
	
	@RequestMapping(path="userRegist", method = {RequestMethod.GET})
	public String userRegistGet() {
		return "user/registUser";
	}
	
	@RequestMapping(path="userRegistTiles", method = {RequestMethod.GET})
	public String userRegistGetTiles() {
		return "tiles.user.registUser";
	}
	
	
	//bindgingResult 객체는 command 객체 바로 뒤에 인자로 기술해야한다
	@RequestMapping(path="userRegistsubmit",  method = {RequestMethod.POST})
	public String userRegist(@Valid UserVo userVo, BindingResult result, MultipartFile profile, RedirectAttributes ar) {
		
		//new UserVoValidator().validate(userVo, result);
		
		if(result.hasErrors()) {
			logger.debug("result has error");
			return "user/registUser";
		}
		
		if(profile != null) {
			String realfilename = UUID.randomUUID().toString() + "." + profile.getOriginalFilename().substring(profile.getOriginalFilename().lastIndexOf(".")+1);
			userVo.setRealfilename(realfilename);
			userVo.setFilename(profile.getOriginalFilename());
		}
		int cnt = userService.registUser(userVo);
		if(cnt == 1) {
			return "redirect:/user/pagingUser";
		}else {
			ar.addFlashAttribute("vo2",userVo);
			return "redirect:/user/userRegist";
		}
	}
	
	@RequestMapping(path="userRegistsubmitTiles",  method = {RequestMethod.POST})
	public String userRegistTiles(@Valid UserVo userVo, BindingResult result, MultipartFile profile, RedirectAttributes ar) {
		
		//new UserVoValidator().validate(userVo, result);
		
		if(result.hasErrors()) {
			logger.debug("result has error");
			return "tiles.user.registUserTiles";
		}
		
		if(profile != null) {
			String realfilename = UUID.randomUUID().toString() + "." + profile.getOriginalFilename().substring(profile.getOriginalFilename().lastIndexOf(".")+1);
			userVo.setRealfilename(realfilename);
			userVo.setFilename(profile.getOriginalFilename());
		}
		int cnt = userService.registUser(userVo);
		if(cnt == 1) {
			return "redirect:/user/pagingUser";
		}else {
			ar.addFlashAttribute("vo2",userVo);
			return "redirect:/user/userRegist";
		}
	}
	
	@RequestMapping("profile")
	public void profile(HttpServletResponse resp, String userid, HttpServletRequest req) {
		resp.setContentType("image");
		
		UserVo vo = userService.selectUser(userid);
		String path ="";
		
		if(vo.getRealfilename() == null) {
			path = req.getServletContext().getRealPath("/image/unknown.jpg");
		}else {
			
			path = vo.getRealfilename();
			logger.debug("path : {}",path);
		}
		
		try {
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			
			byte[] buff = new byte[512];
			
			while(fis.read(buff) != -1 ) {
				sos.write(buff);
			}
			
			fis.close();
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("profileDownload")
	public void profileDownload(String userid, HttpServletResponse response) {
		UserVo userVo = userService.selectUser(userid);
		
		String filename = userVo.getFilename();
		response.setHeader("Content-Disposition", "attachement; filename=" + filename);
		
		//d:/upload/sally.png
		String realFilename = userVo.getRealfilename();
		
		try {
			ServletOutputStream sos = response.getOutputStream();
			
			FileInputStream fis = new FileInputStream(new File(realFilename));
			byte[] buf = new byte[1024];
			while(fis.read(buf) != -1) {
				sos.write(buf);
			}
			sos.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("excleDownload")
	public String excleDownload(Model model) {
		List<String> header = new ArrayList<>();
		header.add("사용자 아이디");
		header.add("사용자 이름");
		header.add("사용자 별명");
		
		model.addAttribute("header", header);
		model.addAttribute("data", userService.selectAllUser());
		
		return "userExcelDownloadView";
	}
	
}
