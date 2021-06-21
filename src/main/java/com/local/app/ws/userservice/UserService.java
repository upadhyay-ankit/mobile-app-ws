package com.local.app.ws.userservice;

import com.local.app.ws.ui.model.request.UserDetailsRequestModel;
import com.local.app.ws.ui.model.response.UserRest;

public interface UserService {
	UserRest createUser(UserDetailsRequestModel requestModel);

}
