package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todo.model.User;
import com.example.todo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService serviceUser;
	
	@GetMapping({"/", "/loginUser"})
	public String loginUser(Model model, @ModelAttribute("message") String message, RedirectAttributes redirectAttributes) {
		model.addAttribute("user", new User());
		return "UserLogin";
	}

	@PostMapping("/checkUser")
	public String checkUser(User user, RedirectAttributes redirectAttributes) {
	    if (serviceUser.findUser(user)) {
	        User authenticatedUser = serviceUser.findByUsername(user.getUsername());
	        if (authenticatedUser != null) {
	            redirectAttributes.addFlashAttribute("message", "Login Success!");
	            return "redirect:/viewProjectList/" + authenticatedUser.getUserId();
	        }
	    }
	    redirectAttributes.addFlashAttribute("message", "Invalid Login Credentials! or User doesnt exist");
	    return "redirect:/loginUser";
	}

	
	@GetMapping("/registerNewUser")
	public String registerNewUser(Model model) {
		model.addAttribute("user", new User());
		return "RegisterUser";
	}
	
	@PostMapping("/registerUser")
	public String saveUser(User user, RedirectAttributes redirectAttributes) {
		if(serviceUser.saveUser(user)) {
			redirectAttributes.addFlashAttribute("message", "Registration Success!");
			return "redirect:/loginUser";
		}
		redirectAttributes.addFlashAttribute("message", "Registration Failure!");
		return "redirect:/loginUser";
	}
	
}
