package com.dawncheng.springboot.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dawncheng.springboot.blog.domain.Authority;
import com.dawncheng.springboot.blog.domain.User;
import com.dawncheng.springboot.blog.service.AuthorityService;
import com.dawncheng.springboot.blog.service.UserService;
import com.dawncheng.springboot.blog.util.ConstraintViolationExceptionHandler;
import com.dawncheng.springboot.blog.vo.Response;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final String USER_MODEL = "userModel";

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	@GetMapping
	public ModelAndView list(@RequestParam(value = "async", required = false) boolean async,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "name", required = false, defaultValue = "") String name, Model model) {
		Page<User> page = userService.listUserByNameLike(name, PageRequest.of(pageIndex, pageSize));

		model.addAttribute("page", page);
		model.addAttribute("userList", page.getContent());
		return new ModelAndView(async ? "users/list :: #mainContainerRepleace" : "users/list", USER_MODEL, model);
	}

	@GetMapping("/add")
	public ModelAndView createForm(Model model) {
		model.addAttribute("user", new User(null, null, null, null));
		return new ModelAndView("users/add", USER_MODEL, model);
	}

	@PostMapping
	public ResponseEntity<Response> saveOrUpdateUser(User user, Long authorityId) {
		List<Authority> authorities = new ArrayList<>();
		authorities.add(authorityService.getAuthorityById(authorityId));
		user.setAuthorities(authorities);

		try {
			userService.saveOrUpdateUser(user);
		} catch (ConstraintViolationException e) {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		return ResponseEntity.ok().body(new Response(true, "Successfully saving or updating users", user));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response> delete(@PathVariable("id") Long id, Model model) {
		try {
			userService.removeUser(id);
		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "Successfully delete the user with id, " + id));
	}

	@GetMapping(value = "edit/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return new ModelAndView("users/edit", USER_MODEL, model);
	}
}
