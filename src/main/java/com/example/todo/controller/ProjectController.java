package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todo.model.Project;
import com.example.todo.model.User;
import com.example.todo.service.ProjectService;
import com.example.todo.service.UserService;

@Controller
public class ProjectController {

	@Autowired
	ProjectService serviceProject;

	@Autowired
	UserService serviceUser;

	@GetMapping("/viewProjectList/{userId}")
	public String viewAllProjects(Model model, @PathVariable long userId) {
		User user = serviceUser.findUserById(userId);
		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("projectList", serviceProject.findAllProjects(user.getUsername()));
		}
		return "ViewProjectList";
	}

	@GetMapping("/addProject/{userId}")
	public String addProject(Model model, @PathVariable Long userId) {
		model.addAttribute("project", new Project());
		model.addAttribute("userId", userId);
//	    model.addAttribute("user", serviceUser.findUserById(userId));
		return "AddProject";
	}

	@PostMapping("/addProjectItem/{userId}")
	public String addProjectItem(Project project, @PathVariable Long userId) {
		User user = serviceUser.findUserById(userId);
		if (user != null) {
			project.setUser(user);
			if (serviceProject.addOrSaveProject(project)) {
				return "redirect:/viewProjectList/" + userId;
			}
		}
		return "redirect:/addProject/" + userId;
	}

	@GetMapping("/editProjectItem/{userId}/{projectId}")
	public String editProjectItem(Model model, @PathVariable long userId, @PathVariable long projectId) {
		model.addAttribute("project", serviceProject.getProjectById(projectId));
		model.addAttribute("userId", userId);
		return "EditProject";
	}

	@PostMapping("/editProject/{userId}/{projectId}")
	public String editProject(@PathVariable long userId, @PathVariable long projectId, Project project, 
	                          RedirectAttributes redirectAttributes) {
	    project.setUser(serviceUser.findUserById(userId));
	    if (serviceProject.addOrSaveProject(project)) {
	        redirectAttributes.addFlashAttribute("message", "Project Updated!");
	        return "redirect:/viewProjectList/" + userId; 
	    }
	    redirectAttributes.addFlashAttribute("message", "Project Updation Unsuccessful!");
	    return "redirect:/viewProjectList/" + userId;
	}

	@GetMapping("/deleteProject/{userId}/{projectId}")
	public String deleteProject(@PathVariable long userId, @PathVariable long projectId,
			RedirectAttributes redirectAttributes) {
		if (serviceProject.deleteProject(projectId)) {
			redirectAttributes.addFlashAttribute("message", "Project Deleted!");
			return "redirect:/viewProjectList/" + userId;
		}
		redirectAttributes.addFlashAttribute("message", "Project Not Deleted!");
		return "redirect:/viewProjectList/" + userId;
	}
}
