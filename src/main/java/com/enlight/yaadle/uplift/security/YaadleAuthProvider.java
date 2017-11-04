package com.enlight.yaadle.uplift.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.enlight.yaadle.uplift.user.User;
import com.enlight.yaadle.uplift.user.UserDao;
import com.enlight.yaadle.uplift.user.UserDaoImpl;


@SuppressWarnings("unused")
@Component
public class YaadleAuthProvider implements AuthenticationProvider {
	
	private final Logger logger = LoggerFactory.getLogger(YaadleAuthProvider.class);
	
	  @Autowired  
	  UserDao userDao;
	  
  public YaadleAuthProvider () {}
	  
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
  
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User principal=new User(username,password);
        
        User fetchPrincipal = userDao.findUserByEmail(principal);
        User fetchJ = userDao.validateEmail(principal);

        if(username.equals(fetchPrincipal.getUsername())){
        	logger.info("User matched > " + username + " " + fetchPrincipal);
        	try {
    			String hashPass = fetchPrincipal.getPassword();
    			logger.info("fetchpass > '"+hashPass+"' & '" + password +"'");
    			boolean matchPass = StrongPassImpl.validatehash(password, hashPass);
    			logger.info("\n Match User password > " + matchPass);
                   if(matchPass == true) {
                                logger.info("Succesful authentication!");
    			                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
                   }
                   else {
                        return null;
                   }
    			
    		     } 
        	  catch (NoSuchAlgorithmException | InvalidKeySpecException e) {e.printStackTrace();}
        	
           }
          else {
        	    logger.info("User not matched >" + username + " " + principal.getUsername());
               }
               	logger.info("Authentication Failed !");
               	return null;
 	    }

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}