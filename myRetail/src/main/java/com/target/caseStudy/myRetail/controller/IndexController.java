package com.target.caseStudy.myRetail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value = {"/","/home"})
	public String homePage(){
		System.out.println("egeherh");
		return "index.html";
	}
}
