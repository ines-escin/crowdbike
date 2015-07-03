package com.software.project.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.software.project.entities.User;

@Component
@Scope("session")
public class UserSession {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean isLoggedIn(){
		return user != null;
	}
}
