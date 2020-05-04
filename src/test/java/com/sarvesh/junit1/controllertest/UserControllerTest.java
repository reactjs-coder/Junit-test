package com.sarvesh.junit1.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sarvesh.junit1.controller.UserController;
import com.sarvesh.junit1.model.User;
import com.sarvesh.junit1.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	@InjectMocks
	UserController controller;
	
	@Mock
	UserService service;
	
	@Mock
	User user;
	
	private static final long ID = 14;
	
	@Test
	public void getFindByUserId() {
		when(service.findById(ID)).thenReturn(user);
		assertEquals(controller.getUserById(ID).getId(), user.getId());
	}
	@Test
	public void getUsers() {
		List<User> users = new ArrayList<User>();
		users.add(user);
		when(service.getUsers()).thenReturn(users);
		assertEquals(controller.getUsers().size(), users.size());
	}
	@Test
	public void addUser() {
		controller.saveUser(user);
		verify(service).addUser(user);
	}
	
	@Test
	public void update() {
		long id = 1;
		System.out.println("Hello");
		User user = mock(User.class);
		when(user.getId()).thenReturn(id);
		when(user.getName()).thenReturn("the name");
		when(user.getAge()).thenReturn(20);
		when(user.getAddress()).thenReturn("the address");
		when(controller.getUserById(id)).thenReturn(user);
		controller.updateUser(user, id);
		verify(service).updateUser(user);
	}
	@Test
	public void delete() {
		long id=1;
		controller.removeUser(id);
		verify(service).removeUser(id);
	}
	@Test
	public void getUser() {
		long  id =12;
		User userInstance = mock(User.class);
		controller.getUserById(id);
		when(controller.getUserById(id)).thenReturn(userInstance);
		when(userInstance.getAge()).thenReturn(30);
		System.out.println(userInstance.toString());
		verify(service).findById(id);
		
		Result result =  JUnitCore.runClasses(UserControllerTest.class);
		for(Failure failure:result.getFailures())
		{
			 System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
		assertEquals(30, userInstance.getAge());
		
		
		
		
	}
}
