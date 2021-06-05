package com.local.app.ws.ui.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {

	// http://localhost:8080/users/?page=1&limit=50
	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
		return "get users page = "+page+" and limit = "+limit+" and sort = "+sort;
	}
	
	// http://localhost:8080/users/1
	@GetMapping(path = "/{userId}")
	public String getUser(@PathVariable String userId) {
		return "get user = " + userId;
	}
	
	@PostMapping
	public String createUser() {
		return "user created";
	}

	@PutMapping
	public String updateUser() {
		return "Updated user";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "deleted user";
	}
	
}
