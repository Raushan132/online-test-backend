package com.test.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.test.model.UserEntity;
import com.test.service.IAuthenticatedUserService;

@Service
public class AuthenticatedUserServiceImpl implements IAuthenticatedUserService {

	  @Override
	    public UserEntity getCurrentUser() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	        if (authentication == null || !authentication.isAuthenticated()) {
	            throw new IllegalStateException("No authenticated user found in security context");
	        }

	        Object principal = authentication.getPrincipal();

	        if (principal instanceof UserEntity) {
	            return (UserEntity) principal;
	        }

	        throw new IllegalStateException("Principal is not of type UserEntity");
	    }

}
