package com.software.project.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.software.project.entities.User;

@Repository("UserDAO")
public class UserDAOImpl extends GenericDAOImp<User, Long> implements UserDAO, Serializable {
	
	@SuppressWarnings("rawtypes")
	public User login(String username, String password) {
		String query = "FROM User u where u.username = ? and u.password=?";
		Query q = getEntityManager().createQuery(query);
		q.setParameter(1, username);
		q.setParameter(2, password);

		List l = q.getResultList();

		if (l == null || l.isEmpty() || l.size() > 1) {
			return null;
		}
		return (User) l.get(0);
	}
}
