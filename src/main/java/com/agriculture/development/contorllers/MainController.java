package com.agriculture.development.contorllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String homePage() {
		System.out.println("Showing home page ..");
		return "HomePage";
	}
	
	@RequestMapping("/aboutUs")
	public String aboutUs() {
		System.out.println("Showing home page ..");
		return "aboutUs";
	}
}
