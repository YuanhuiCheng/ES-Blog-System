package com.dawncheng.springboot.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawncheng.springboot.blog.domain.Authority;
import com.dawncheng.springboot.blog.domain.AuthorityRepository;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public Authority getAuthorityById(Long id) {
		return authorityRepository.findById(id).get();
	}

}
