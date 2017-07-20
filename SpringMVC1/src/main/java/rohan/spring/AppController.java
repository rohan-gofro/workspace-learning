package rohan.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@RequestMapping("/hello")
	public ModelAndView helloWorld() {
		String message = "Hello ! Welcome to Spring MVC";
		return new ModelAndView("hellopage", "message", message);
	}

	@RequestMapping("/abc")
	public String abc(ModelMap map) {
		String message = "Hello ! Welcome to Spring MVC";
		map.addAttribute("message", message);
		return "hellopage";
	}
}
