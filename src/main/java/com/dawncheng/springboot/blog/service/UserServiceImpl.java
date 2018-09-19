package com.dawncheng.springboot.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dawncheng.springboot.blog.domain.User;
import com.dawncheng.springboot.blog.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	@Override
	public User saveOrUpdateUser(User user) {
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public User registerUser(User user) {
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public void removeUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public Page<User> listUserByNameLike(String name, Pageable pageable) {
		name = "%" + name + "%";
		return userRepository.findByNameLike(name, pageable);
	}
}
