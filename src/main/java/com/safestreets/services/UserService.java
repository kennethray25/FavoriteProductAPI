package com.safestreets.services;

import com.safestreets.model.User;
import com.safestreets.model.repository.UserRepository;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

/**
 * UserService
 * Created By: dylanthompson
 * Created On: 12/28/23
 **/
@Singleton
@Slf4j
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createNewUser(String name, String email, String role) {
    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setRole(role);

    return userRepository.save(user);
  }

  public User getUser(long id) {
    var user = userRepository.findById(id);
    return user.orElse(null);
  }

  public boolean userExists(long id)  {
    return userRepository.existsById(id);
  }

  public User updateUser(User user, Long id) {
    user.setId(id);
    return userRepository.update(user);
  }

  public void deleteUser(long id) {
    userRepository.deleteById(id);
  }

}
