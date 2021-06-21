package com.local.app.ws.userservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.app.ws.shared.Utils;
import com.local.app.ws.ui.model.request.UserDetailsRequestModel;
import com.local.app.ws.ui.model.response.UserRest;
import com.local.app.ws.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	Utils utils;
	
	public UserServiceImpl() {}

	@Autowired
	public UserServiceImpl(Utils utils) {
		this.utils = utils;
	}
	
	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		UserRest returnUser = new UserRest();
		returnUser.setFirstName(userDetails.getFirstName());
		returnUser.setLastName(userDetails.getLastName());
		returnUser.setEmail(userDetails.getEmail());
		String userId = utils.generateUserId();
		returnUser.setUserId(userId);
		return returnUser;
	}

}
