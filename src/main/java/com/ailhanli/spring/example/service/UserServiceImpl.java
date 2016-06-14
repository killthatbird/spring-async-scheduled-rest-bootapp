
package com.ailhanli.spring.example.service;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.ailhanli.spring.example.domain.User;
import com.ailhanli.spring.example.repository.UserRepository;
import com.ailhanli.spring.example.service.exception.UserAlreadyExistsException;

@Service
@Validated
public class UserServiceImpl implements UserService {

   private final UserRepository repository;

   @Inject
   public UserServiceImpl(final UserRepository repository) {
      this.repository = repository;
   }

   @Override
   @Transactional
   public User save(@NotNull
   @Valid
   final User user) {
      System.out.println("Creating "+ user);
      User existing = repository.findOne(user.getId());
      if (existing != null) {
         throw new UserAlreadyExistsException(String.format("There already exists a user with id=%s", user.getId()));
      }
      return repository.save(user);
   }

   @Async
   @Override
   @Transactional(readOnly = true)
   public List<User> getList() {
      System.out.println("Thread "+Thread.currentThread().getName());

      System.out.println("Retrieving the list of all users");
      try {
         hello();
      }
      catch (InterruptedException e) {
         e.printStackTrace();
      }
      System.out.println("getList finished");
      return repository.findAll();
   }

   public void hello() throws InterruptedException {
      System.out.println("hello started");
      Thread.sleep(10000);
      System.out.println("hello finished");
   }

}
