package com.enlight.yaadle.uplift.security;

public interface StrongPassDao {
	
	String	generateHash(String password);
	boolean validateHash(String originalPassword, String storedPassword);
}
