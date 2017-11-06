package com.enlight.yaadle.uplift.user;

import java.security.NoSuchAlgorithmException;

import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
//import java.util.HashMap;
//import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.enlight.yaadle.uplift.mail.EmailService;
import com.enlight.yaadle.uplift.security.StrongPassImpl;
import com.enlight.yaadle.uplift.user.User;

@SuppressWarnings("unused")
@Component
public class UserDaoImpl implements UserDao, UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	EmailService msg;
	
	@Autowired 
	UserDetailsService userService;
	
	 
	// <!-THIS SEGMENT IS FOR REGISTRATION CONTROLLER->
	public void register(User user) {
		final String CREATE_USER = "INSERT INTO USERS (username, displayname, email, password, id,enabled,provider) VALUES (?, ?, ?, ? , ?, ?, ?)";
		final String CREATE_USER_ROLE = "INSERT INTO USERS (username, role) VALUES (?, ?)";
		
		UUID uuid = UUID.randomUUID();String randomUUIDString = uuid.toString();String id = randomUUIDString;

		try {
			String hashPass = StrongPassImpl.generateHash(user.getPassword());
			int status = jdbcTemplate.update(CREATE_USER, user.getEmail(), user.getUsername(), user.getEmail(), hashPass, id, false,"LOCAL");
			int status2 = jdbcTemplate.update(CREATE_USER_ROLE, user.getEmail(),"ROLE_USER");
			logger.info(
					"User Updated status: " + status + "\nDisplay UserName: " + user.getUsername() + "\nDisplay Email: "
							+ user.getEmail() + "\nDisplay Password: " + user.getPassword() + "\nDisplay Id:" + id);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		String confirmMessage="Hello\n"+ user.getUsername() + "you have successfully registered to yaadle. Please click the given link to confirm registration.\n"+
				"http://localhost/confirmregistration?token="+id;
		logger.info(confirmMessage);
		msg.sendMail(user.getEmail(), "Yaadle User Registration !", confirmMessage);
	}

	// <!-THIS SEGMENT IS FOR AUTHENTICATION PROVIDER->

	public User findUserByEmail(User principal) {

		String textPass = principal.getPassword();
		
		String sql1 = "SELECT PASSWORD FROM USERS WHERE USERNAME= ?";
		String sql2 = "SELECT EMAIL FROM USERS WHERE USERNAME= ?";
		
		String pwd  = jdbcTemplate.queryForObject(sql1, String.class, principal.getUsername());
		String email  = jdbcTemplate.queryForObject(sql2, String.class, principal.getUsername());
		if(email.equals(null))
		{
			HttpServletResponse response;
			logger.info("User info not found findUserByEmail(null)");
			principal = null;
			return principal;
		}
	
		if(email.equals(principal.getUsername()))
		{
		logger.info("User info found findUserByEmail("+ email+")");
		try {
			boolean verify = StrongPassImpl.validatehash(textPass, pwd);
			logger.info("findUserByEmail Password Match:=" + verify);
			if(verify == true)
			{
				principal.setPassword(pwd);
				principal.setUsername(email);
				
			}
			else 
			{
				principal.setUsername(email);
				principal.setPassword("NOT_MATCHED");
				return principal;
			}
 		    }
		catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
 		  }
		}
		else {
			return null;
		}
		return principal;
	}

	// <!-THIS SEGMENT IS FOR EMAIL VALIDATION->
	public String validateToken(User login) {
		logger.info("Inside validate");
		final String sql = "select id from users where id=?";
		String checkToken  = jdbcTemplate.queryForObject(sql, String.class, login.getToken());
		logger.info("Check Completed for token " + checkToken);
		if(checkToken.equals(login.getToken()))
		{
			logger.info("Token matched " + checkToken);
			final String STATUS_ENABLE = "UPDATE USERS SET enabled=1 WHERE ID='"+checkToken+"'";
			int status = jdbcTemplate.update(STATUS_ENABLE);
			logger.info("User Activated " + status);
		}
		else {
		    logger.info("Email validation not successful");
		    return "Please generate the token again";
		}
		return "Token Verified";
      }
	
	public User validateEmail(User login) {
		final String sql = "select * from users where email= ?";
		List<User> users = jdbcTemplate.query(sql, new UserMapper(), login.getUsername());
		logger.info("Email validation completed successfully");
		return users != null && users.size() > 0 ? users.get(0) : null;
      }

	class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int arg1) throws SQLException {
			User user = new User();
			user.setPassword(rs.getString("password"));
			user.setDisplayname(rs.getString("displayname"));
			user.setEmail(rs.getString("email"));
			user.setUsername(rs.getString("username"));
			user.setStatus(rs.getString("enabled"));
			return user;
		}
	}
	 
	// <!-THIS SEGMENT IS FOR SAVING SOCIAL PROVIDER->
	
	public String save(User user) {
		
		final String CREATE_USER = "INSERT INTO USERS (username, displayname, email, password, id,enabled,provider) VALUES (?, ?, ?, ? , ?, ?,?)";
		final String SOCIAL_USER = "INSERT INTO USERS_SOCIAL (username,imageurl,profileurl,displayname,skey) VALUES (?, ?, ?, ? , ?)";
		final String SOCIAL_TOKEN ="";
		
		UUID uuid = UUID.randomUUID();String randomUUIDString = uuid.toString();String id = randomUUIDString;

		try {
			String hashPass = StrongPassImpl.generateHash(user.getPassword());
			int status = jdbcTemplate.update(CREATE_USER, user.getEmail(), user.getUsername(), user.getEmail(), hashPass, id, true,"SOCIAL");
			logger.info(   "User Updated status: " + status + "\nDisplay UserName: " + user.getUsername() + "\nDisplay Email: "
							+ user.getEmail() + "\nDisplay Password: " + user.getPassword() + "\nDisplay Id:" + id);

			int social = jdbcTemplate.update(SOCIAL_USER, user.getEmail(), user.getimageUrl(), user.getProfileurl(),user.getUsername(),user.getKey());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "No Such Algorithm";
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			return "InvalidKeySpecException";
		}
		return "User Saved into database";
	}
	
	// -------------FOR USERDETAILS TOKEN-------------------------
	
	public User loadUserByUsername(User login) {
		final String sql = "select * from users where email= ?";
		List<User> users = jdbcTemplate.query(sql, new UserMapper(), login.getUsername());
		logger.info("loadUserByUsername user object");
		return users != null && users.size() > 0 ? users.get(0) : null;
	}
	
	
	// -------------FOR USERDETAILS TOKEN-------------------------
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final String sql = "select u.username username, u.password password, ur.role role from users u, user_roles ur where u.username = ? and u.username = ur.username";
	    List<User> udetails = jdbcTemplate.query(sql, new UserMap(), username);
	    
	    logger.info("Print List: " + udetails.toString());
	    String uname = (String) udetails.get(0).getEmail();
	    String pass = (String)udetails.get(0).getPassword();
	    String role = (String)udetails.get(0).getRole();
        logger.info("Info 1 >"+uname+" Info 2 >" + pass + " Info 3 >"+role);
        List<GrantedAuthority> authority=new ArrayList<GrantedAuthority>();
        //GrantedAuthority authority1 = new SimpleGrantedAuthority(role);
        UserDetails rudi = new User(uname,pass,true,true,true,true,authority);
		return rudi; 
     }

	
	class UserMap implements RowMapper<User> {
		public User mapRow(ResultSet rs, int arg1) throws SQLException {
			User user = new User();
			user.setPassword(rs.getString("password"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("username"));
			user.setRole(rs.getString("role"));
			return user;
		}
	}
	
	@Override
	public User findByEmail(String email) {
		String sql2 = "SELECT EMAIL FROM USERS WHERE USERNAME= ?";
		String emailReturn  = jdbcTemplate.queryForObject(sql2, String.class, email);
		User principal=new User();
		principal.setEmail(emailReturn);
		return principal;
	}


	@Override
	public UserDetails getUser(String username) {
		return null;
	}

//----------------------------------------------------------------------------
	
	
}

