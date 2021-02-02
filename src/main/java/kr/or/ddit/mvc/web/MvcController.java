package kr.or.ddit.mvc.web;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.user.model.UsersVo;
import kr.or.ddit.user.model.UsersVo_;

@RequestMapping("mvc")
@Controller
public class MvcController {
	
	private static final Logger logger = LoggerFactory.getLogger(MvcController.class);
	
	@RequestMapping("fileupload/view")
	public String fileuploadView() {
		return "file/view";
	}

	
	//파라미터는 userid, picture
	@RequestMapping("fileupload/upload")
	public String fileupload(String userid, MultipartFile picture) {
		logger.debug("userid : {}", userid);
		logger.debug("filesize : {}, name : {}, originalFilename : {}", picture.getSize(), picture.getName(), picture.getOriginalFilename());
		
		try {
			picture.transferTo(new File("D:/upload/" + picture.getOriginalFilename()));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return "file/view";
	}
	
	@RequestMapping("multi/view")
	public String multiView() {
		return "multi/view";
	}
	
//	@RequestMapping("multi/param")
//	public String multiparam(String[] userid) {
//		logger.debug("userid : {}" , (Object)userid);
//		return "multi/view";
//	}
	
	@RequestMapping("multi/param")
	public String multiparam(UsersVo usersVo) {
		logger.debug("usersVo : {}" , usersVo);
		return "multi/view";
	}
}