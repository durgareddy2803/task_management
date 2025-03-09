package com.taskmangement.taskproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmangement.taskproject.payload.TaskDto;
import com.taskmangement.taskproject.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {

	//save a task
	@Autowired
	private TaskService taskService;
	
	@PostMapping("/{userId}/tasks")
	public ResponseEntity<TaskDto> saveTask(@PathVariable(name="userId") long userId, @RequestBody TaskDto taskDto){
		
		return new ResponseEntity<>(taskService.saveTask(userId, taskDto),HttpStatus.CREATED);
	}
	
	//get all tasks
	@GetMapping("/{userId}/tasks")
	public ResponseEntity<List<TaskDto>> getAllTasks(@PathVariable(name="userId") long userId){
		
		return new ResponseEntity<>(taskService.getAllTasks(userId),HttpStatus.OK);
	}
	
	//get indv tasks
	@GetMapping("/{userId}/tasks/{taskId}")
	public ResponseEntity<TaskDto> getTask(@PathVariable(name="userId") long userId,@PathVariable(name="taskId") long taskId) {
		
		return new ResponseEntity<>(taskService.getTask(userId,taskId),HttpStatus.OK);
	}
	//deleter task
	@DeleteMapping("/{userId}/tasks/{taskId}")
	public ResponseEntity<String> deleteTask(@PathVariable(name="userId") long userId,@PathVariable(name="taskId") long taskId) {
		taskService.deleteTask(userId,taskId);
		return new ResponseEntity<>("user deleted succesfully",HttpStatus.OK);
	}
}
