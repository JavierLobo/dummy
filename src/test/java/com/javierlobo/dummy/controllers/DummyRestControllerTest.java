package com.javierlobo.dummy.controllers;

import com.javierlobo.dummy.enums.EndPointsEnum;
import com.javierlobo.dummy.service.IDummyService;
import com.javierlobo.dummy.view.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class DummyRestControllerTest extends AbstractRestControlTest {

	private static final Logger logger = LoggerFactory.getLogger(DummyRestControllerTest.class);
	
	@Spy
	@InjectMocks
	private DummyController dummyController;
	@Mock
	private IDummyService dummyService;
	@Mock
	private List<User> expectedUserList;
	@Mock
	private List<User> responseList;
	@Mock
	private MockHttpServletResponse response;
	@Mock
	private User user;
	@Mock
	private User userEmpty;
	@Mock
	private User userExpecter;
	@Mock
	private User userResponse;

	@BeforeEach @Override
	public void setUp() {
		super.setUp();
		user = new User("Jose Rodriguez", 229, "Work");
		userExpecter = new User("Jose Rodriguez", 229, "Work");
		userEmpty = new User("", 0, "");
		logger.info("@BeforeEach -> setUp()");
	}
	
	@AfterEach
	public void tearDown() {
		logger.info("@AfterEach -> tearDown()");
	}
	
	@Test
    public void helloTest() {
		String response = dummyController.hello();
		assertEquals("Hello World!!!", response);
		logger.info("@Test -> helloTest() = " + response);
	}

	@Test
    public void getUserList_OkTest() {
		expectedUserList = new ArrayList<User>();
		
		expectedUserList.add(new User("Jose Rodriguez", 229, "Work"));
		expectedUserList.add(new User("Jose Rodriguez", 300, "Work"));
		expectedUserList.add(new User("Jose Rodriguez", 305, "Work"));
		expectedUserList.add(new User("Jose Rodriguez", 289, "Work"));
		expectedUserList.add(new User("Jose Rodriguez", 250, "Work"));
		
		when(dummyService.getUserList()).thenReturn(expectedUserList);

		responseList = dummyController.getUserList();
		assertAll(
				() -> assertNotNull(expectedUserList),
				() -> assertEquals(expectedUserList, responseList)
			);		

		verify(dummyService).getUserList();
		
		logger.info("@Test -> getUserList_Ok()");
	}
	
	@Test
    public void getUserList_EmptyTest() {
		expectedUserList = new ArrayList<User>();
		expectedUserList.add(new User("", 0, ""));
		
		when(dummyService.getUserList()).thenReturn(expectedUserList);
		
		responseList = dummyController.getUserList();
		assertAll(
				() -> assertNotNull(expectedUserList),
				() -> assertEquals(expectedUserList, responseList)
			);
		
		verify(dummyService).getUserList();
		
		logger.info("@Test -> getUserList_Empty()");
	}
	
	@Test
    public void getUser_OkTest() {
		String userName = "Pepe Martinez";
		
		when(dummyService.getUserByName(userName)).thenReturn(user);
				
		userResponse = dummyController.getUser(userName);
		assertAll(
				() -> assertNotNull(user),
				() -> assertEquals(user, userResponse)
			);
		
		verify(dummyService, times(1)).getUserByName(userName);
		
		logger.info("@Test -> getUser_Ok()");
	}

	@Test
    public void getUser_EmptyTest() {
		String userName = "Pepe Martinez";
		
		when(dummyService.getUserByName(userName)).thenReturn(userEmpty);
		
		userResponse = dummyController.getUser(userName);
		assertAll(
				() -> assertNotNull(userResponse),
				() -> assertEquals(userEmpty, userResponse)
			);
		
		verify(dummyService, times(1)).getUserByName(userName);
		
		logger.info("@Test -> getUser_Empty()");
	}
	
	@Test
    public void postUser_OkTest() {
		when(dummyService.createUser(user)).thenReturn(user);
		User userResponse = dummyController.postUser(user);
		assertAll(
				() -> assertNotNull(user),
				() -> assertEquals(user, userResponse)
			);		
		verify(dummyService, times(1)).createUser(user);
		logger.info("@Test -> getUserList_Ok()");
	}

	@Test
    public void postUser_EmptyTest() {
		when(dummyService.createUser(user)).thenReturn(userEmpty);
		User userResponse = dummyController.postUser(user);
		assertAll(
				() -> assertEquals(0, userResponse.getSalary()),
				() -> assertEquals("", userResponse.getStatus()),
				() -> assertEquals("", userResponse.getUserName())
			);		
		verify(dummyService, times(1)).createUser(user);
		logger.info("@Test -> getUserList_Ok()");
	}

	@Test
	public void putUser_OkTest() {
		when(dummyService.modifiUser(user)).thenReturn(user);
		User userResponse = dummyController.putUser(user);
		assertAll(
				() -> assertNotNull(userResponse),
				() -> assertEquals(user, userResponse)
			);
		verify(dummyService, times(1)).modifiUser(user);
		logger.info("@Test -> putUser_OkTest()");
	}

	@Test
	public void putUser_OkEmpty() {
		when(dummyService.modifiUser(user)).thenReturn(userEmpty);
		User userResponse = dummyController.putUser(user);
		assertAll(
				() -> assertNotNull(userResponse),
				() -> assertEquals(userEmpty.getSalary(), userResponse.getSalary()),
				() -> assertEquals(userEmpty.getStatus(), userResponse.getStatus()),
				() -> assertEquals(userEmpty.getUserName(), userResponse.getUserName())
			);
		verify(dummyService, times(1)).modifiUser(user);
		logger.info("@Test -> putUser_OkEmpty()");
	}

	@Test
	public void deleteUser_OkTest() {
		String userName = "Pepe Martinez";
		when(dummyService.deleteUser(userName)).thenReturn(user);
		User userResponse = dummyController.deleteUser(userName);
		assertAll(
				() -> assertNotNull(userResponse),
				() -> assertEquals(user, userResponse)
			);
		verify(dummyService, times(1)).deleteUser(userName);
		logger.info("@Test -> deleteUser_OkTest()");
	}
	
	@Test
	public void deleteUser_EmptyTest() {
		String userName = "Pepe Martinez";
		when(dummyService.deleteUser(userName)).thenReturn(userEmpty);
		User userResponse = dummyController.deleteUser(userName);
		assertAll(
				() -> assertNotNull(userResponse),
				() -> assertEquals(userEmpty, userResponse)
			);
		verify(dummyService, times(1)).deleteUser(userName);
		logger.info("@Test -> deleteUser_OkTest()");
	}

}
