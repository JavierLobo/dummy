package com.javierlobo.dummy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.javierlobo.dummy.service.IDummyService;
import com.javierlobo.dummy.view.User;

@Service
public class DummyServiceImpl implements IDummyService {

	private List<User> userList = new  ArrayList<User>();
	
	@Override
	public User getUserByName(String userName) {
		if (existUser(userName)) {
			return getUser(userName);
		}
		return new User("", 0, "");
	}

	@Override
	public User createUser(User user) {
		return saveUser(user);
	}

	@Override
	public User modifiUser(User user) {
		return saveUser(user);
	}

	@Override
	public User deleteUser(String userName) {
		User deletedUser = new User();
		
		if (existUser(userName)) {
			deletedUser = getUser(userName);
			userList.remove(deletedUser);
		}
		return deletedUser;
	}

	@Override
	public List<User> getUserList() {
		return userList;
	}
	
// -----------------------------------------------------------------------------
	
	private boolean existUser(String userName) {
		boolean exist = false;
		if (!userList.isEmpty()) {
			for (User user : userList) {
				if (user.getUserName().contentEquals(userName)) {
					exist = true;
				};
			}
		}
		return exist;
	}
	
	private User getUser(String userName) {
		for (User user : userList) {
			if (user.getUserName().contentEquals(userName)) {
				return user;
			}
		}
		
		return new User("", 0, "");
	}
	
	private User saveUser(User user) {
		if (existUser(user.getUserName())) {
			Integer indice = userList.indexOf(getUser(user.getUserName()));
			
			userList.get(indice).setSalary(user.getSalary());
			userList.get(indice).setStatus(user.getStatus());
			userList.get(indice).setUserName(user.getUserName());
			
		} else {
			userList.add(user);	
		}
		
		return user;
	}

}
