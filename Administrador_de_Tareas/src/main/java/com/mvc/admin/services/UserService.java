package com.mvc.admin.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.mvc.admin.models.Task;
import com.mvc.admin.models.User;
import com.mvc.admin.repositories.TaskRepository;
import com.mvc.admin.repositories.UserRepository;

@Service
public class UserService {
	   private final UserRepository userRepository;
	   private final TaskRepository taskRepository;
	    public UserService(UserRepository userRepository,TaskRepository taskRepository) {
	        this.userRepository = userRepository;
	        this.taskRepository=taskRepository;
	    }
	    
	    public List<User> allUsers(){
	    	return userRepository.findAll();
	    }
	    
	    // registrar el usuario y hacer Hash a su password
	    public User registerUser(User user) {
	        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
	        user.setPassword(hashed);
	        return userRepository.save(user);
	    }
	    
	    // encontrar un usuario por su email
	    public User findByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }
	    
	    // encontrar un usuario por su id
	    public User findUserById(Long id) {
	    	Optional<User> u = userRepository.findById(id);
	    	
	    	if(u.isPresent()) {
	            return u.get();
	    	} else {
	    	    return null;
	    	}
	    }
	    // autenticar usuario
	    public boolean authenticateUser(String email, String password) {   
	        User user = userRepository.findByEmail(email);
	        if(user == null) {
	            return false;
	        } else {
	            if(BCrypt.checkpw(password, user.getPassword())) {
	                return true;
	            } else {
	                return false;
	            }
	        }
	    }
	    
	    public Task createTask(Task task) {
	    	return taskRepository.save(task);
	    }
	    
		public Task findTask(Long id) {	
			Optional<Task> optionaltype = taskRepository.findById(id);
		    if(optionaltype.isPresent()) {
		    	return optionaltype.get();
		    }else {
		        return null;
		    }
		 }
		
	    public Task update(Task t) {
	    	Task temp = findTask(t.getId());
	    	temp.setName(t.getName());
	    	temp.setAssignee(t.getAssignee());
	    	temp.setPriority(t.getPriority());
	    	return taskRepository.save(temp);
	    }
	    
	    public void delete(Long id) {
	    	taskRepository.deleteById(id);
	    }
}
