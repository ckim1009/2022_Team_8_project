package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {
	
	@RequestMapping(value = "/error")
	public String handleError(HttpServletRequest request) {
		
		return "redirect:/";
	}
	
	
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}

}
