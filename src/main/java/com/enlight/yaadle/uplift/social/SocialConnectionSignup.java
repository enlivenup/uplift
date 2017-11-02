package com.enlight.yaadle.uplift.social;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import com.enlight.yaadle.uplift.user.User;
import com.enlight.yaadle.uplift.user.UserDao;

@Service
public class SocialConnectionSignup implements ConnectionSignUp {
	
	private final Logger logger = LoggerFactory.getLogger(SocialConnectionSignup.class);
    
	@Autowired
    private UserDao userRepository;
 
    @Override
    public String execute(Connection<?> connection) {
        User user = new User();
        user.setUsername(connection.getDisplayName());
        user.setPassword("!QAZ0okm%TGB");
        user.setImageUrl(connection.getImageUrl());
        logger.info("\nProfile Url:="+connection.getProfileUrl()+ 
        		    "\nImage   Url:="+connection.getImageUrl()+
        		    "\nProfile Key:="+connection.getKey()+
        		    "\nDisplay Name:="+ connection.getDisplayName());
        //logger.info("Profile data>"+connection.fetchUserProfile());
        userRepository.save(user);
        return user.getUsername();
    }
}
