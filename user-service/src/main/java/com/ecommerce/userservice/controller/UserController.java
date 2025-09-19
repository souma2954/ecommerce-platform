package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.repository.UserRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	
    private final UserRepository repo;
    
    public UserController(UserRepository repo) {
		super();
		this.repo = repo;
	}
	@GetMapping
    public List<User> allUsers() {
        return repo.findAll();
    }
}
