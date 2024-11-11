package com.example.todo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.todo.model.ToDo;

@Repository
public interface IToDoRepo extends JpaRepository<ToDo, Long> {
	
	@Query("SELECT t FROM ToDo t WHERE t.project.projectId = :projectId")
	List<ToDo> findAllToDoByProjectId(@Param("projectId") long projectId);
}
