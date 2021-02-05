package kr.or.ddit.mvc.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.or.ddit.user.model.UserVo;

@RequestMapping("ajax")
@Controller
public class AjaxController {
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@ModelAttribute(name="rangers")
	public List<String> rangers(){
		List<String> rangers = new ArrayList<>();
		rangers.add("brown");
		rangers.add("sally");
		rangers.add("moon");
		rangers.add("james");
		rangers.add("cony");
		
		return rangers;
	}
	
	@RequestMapping("view")
	public String view() {
		return "ajax/ajaxView";
	}
	
	@RequestMapping("form")
	public String form(UserVo userVo) {
		logger.debug("userVo : {}", userVo);
		return "jsonView";
	}
	
	@RequestMapping("jsonView")
	public String jsonView() {
		return "jsonView";
	}
	
	@RequestMapping("jsonViewViewObj")
	public View jsonViewViewObj() {
		return new MappingJackson2JsonView();
	}
	
	//과거 스타일
	@RequestMapping("jsonViewMav")
	public ModelAndView jsonViewMav() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jsonView");
		return mav;
	}
}
