package com.test.service;

import com.test.dto.AuthDTO;
import com.test.model.UserEntity;

public interface IAuthenticatedUserService {
	
	UserEntity getCurrentUser();
	

}
