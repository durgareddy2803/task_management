package com.taskmangement.taskproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taskmangement.taskproject.entity.Users;
import com.taskmangement.taskproject.payload.UsersDto;
import com.taskmangement.taskproject.repository.UserRepository;
import com.taskmangement.taskproject.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {


//    @Autowired
//    private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public UsersDto createUser(UsersDto usersDto) {
		Users users = new Users();
		users.setName(usersDto.getName());
		users.setEmail(usersDto.getEmail());
		users.setPassword(passwordEncoder.encode(usersDto.getPassword()));
		Users savedUser = userRepository.save(users);
		UsersDto responseDto = new UsersDto();
		responseDto.setName(savedUser.getName());
		responseDto.setEmail(savedUser.getEmail());
		responseDto.setPassword(savedUser.getPassword());
		responseDto.setId(savedUser.getId());
		return responseDto;
	}

	
}
