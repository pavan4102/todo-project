package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todo.model.Project;
import com.example.todo.model.ToDo;
import com.example.todo.service.ProjectService;
import com.example.todo.service.ToDoService;

@Controller
public class ToDoController {

	@Autowired
	ToDoService service;

	@Autowired
	ProjectService serviceProject;

	@GetMapping("/viewToDoList/{projectId}")
	public String viewAllToDoItems(Model model, @ModelAttribute("message") String message,
			@PathVariable long projectId) {
		Project project = serviceProject.getProjectById(projectId);
		if (project != null) {
			model.addAttribute("userId", project.getUser().getUserId());
			model.addAttribute("project", project);
			model.addAttribute("list", service.getAllToDoItems(projectId));
		}
		model.addAttribute("message", message);
		return "ViewToDoList.html";
	}

	@GetMapping("/updateToDoStatusAsComplete/{projectId}/{id}")
	public String updateToDoStatusAsComplete(@PathVariable long id, @PathVariable long projectId,
			RedirectAttributes redirectAttributes) {
		if (service.updateStatusAsCompleted(id)) {
			redirectAttributes.addFlashAttribute("message", "Update Successful!");
			return "redirect:/viewToDoList/" + projectId;
		}
		redirectAttributes.addFlashAttribute("message", "Update Failure!");
		return "redirect:/viewToDoList/" + projectId;
	}

	@GetMapping("/updateToDoStatusAsPending/{projectId}/{id}")
	public String updateToDoStatusAsPending(@PathVariable Long id, @PathVariable long projectId,
			RedirectAttributes redirectAttributes) {
		if (service.updateStatusAsPending(id)) {
			redirectAttributes.addFlashAttribute("message", "Update Successful!");
			return "redirect:/viewToDoList/" + projectId;
		}
		redirectAttributes.addFlashAttribute("message", "Update Failure!");
		return "redirect:/viewToDoList/" + projectId;
	}

	@GetMapping("/addToDoItem/{projectId}")
	public String addToDoItem(Model model, @PathVariable long projectId) {
		ToDo todo = new ToDo();
		todo.setStatus("Pending...");
		model.addAttribute("todo", todo);
		model.addAttribute("projectId", projectId);
		return "AddToDoItem";
	}

	@PostMapping("/saveToDoItem/{projectId}")
	public String saveToDoItem(ToDo todo, RedirectAttributes redirectAttributes, @PathVariable long projectId) {
		Project project = serviceProject.getProjectById(projectId);
		if (project != null) {
			todo.setProject(project);
			service.saveOrUpdateToDoItem(todo);
			redirectAttributes.addFlashAttribute("message", "Save Successful!");
			return "redirect:/viewToDoList/" + projectId;
		}
		redirectAttributes.addFlashAttribute("message", "Save Failure!");
		return "redirect:/addToDoItem";
	}

	@GetMapping("/editToDoItem/{projectId}/{id}")
	public String editToDoItem(Model model, @PathVariable long id, @PathVariable long projectId) {
		model.addAttribute("projectId", projectId);
		model.addAttribute("todo", service.getToDoItemById(id));
		return "EditToDoItem";
	}

	@PostMapping("/editSaveToDoItem/{projectId}/{id}")
	public String editSaveToDoItem(@PathVariable long projectId, ToDo todo, RedirectAttributes redirectAttributes) {
		todo.setProject(serviceProject.getProjectById(projectId));
		if (service.saveOrUpdateToDoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Edit Successful!");
			return "redirect:/viewToDoList/" + projectId; // Redirect with projectId
		}
		redirectAttributes.addFlashAttribute("message", "Edit Failure!");
		return "redirect:/editToDoItem/" + projectId + "/" + todo.getId(); // Redirect back to the edit page
	}

	@GetMapping("/deleteToDoItem/{projectId}/{id}")
	public String deleteToDoItem(@PathVariable Long id, @PathVariable long projectId,
			RedirectAttributes redirectAttributes) {
		if (service.deleteToDoItem(id)) {
			redirectAttributes.addFlashAttribute("message", "Delete Successful!");
			return "redirect:/viewToDoList/" + projectId;
		}
		redirectAttributes.addFlashAttribute("message", "Delete Failure!");
		return "redirect:/viewToDoList/" + projectId;
	}
}
