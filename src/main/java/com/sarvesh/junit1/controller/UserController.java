package com.sarvesh.junit1.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarvesh.junit1.model.User;
import com.sarvesh.junit1.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/addUser")
	public User saveUser(@RequestBody User user) {
		System.out.println(user.getName());
		return userService.addUser(user);
	}

	@SuppressWarnings("null")
	@GetMapping("/getUsers")
	public List<User> getUsers() {
		String ptr = null; 
		  
        // Checking if ptr.equals null or works fine. 
        try
        { 
            // This line of code throws NullPointerException 
            // because ptr is null 
            if (ptr.equals("gfg")) 
                System.out.print("Same"); 
            else 
                System.out.print("Not Same"); 
        } 
        catch(NullPointerException e) 
        { 
            System.out.print("NullPointerException Caught"); 
        } 
		return userService.getUsers();
	}

	@GetMapping("/getUser/{id}")
	public User getUserById(@PathVariable("id") long id) {
		return userService.findById(id);
	}

	@PutMapping("/updateUser/{id}")
	public String updateUser(@RequestBody User user, @PathVariable long id) {
		User userInstance = userService.findById(id);
		userInstance.setName(user.getName());
		userInstance.setAge(user.getAge());
		userInstance.setAddress(user.getAddress());
		userService.updateUser(userInstance);
		return "Updated Successfully";
	}

	@DeleteMapping("removeUser/{id}")
	public boolean removeUser(@PathVariable("id") long id) {
			userService.removeUser(id);
			return true;
	}
	
	public void divideByZero() {
		 int array[] = {20,20,40};
	      int num1 = 15, num2 = 0;
	      int result = 0;
	      try {
	         result = num1/num2;
	         System.out.println("The result is" +result);
	         
	         for(int i = 2; i >= 0; i--) {
	            System.out.println("The value of array is" +array[i]);
	         }
	      } catch (ArrayIndexOutOfBoundsException e) {
	         System.out.println("Error. Array is out of Bounds"+e);
	      } catch (ArithmeticException e) {
	         System.out.println ("Can't be divided by Zero"+e);
	      }
	}

}
