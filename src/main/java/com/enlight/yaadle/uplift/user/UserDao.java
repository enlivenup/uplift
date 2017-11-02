package com.enlight.yaadle.uplift.user;

import com.enlight.yaadle.uplift.user.User;

public interface UserDao {

	  void register(User user);
	  public User findUserByEmail(User user);
	  public User loadUserByUsername(User login);
	  public User validateEmail(User login);
	  User findByEmail(String email);
	  void save(User user);
	  void saveRegisteredUser(User user);

	}