package com.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.test.model.UserEntity;
import com.test.service.IAuthenticatedUserService;
import com.test.service.IUserService;

@Service
public class AuthenticatedUserServiceImpl implements IAuthenticatedUserService {

	@Autowired
	private IUserService userService;
	  @Override
	    public UserEntity getCurrentUser() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	        if (authentication == null || !authentication.isAuthenticated()) {
	            throw new IllegalStateException("No authenticated user found in security context");
	        }

	        Object principal = authentication.getPrincipal();

	        if (principal instanceof User) {
	            User user= (User) principal;
	            return userService.getUserEntityByEmail(user.getUsername());
	        }

	        throw new IllegalStateException("Principal is not of type UserEntity");
	    }

}
