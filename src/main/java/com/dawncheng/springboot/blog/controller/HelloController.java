package com.dawncheng.springboot.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//used for testing 
@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World!";
	}
}
