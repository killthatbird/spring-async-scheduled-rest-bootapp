package com.ailhanli.spring.example.service;

import java.util.List;

import com.ailhanli.spring.example.domain.User;

public interface UserService {

    User save(User user);

    List<User> getList();

}
