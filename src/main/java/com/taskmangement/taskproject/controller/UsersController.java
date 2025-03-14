package com.taskmangement.taskproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmangement.taskproject.payload.LoginDto;
import com.taskmangement.taskproject.payload.UsersDto;
import com.taskmangement.taskproject.repository.UserRepository;
import com.taskmangement.taskproject.service.UsersService;

@RestController
@RequestMapping("/api/auth")
public class UsersController {
	@Autowired
	public UserRepository usersRepository;
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	// post method to store user in DB
	@PostMapping("/register")
	public ResponseEntity<UsersDto> createUser(@RequestBody UsersDto usersDto) {
		
//		usersService.createUser(usersDto);
		return new ResponseEntity<>(usersService.createUser(usersDto),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<>("User logged in successfully",HttpStatus.OK);
	}

}
