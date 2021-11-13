package com.javierlobo.dummy.service;

import java.util.List;

import com.javierlobo.dummy.view.User;

public interface IDummyService {

	User getUserByName(String userName);

	User createUser(User user);

	User modifiUser(User user);

	User deleteUser(String userName) throws Exception;

	List<User> getUserList();

}
