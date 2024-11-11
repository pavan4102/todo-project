package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.todo.model.User;
import com.example.todo.repo.IUserRepo;

@Service
public class UserService {
	
	@Autowired
	IUserRepo repoUser;

	public boolean saveUser(User user) {
		User newUser = repoUser.save(user);
		if(repoUser.findById(newUser.getUserId()).get() != null) {
			return true;
		}
		return false;
	}
	
	public boolean findUser(User user) {
		User checkUser = repoUser.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if(checkUser == null) {
			return false;
		}
		return true;
	}
	
	public User findByUsername(String username) {
		User user = repoUser.findUserByUsername(username);
		if(user != null) {
			return user;
		}
		return null;
	}
	
	public User findUserById(Long userId) {
		User user = repoUser.findById(userId).get();
		if(user != null) {
			return user;
		}
		return null;
	}
}
