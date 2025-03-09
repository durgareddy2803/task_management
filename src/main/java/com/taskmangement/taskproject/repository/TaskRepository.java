package com.taskmangement.taskproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmangement.taskproject.entity.Task;
import com.taskmangement.taskproject.entity.Users;

public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findAllByUsersId(Long userid);

}
