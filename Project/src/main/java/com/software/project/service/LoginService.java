package com.software.project.service;

import com.software.project.entities.User;

public interface LoginService {
	User login(String username, String password) throws IllegalArgumentException;
	public User getUser();
}
