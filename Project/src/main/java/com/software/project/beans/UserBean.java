package com.software.project.beans;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.software.project.entities.User;
import com.software.project.service.LoginService;

@Controller("userbean")
@Scope("view")
public class UserBean {

	private User user;
	private String nome;
	
	
	@Resource
	private LoginService loginservice;
	
	@PostConstruct
	public void getInit(){
		this.user = loginservice.getUser();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNome() {
		return user.getUsername();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
