package com.taskmangement.taskproject.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.taskmangement.taskproject.entity.Task;
import com.taskmangement.taskproject.entity.Users;
import com.taskmangement.taskproject.exception.APIException;
import com.taskmangement.taskproject.exception.TaskNotFound;
import com.taskmangement.taskproject.exception.UserNotFound;

import org.springframework.stereotype.Service;

import com.taskmangement.taskproject.payload.TaskDto;
import com.taskmangement.taskproject.repository.TaskRepository;
import com.taskmangement.taskproject.repository.UserRepository;
import com.taskmangement.taskproject.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserRepository userRepository;
	@Override
	public TaskDto saveTask(long userId, TaskDto taskDto) {
	    // Check if user exists, otherwise throw exception
	    Users user = userRepository.findById(userId)
	        .orElseThrow(() -> new UserNotFound("User with ID " + userId + " not found"));

	    // Map DTO to Entity
	    Task task = modelMapper.map(taskDto, Task.class);
	    task.setUsers(user);

	    // Save task to the database
	    Task savedTask = taskRepository.save(task);
	    return modelMapper.map(savedTask, TaskDto.class);

	}
	

	@Override
	public List<TaskDto> getAllTasks(long userId) {
		userRepository.findById(userId)
		        .orElseThrow(() -> new UserNotFound("User with ID " + userId + " not found"));
		
		List<Task> tasks = taskRepository.findAllByUsersId(userId);
		
		return tasks.stream().map(task -> modelMapper.map(task, TaskDto.class)).collect(Collectors.toList());

	}


	@Override
	public TaskDto getTask(long userId, long taskId) {

		Users user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFound("User with ID " + userId + " not found"));
		
		Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFound("task with Id" + taskId +"noyt found"));
		
		if(user.getId() != task.getUsers().getId()) {
			throw new APIException("Task id " + taskId + "does not belong to user with ID " + userId);
		}
		
		return modelMapper.map(task, TaskDto.class);
	}


	@Override
	public void deleteTask(long userId, long taskId) {
		Users user = userRepository.findById(userId)
		        .orElseThrow(() -> new UserNotFound("User with ID " + userId + " not found"));
				
		Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFound("task with Id" + taskId +"noyt found"));
				
		if(user.getId() != task.getUsers().getId()) {
					throw new APIException("Task id " + taskId + "does not belong to user with ID " + userId);
				}
		taskRepository.deleteById(taskId);
		}

}
