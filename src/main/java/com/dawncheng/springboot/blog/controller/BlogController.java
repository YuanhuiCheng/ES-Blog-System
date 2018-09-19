package com.dawncheng.springboot.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogs")
public class BlogController {

	@GetMapping
	public String listBlogs(@RequestParam(value = "order", required = false, defaultValue = "new") String order,
			@RequestParam(value = "tag", required = false, defaultValue = "") String keyword) {
		System.out.println("order:" + order + ";keyword:" + keyword);
		return "redirect:/index?order=" + order + "&keyword=" + keyword;
	}
}
