package com.enlight.yaadle.uplift.user;

import java.security.NoSuchAlgorithmException;

import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
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
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.enlight.yaadle.uplift.security.StrongPassImpl;
import com.enlight.yaadle.uplift.user.User;

@SuppressWarnings("unused")
@Component
public class UserDaoImpl implements UserDao, UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	public JdbcTemplate jdbcTemplate;
    
	 
	// <!-THIS SEGMENT IS FOR REGISTRATION CONTROLLER->
	public void register(User user) {
		final String CREATE_USER = "INSERT INTO USERS (username, displayname, email, password, id,enabled) VALUES (?, ?, ?, ? , ?, ?)";

		UUID uuid = UUID.randomUUID();String randomUUIDString = uuid.toString();String id = randomUUIDString;

		try {
			String hashPass = StrongPassImpl.generateHash(user.getPassword());
			int status = jdbcTemplate.update(CREATE_USER, user.getEmail(), user.getUsername(), user.getEmail(), hashPass, id, false);
			logger.info(
					"User Updated status: " + status + "\nDisplay UserName: " + user.getUsername() + "\nDisplay Email: "
							+ user.getEmail() + "\nDisplay Password: " + user.getPassword() + "\nDisplay Id:" + id);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
	}

	// <!-THIS SEGMENT IS FOR AUTHENTICATION PROVIDER->

	public User findUserByEmail(User principal) {

		String textPass = principal.getPassword();
		
		String sql1 = "SELECT PASSWORD FROM USERS WHERE USERNAME= ?";
		String sql2 = "SELECT EMAIL FROM USERS WHERE USERNAME= ?";
		
		String pwd  = jdbcTemplate.queryForObject(sql1, String.class, principal.getUsername());
		String email  = jdbcTemplate.queryForObject(sql2, String.class, principal.getUsername());
		logger.info("findUserByEmail completed successfully");
		principal.setPassword(pwd);
		principal.setUsername(email);
		return principal;
	}

	public User validateEmail(User login) {
		final String sql = "select * from users where email= ?";
		List<User> users = jdbcTemplate.query(sql, new UserMapper(), login.getUsername());
		logger.info("validateEmail completed successfully");
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
	
	@Override
	public void save(User user) {
		final String CREATE_USER = "INSERT INTO USERS (username, displayname, email, password, id,enabled) VALUES (?, ?, ?, ? , ?, ?)";

		UUID uuid = UUID.randomUUID();String randomUUIDString = uuid.toString();String id = randomUUIDString;

		try {
			String hashPass = StrongPassImpl.generateHash(user.getPassword());
			int status = jdbcTemplate.update(CREATE_USER, user.getEmail(), user.getUsername(), user.getEmail(), hashPass, id, true);
			logger.info(
					"User Updated status: " + status + "\nDisplay UserName: " + user.getUsername() + "\nDisplay Email: "
							+ user.getEmail() + "\nDisplay Password: " + user.getPassword() + "\nDisplay Id:" + id);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public User loadUserByUsername(User login) {
		return null;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}


	@Override
	public User findByEmail(String email) {
	return null;
	}

	@Override
	public void saveRegisteredUser(User user) {
	}


}
