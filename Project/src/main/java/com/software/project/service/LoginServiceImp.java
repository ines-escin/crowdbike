package com.software.project.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.software.project.dao.UserDAO;
import com.software.project.entities.User;

@Service("LoginService")
@Transactional(propagation=Propagation.REQUIRED)
public class LoginServiceImp implements LoginService {
	
	@Resource
	private UserDAO dao;
	private User u;

	@Override
	public User login(String username, String password)
			throws IllegalArgumentException {
		if (isEmptyOrNull(username) || isEmptyOrNull(password)) {
			throw new IllegalArgumentException(
					"Atenção, username ou password vazios!");
		}
		u = dao.login(username, password);
		
		if (u == null) {
			throw new IllegalArgumentException(
					"Erro: username ou password incorretos!");
		}
		return u;
	}

	private boolean isEmptyOrNull(String s) {
		return s == null || s.equals("");
	}

	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return u;
	}
}
