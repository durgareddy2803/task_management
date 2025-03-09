package com.taskmangement.taskproject.service;

import java.util.List;

import com.taskmangement.taskproject.payload.TaskDto;

public interface TaskService {

	public TaskDto saveTask(long userId, TaskDto taskDto);
	
	public List<TaskDto> getAllTasks(long userId);
	
	public TaskDto getTask(long userId, long taskId);
	
	public void deleteTask(long userId, long taskId);
	
}
