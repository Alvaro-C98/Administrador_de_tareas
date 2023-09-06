package com.mvc.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvc.admin.models.User;
import com.mvc.admin.services.UserService;
import com.mvc.admin.validator.UserValidator;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {
	private final UserService userService;
	private final UserValidator userValidator;
    public MainController(UserService userService,UserValidator userValidator) {
        this.userService = userService;
        this.userValidator=userValidator;
    }
    
    @GetMapping("/")
    public String home() {
    	return "redirect:/registration";
    }
    
    @GetMapping("/registration")
    public String registerForm(@ModelAttribute("user") User user) {
        return "registrationPage.jsp";
    }
    
    
    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("user") User user, 
    		BindingResult result, HttpSession session,
    		Model redirect) {
    	userValidator.validate(user, result);
    	if(result.hasErrors() || userService.findByEmail(user.getEmail())!=null) {
    		redirect.addAttribute("errorCreate", "Mail already registered");
    		return "registrationPage.jsp";
    	}else {
    		User u = userService.registerUser(user);
    		session.setAttribute("userId", u.getId());
    		return "redirect:/home";
    	}
    }
    
    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, 
    		@RequestParam("password") String password, 
    		Model model, HttpSession session) {
    	boolean isAuthentication = userService.authenticateUser(email, password);
    	if(isAuthentication) {
    		User u = userService.findByEmail(email);
    		session.setAttribute("userId", u.getId());
    		return "redirect:/tasks";
    	}else {
    		model.addAttribute("errorLogin", "invalid credentials. Please try again.");
    		return "redirect:/";
    	}
    }
    
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
    	User u = userService.findUserById(userId);
    	model.addAttribute("user",u);
    	return "homePage.jsp";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
}
