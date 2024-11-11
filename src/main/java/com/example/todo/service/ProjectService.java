package com.example.todo.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.model.Project;
import com.example.todo.repo.IProjectRepo;

@Service
public class ProjectService {
	
	@Autowired
	IProjectRepo repoProject;
	
	public ArrayList<Project> findAllProjects(String username){
		ArrayList<Project> projectList = repoProject.findAllProjectsByUsername(username);
		if(!projectList.isEmpty()) {
			return projectList;
		}
		return null;
	}
	
	public Project getProjectById(Long projectId) {
		Project project = repoProject.findById(projectId).get();
		return project;
	}
	
	public boolean addOrSaveProject(Project project) {
		Project projectObj = repoProject.save(project);
		if(getProjectById(projectObj.getProjectId()) != null) {
			return true;
		}
		return false;
	}
	
	public boolean deleteProject(Long projectId) {
		repoProject.deleteById(projectId);
		if(repoProject.findById(projectId).isEmpty()) {
			return true;
		}
		return false;
	}
}
