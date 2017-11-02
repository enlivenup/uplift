package com.enlight.yaadle.uplift.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;

import com.enlight.yaadle.uplift.social.SocialConnectionSignup;
import com.enlight.yaadle.uplift.social.SocialSignInAdapter;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

	@Autowired
	OAuth2ClientContext oauth2ClientContext;

	@Autowired
	private YaadleAuthProvider authProvider;

	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	private UsersConnectionRepository usersConnectionRepository;

	@Autowired
	private SocialConnectionSignup socialConnectionSignup;
	
    @SuppressWarnings("unused")
	@Autowired
    private OAuth2ClientContextFilter oauth2ClientContextFilter;
    

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		    http
		    .authorizeRequests()
		        .antMatchers("/", "/home", "/register", "/signin/**", "/signup/**","/login").permitAll()
		        .antMatchers("/auth/**").permitAll().antMatchers("/resources/**", "/images/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin().loginPage("/home").permitAll()
				.defaultSuccessUrl("/landing")
				.failureUrl("/login?error")
				.and()
			.logout().logoutSuccessUrl("/home?logout").permitAll()
			    .and()
			.exceptionHandling().accessDeniedPage("/error");
		    
		//	http.addFilterAfter(oauth2ClientContextFilter, SecurityContextPersistenceFilter.class);
	}

	 @Override
		public void configure(WebSecurity web) throws Exception {
			web
				.ignoring()
					.antMatchers("/**/*.css", "/**/*.png", "/**/*.gif",
							     "/**/*.jpg","/**/*.js","/jquery/**",
							     "/fonts/**","/webjars/**","/**/favicon.ico");
	 }
			
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception, NoSuchAlgorithmException, InvalidKeySpecException {
		auth.authenticationProvider(authProvider);
		logger.info("Authenticated");
	}

	@Bean
	public ProviderSignInController providerSignInController() {
		((InMemoryUsersConnectionRepository) usersConnectionRepository).setConnectionSignUp(socialConnectionSignup);

		return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new SocialSignInAdapter());

	}

}
