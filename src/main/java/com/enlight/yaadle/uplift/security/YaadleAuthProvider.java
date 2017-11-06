package com.enlight.yaadle.uplift.security;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.enlight.yaadle.uplift.user.User;
import com.enlight.yaadle.uplift.user.UserDao;
import com.enlight.yaadle.uplift.user.UserDaoImpl;


@SuppressWarnings("unused")
@Component
public class YaadleAuthProvider implements AuthenticationProvider,AuthenticationFailureHandler {
	
	private final Logger logger = LoggerFactory.getLogger(YaadleAuthProvider.class);
	
	  @Autowired  
	  UserDao userDao;
	  
    public YaadleAuthProvider () {}
	  
	@SuppressWarnings("null")
	@Override
    public Authentication authenticate(Authentication authentication)  throws AuthenticationException {
  
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User principal=new User(username,password);
        
        User fetchPrincipal = userDao.findUserByEmail(principal);
       // User fetchJ = userDao.validateEmail(principal);
         if(fetchPrincipal!=null)
         {
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
         }
         else if(fetchPrincipal.getPassword().equals("NOT_MATCHED"))
         {
        	 authentication.setAuthenticated(false);
        	 logger.info("User password not matched >" + fetchPrincipal.getPassword());
        	return null;
         }
               	logger.info("Authentication Failed !");
               	return null;
 	    }

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {

		
	}

}