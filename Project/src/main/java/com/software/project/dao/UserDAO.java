package com.software.project.dao;

import com.software.project.entities.User;

public interface UserDAO extends GenericDAO<User, Long>{
	User login(String username, String password);
}
