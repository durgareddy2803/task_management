package com.taskmangement.taskproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users", uniqueConstraints= {
						@UniqueConstraint(columnNames = {"email"})})
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID",nullable =false)
	private long id;
	@Column(name ="NAME",nullable =false)
	private String name;
	@Column(name ="EMAIL",nullable =false)
	private String email;
	@Column(name ="PASSWORD",nullable =false)
	private String password;
	

	

}
