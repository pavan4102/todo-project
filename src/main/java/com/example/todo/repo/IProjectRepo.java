package com.example.todo.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.todo.model.Project;

@Repository
public interface IProjectRepo extends JpaRepository<Project, Long> {
	
	@Query("SELECT p FROM Project p WHERE p.user.username = :username")
	ArrayList<Project> findAllProjectsByUsername(@Param("username") String username);
}
