package com.taskmangement.taskproject.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto {
	
	private long id;
	private String name;
	private String email;
	private String password;
}
