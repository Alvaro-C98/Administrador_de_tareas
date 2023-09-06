package com.mvc.admin.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mvc.admin.models.Task;
import com.mvc.admin.models.User;
import com.mvc.admin.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class TaskController {
		private final UserService userService;
	    public TaskController(UserService user) {
	        this.userService = user;
	    }
	    
	    @GetMapping("/tasks")
	    public String tasks(Model model, HttpSession session) {
	    	Long userId = (Long) session.getAttribute("userId");
	    	User u = userService.findUserById(userId);
	    	model.addAttribute("user",u);
	    	List<User> users = userService.allUsers();
	    	model.addAttribute("users",users);
	    	return "tasks.jsp";
	    }
	    
	    @GetMapping("/tasks/new")
	    public String createTask(@ModelAttribute("task") Task task,
				HttpSession sesion, Model model) {
	    	Long userId = (Long) sesion.getAttribute("userId");   	
			if (userId == null) {
				return "redirect:/";
			}
	    	User u = userService.findUserById(userId);
	    	model.addAttribute("user",u);
	    	List<User> users = userService.allUsers();
	    	model.addAttribute("users",users);
	    	return "newTask.jsp";
			
	    }
	    
	    @PostMapping("/tasks/new")
	    public String newTask(@Valid @ModelAttribute("task") Task task, BindingResult resultado,
	    		HttpSession sesion) {
			if (resultado.hasErrors()) {
				return "redirect:/tasks/new";
			}
			Long userId = (Long) sesion.getAttribute("userId");
			User u = userService.findUserById(userId);
			u.setTasks(task);
			task.setUsers(u);
			userService.createTask(task);
	    	return "redirect:/tasks";
	    }
	    
	    @GetMapping("/tasks/{id}")
	    public String showtask(@PathVariable("id") Long id,
	    		Model model,HttpSession sesion) {
	    	Task task = userService.findTask(id);
	    	model.addAttribute("task",task);
	    	return "showTask.jsp";
	    }
	    
	    @GetMapping("/tasks/{id}/edit")
	    public String editask(@PathVariable("id") Long id,
	    		Model model,HttpSession sesion) {
	    	Long userId = (Long) sesion.getAttribute("userId");
			User u = userService.findUserById(userId);
			model.addAttribute("user",u);
	    	Task task = userService.findTask(id);
	    	model.addAttribute("task",task);
	    	List<User> users = userService.allUsers();
	    	model.addAttribute("users",users);
	    	return "editTask.jsp";
	    }
	    
	    @PostMapping("/tasks/{id}/edit")
	    public String update(@Valid @ModelAttribute("task") Task task, BindingResult result,
	    		HttpSession sesion, Model model) {
	    	Long userId = (Long) sesion.getAttribute("userId");
			User u = userService.findUserById(userId);
			Task t = userService.findTask(task.getId());
	        if (result.hasErrors() || !t.getCreator().equals(u.getName())) {
	        	model.addAttribute("error","You can't edit this task");
	            return "editTask.jsp";
	        } else {
	            userService.update(task);
	            return "redirect:/tasks";
	        }
	    }
	    
	    @GetMapping("/tasks/{id}/delete")
	    public String delete(@PathVariable("id") Long id, HttpSession sesion, Model model) {
	    	Long userId = (Long) sesion.getAttribute("userId");
			User u = userService.findUserById(userId);
			Task t = userService.findTask(id);
	    	if(!t.getCreator().equals(u.getName())) {
	    		model.addAttribute("error","You can't delete this task");
	    		return "showTask.jsp";
	    	}
	        userService.delete(id);
	        return "redirect:/tasks";
	    }
}
