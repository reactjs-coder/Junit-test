package com.sarvesh.junit1;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.sarvesh.junit1.model.User;
import com.sarvesh.junit1.repository.UserRepository;
import com.sarvesh.junit1.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class Junit1ApplicationTests {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Mock
	User user;

	@Mock
	private UserRepository repository;

	@InjectMocks
	private UserService service;

	private static final long ID = 13;

	@Test
	public void getUsers() {
		when(userRepository.findAll()).thenReturn(Stream
				.of(new User(376, "Danile", 31, "USA"), new User(958, "Huy", 35, "UK")).collect(Collectors.toList()));
		assertEquals(2, userService.getUsers().size());
	}

	@Test
	public void getUserById() {
		userService.findById(ID);
		verify(userRepository).findByUserId(ID);
	}

	@Test
	public void getUserId() {
		Result result = JUnitCore.runClasses(Junit1ApplicationTests.class);
		User obj = new User(958, "Huy", 35, "UK");
		long id = 958;
		when(userRepository.findByUserId(id)).thenReturn(obj);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());

		assertEquals(obj, userService.findById(id));

	}

	@Test
	public void addUser() {
		User user = mock(User.class);
		userService.addUser(user);
		verify(userRepository).save(user);
	}

	@Test
	public void updateUser() {
		User user = mock(User.class);
		when(user.getId()).thenReturn(ID);
		when(user.getName()).thenReturn("the name");
		when(user.getAge()).thenReturn(20);
		when(user.getAddress()).thenReturn("the address");
		when(userRepository.findByUserId(ID)).thenReturn(user);
		userService.updateUser(user);
		verify(userRepository).save(user);
	}

	@Test
	public void removeUser() {
		userService.removeUser(ID);
		verify(userRepository).deleteById(ID);
	}

	@Test
	public void listOfUsers() {

		List<User> list = new ArrayList<User>();
		User userOne = new User(2, "sarvesh", 24, "Hyd");
		User userTwo = new User(3, "Kamesh", 23, "Hyd");
		User userThree = new User(4, "Venkatesh", 23, "LBN");
		list.add(userOne);
		list.add(userTwo);
		list.add(userThree);

		when(repository.findAll()).thenReturn(list);

		// test
		List<User> userList = service.getUsers();

		assertEquals(3, userList.size());

		verify(repository, times(1)).findAll();

	}

	@Test
	public void getlistOfUsers() {

		List<User> list = new ArrayList<User>();
		User userOne = new User(2, "sarvesh", 24, "Hyd");
		User userTwo = new User(3, "Kamesh", 23, "Hyd");
		User userThree = new User(4, "Venkatesh", 23, "LBN");
		list.add(userOne);
		list.add(userTwo);
		list.add(userThree);

		when(repository.findAll()).thenReturn(list);

		// test
		List<User> userList = service.getUsers();

		System.out.println(userList.toString());

		System.out.println(userOne.getAge());

		assertEquals(2, userList.size());

		verify(repository, times(1)).findAll();

	}
	
	@Test 
	public void getSingleUser() {
		when(repository.findByUserId(2)).thenReturn(new User(2,"sarvesh",22,"Hyd"));
		User user = service.findById(2);
		assertEquals("sarvesh", user.getName());
		assertEquals(22, user.getAge());
		assertEquals("Hyd", user.getAddress());
	}
	
	@Test 
	public void getSingleUserList() {
		when(repository.findByUserId(2)).thenReturn(new User(2,"sarvesh",22,"Hyd"));
		User user = service.findById(2);
		assertEquals("sarvesh", user.getName());
		assertEquals(22, user.getAge());
		assertEquals("RJM", user.getAddress());
	}

}
