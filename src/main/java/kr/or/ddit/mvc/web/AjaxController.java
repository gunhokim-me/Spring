package kr.or.ddit.mvc.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@RequestMapping("ajax")
@Controller
public class AjaxController {
	
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
