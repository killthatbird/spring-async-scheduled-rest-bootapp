
package com.ailhanli.spring.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ailhanli.spring.example.domain.User;
import com.ailhanli.spring.example.service.UserService;
import com.ailhanli.spring.example.service.exception.UserAlreadyExistsException;

@RestController
public class UserController {

   private final UserService userService;

   @Inject
   public UserController(final UserService userService) {
      this.userService = userService;
   }

   @RequestMapping(value = "/user", method = RequestMethod.POST)
   public User createUser(@RequestBody
   @Valid
   final User user) {
      System.out.println("Received request to create the " + user);
      return userService.save(user);
   }

   @RequestMapping(value = "/user", method = RequestMethod.GET)
   public List<User> listUsers() {
      System.out.println("Thread "+Thread.currentThread().getName());
      System.out.println("Received request to list all users");
      userService.getList();
      return new ArrayList<>();
   }

   @ExceptionHandler
   @ResponseStatus(HttpStatus.CONFLICT)
   public String handleUserAlreadyExistsException(UserAlreadyExistsException e) {
      return e.getMessage();
   }

}
