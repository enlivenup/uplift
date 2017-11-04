package com.enlight.yaadle.uplift.user;

import org.springframework.security.core.userdetails.UserDetails;

import com.enlight.yaadle.uplift.user.User;

public interface UserDao {

	  void register(User user);
	  public User findUserByEmail(User user);
	  public User loadUserByUsername(User login);
	  public User validateEmail(User login);
	  User findByEmail(String email);
	  String save(User user);
	  public String validateToken(User login);
	  public UserDetails getUser(String username);
	}