package com.example.todo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.model.ToDo;
import com.example.todo.repo.IToDoRepo;

@Service
public class ToDoService {
	
	@Autowired
	IToDoRepo repo;
	
	public List<ToDo> getAllToDoItems(long projectId){
		List<ToDo> toDoList = new ArrayList<ToDo>();
		toDoList = repo.findAllToDoByProjectId(projectId);
		return toDoList;
	}
	
	public ToDo getToDoItemById(Long Id){
		ToDo todo = repo.findById(Id).get();
		return todo;
	}
	
	public boolean updateStatusAsCompleted(Long Id){
		ToDo todo = repo.findById(Id).get();
		todo.setStatus("Completed");
		return saveOrUpdateToDoItem(todo);
	}
	
	public boolean updateStatusAsPending(Long Id){
		ToDo todo = repo.findById(Id).get();
		todo.setStatus("Pending...");
		return saveOrUpdateToDoItem(todo);
	}
	
	public boolean saveOrUpdateToDoItem(ToDo todo){
		ToDo updatedObj = repo.save(todo);
		if(getToDoItemById(updatedObj.getId()) != null) {
			return true;
		}
		return false;
	}
	
	public boolean deleteToDoItem(Long id){
		repo.deleteById(id);
		if(repo.findById(id).isEmpty()) {
			return true;
		}
		return false;
	}
}
