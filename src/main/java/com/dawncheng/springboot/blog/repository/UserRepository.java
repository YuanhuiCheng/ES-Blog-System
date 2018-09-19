package com.dawncheng.springboot.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dawncheng.springboot.blog.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Page<User> findByNameLike(String name, Pageable pageable);

	User findByUsername(String username);
}
