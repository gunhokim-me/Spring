package kr.or.ddit.mvc.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("file")
@Controller
public class FileDownloadController {

	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("/fileDownloadView")
	public String fileDownloadView(String userid, Model model) {
		//1. 다운로드의 파일 경로 => realFilename
		//2. 다운로드시 보여줄 파일명 => filename
		//3. 1,2번을 구해서 model에 넣어준다
		//userid 파라미터를 보낸다고 가정
		//파라미터를 이용해서 해당 사용자의 사진정보(realFilename, filename)를 조회하여 처리
		
		UserVo userVo = userService.selectUser(userid);
		model.addAttribute("realFilename" , userVo.getRealfilename());
		model.addAttribute("filename" , userVo.getFilename());
		
		return "fd";
	}
	
	@RequestMapping("fileDownload")
	public void fileDownload(HttpServletResponse response, String userid) throws Exception {
		UserVo userVo = userService.selectUser(userid);
		
		String filename = userVo.getFilename();
		response.setHeader("Content-Disposition", "attachement; filename=" + filename);
		
		//d:/upload/sally.png
		String realFilename = userVo.getRealfilename();
		
		ServletOutputStream sos = response.getOutputStream();
		
		FileInputStream fis = new FileInputStream(new File(realFilename));
		byte[] buf = new byte[1024];
		while(fis.read(buf) != -1) {
			sos.write(buf);
		}
		sos.close();
		fis.close();
		
	}
}
