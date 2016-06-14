package com.ailhanli.spring.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ailhanli.spring.example.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
}
