package com.rohan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {
	
	@RequestMapping("/hello")
	public ModelAndView helloWorld()
	{
		String message = "Hello. Welcome to my first project on spring MVC";
		return new ModelAndView("HelloPage" , "Message" , message);
		
	}

}
