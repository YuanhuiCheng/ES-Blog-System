package com.dawncheng.springboot.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dawncheng.springboot.blog.domain.User;

public interface UserService {

	User saveOrUpdateUser(User user);

	User registerUser(User user);

	void removeUser(Long id);

	User getUserById(Long id);

	Page<User> listUserByNameLike(String name, Pageable pageable);
}
