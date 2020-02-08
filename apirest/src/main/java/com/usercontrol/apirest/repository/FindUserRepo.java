package com.usercontrol.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usercontrol.apirest.models.User;

public interface FindUserRepo extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
