package com.local.app.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.local.app.ws.exceptions.UserServiceException;
import com.local.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.local.app.ws.ui.model.request.UserDetailsRequestModel;
import com.local.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {
	
	Map<String, UserRest> users;

	// http://localhost:8080/users/?page=1&limit=50
	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
		return "get users page = "+page+" and limit = "+limit+" and sort = "+sort;
	}
	
	// http://localhost:8080/users/1
	@GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		if (true) {
			throw new UserServiceException("User service exception is thrown");
		}
		if (null != users && users.containsKey(userId)) {
			return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.INSUFFICIENT_STORAGE);
		} else {
			return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		if (users == null) {
			users = new HashMap<>();
		}
		UserRest user = new UserRest();
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(userDetails.getEmail());
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		
		users.put(userId, user);
		return new ResponseEntity<UserRest>(user, HttpStatus.OK);
	}

	@PutMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserRest> updateUser(@PathVariable String userId, @RequestBody UpdateUserDetailsRequestModel userDetails) {		
		if (users.containsKey(userId)) {
			UserRest user = users.get(userId);
			user.setFirstName(userDetails.getFirstName());
			user.setLastName(userDetails.getLastName());
			return new ResponseEntity<UserRest>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		users.remove(userId);
		return ResponseEntity.noContent().build();
	}
	
}
