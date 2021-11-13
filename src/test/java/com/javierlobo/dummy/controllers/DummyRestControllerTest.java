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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class DummyRestControllerTest extends AbstractRestControl {

	private static final Logger logger = LoggerFactory.getLogger(DummyRestControllerTest.class);

	public static final String HELLO_WORLD = "Hello World!!!";

	@Spy
	@InjectMocks
	private DummyController dummyController;
	@Mock
	private IDummyService dummyService;
	@Mock
	private List<User> userListExpected;
	@Mock
	private List<User> responseList;
	@Mock
	private User user;
	@Mock
	private User userEmpty;
	@Mock
	private User userExpecter;
	@Mock
	private User userResponse;
	@Mock
	private MockHttpServletResponse response;

	String userName;
	String exceptionMessage;

	@BeforeEach @Override
	public void setUp() {
		super.setUp();

		userName = "Pepe Martinez";
		exceptionMessage = "No existe el usuario '".concat(userName).concat("'");

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
		assertEquals(HELLO_WORLD, response);
		logger.info("@Test -> helloTest() = " + response);
	}

	@Test
    public void getUserList_OkTest() {
		userListExpected = new ArrayList<User>();
		
		userListExpected.add(new User("Jose Rodriguez", 229, "Work"));
		userListExpected.add(new User("Jose Rodriguez", 300, "Work"));
		userListExpected.add(new User("Jose Rodriguez", 305, "Work"));
		userListExpected.add(new User("Jose Rodriguez", 289, "Work"));
		userListExpected.add(new User("Jose Rodriguez", 250, "Work"));
		
		when(dummyService.getUserList()).thenReturn(userListExpected);

		responseList = dummyController.getUserList();
		assertAll(
				() -> assertNotNull(userListExpected),
				() -> assertEquals(userListExpected, responseList)
			);		

		verify(dummyService).getUserList();
		
		logger.info("@Test -> getUserList_Ok()");
	}
	
	@Test
    public void getUserList_EmptyTest() {
		userListExpected = new ArrayList<User>();
		userListExpected.add(new User("", 0, ""));
		
		when(dummyService.getUserList()).thenReturn(userListExpected);
		
		responseList = dummyController.getUserList();
		assertAll(
				() -> assertNotNull(userListExpected),
				() -> assertEquals(userListExpected, responseList)
			);
		
		verify(dummyService).getUserList();
		
		logger.info("@Test -> getUserList_Empty()");
	}
	
	@Test
    public void getUser_OkTest() {
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
	public void deleteUser_OkTest() throws Exception {
		when(dummyService.deleteUser(userName)).thenReturn(userExpecter);
		userResponse = dummyController.deleteUser(userName);
		assertAll(
				() -> assertNotNull(userResponse),
				() -> assertEquals(userExpecter, userResponse)
			);
		verify(dummyService, times(1)).deleteUser(userName);
		logger.info("@Test -> deleteUser_OkTest()");
	}
	
	@Test
	public void deleteUser_EmptyTest() throws Exception {
		when(dummyService.deleteUser(userName)).thenReturn(null)
				.thenThrow(new Exception("No existe el usuario '".concat(userName).concat("'")));
		userResponse = dummyController.deleteUser(userName);
		Throwable exceptionExpected = assertThrows(Exception.class, () -> {
			throw new Exception(exceptionMessage);
		});
		assertAll(
				() -> assertNull(userResponse),
				() -> assertNotEquals(userEmpty, userResponse),
				() -> assertEquals(exceptionMessage, exceptionExpected.getMessage())
		);
		verify(dummyService, times(1)).deleteUser(userName);
		logger.info("@Test -> deleteUser_OkTest()");
	}

	 // --------------------------------
	 // Code Error 200
	 // --------------------------------
	 @Test
     public void getHelloHttpCode200Test() throws Exception {
		when(dummyController.hello()).thenReturn(HELLO_WORLD);
	 	super.setUrl(EndPointsEnum.URL_HOME.getUrl());
	 	MockHttpServletResponse response = this.getPetition();

		 assertAll(
				 () -> assertNotNull(response),
				 () -> assertEquals(HttpStatus.OK.value(), response.getStatus())
		 );
		logger.info("@Test -> getHelloHttpCode200Test() = Content: " + response.getContentAsString() +"'");
		//verify(dummyController, times(1)).hello();
	 }

	 @Test
     public void getUserListHttpCode200Test() throws Exception {
		when(dummyController.getUserList()).thenReturn(userListExpected);
	 	this.setUrl(EndPointsEnum.URL_USER_LIST.getUrl());
	 	MockHttpServletResponse response = this.getPetition();

	 	assertAll(
	 			() -> assertNotNull(response),
	 			() -> assertEquals(HttpStatus.OK.value(), response.getStatus())
	 		);
	 	verify(dummyController, times(1)).getUserList();
	 	logger.info("@Test -> getUserListHttpCode200Test() = Content: " + response.getContentAsString() +"'");
	 }

	 @Test
     public void getUserHttpCode200Test() throws Exception {
		when(dummyController.getUser(userName)).thenReturn(userExpecter);
	 	this.setUrl(EndPointsEnum.URL_USER_PEPE.getUrl());
	 	MockHttpServletResponse response = this.getPetition();

	 	assertAll(
	 			() -> assertNotNull(response),
	 			() -> assertEquals(HttpStatus.OK.value(), response.getStatus())
	 		);
		verify(dummyController, times(1)).getUser(userName);
	 	logger.info("@Test -> getUserHttpCode200Test() = Content: " + response.getContentAsString() +"'");
	 }

	 @Test
     public void postCreateUserHttpCode200Test() throws Exception {
		when(dummyController.postUser(user)).thenReturn(userExpecter);
		this.setUrl(EndPointsEnum.URL_USER.getUrl());
	 	MockHttpServletResponse response = this.postPetition(user);
	 	assertAll(
	 			() -> assertNotNull(response),
	 			() -> assertEquals(this.mapToJson(user), response.getContentAsString())
	 		);
	 	verify(dummyController, times(1)).postUser(user);
	 	logger.info("@Test -> postCreateUserHttpCode200Test()");
	 }

	@Test
	public void putCreateUserHttpCode200Test() throws Exception {
		when(dummyController.putUser(user)).thenReturn(userExpecter);
		this.setUrl(EndPointsEnum.URL_USER.getUrl());
		response = this.putPetition(user);
		assertAll(
				() -> assertNotNull(response),
				() -> assertEquals(this.mapToJson(userExpecter), response.getContentAsString())
		);
		verify(dummyController, times(1)).putUser(user);
		logger.info("@Test -> postCreateUserHttpCode200Test()");
	}

	 @Test
	 public void deleteUserHttpCode200Test() throws Exception {
		 when(dummyController.deleteUser(userName)).thenReturn(userExpecter);
		 when(dummyService.deleteUser(userName)).thenReturn(userExpecter);

		 // this.setUrl(EndPointsEnum.URL_USER_PEPE.getUrl());
		 this.setUrl("/api/user/Pepe Martinez");
		 MockHttpServletResponse response = this.deletePetition();

		 assertAll(
				() -> assertNotNull(response),
				() -> assertEquals(this.mapToJson(userExpecter), response.getContentAsString())
		 );
		 verify(dummyController, times(1)).deleteUser(userName);
		 logger.info("@Test -> deleteUserHttpCode200Test()");
	 }
	// // --------------------------------
	// // Code Error 401, 403, 404, 500
	// // --------------------------------
	// @Test @Disabled
    // public void getHelloHttpCode401Test() throws Exception {
	// 	this.setUrl(EndPointsEnum.UNAUTHORIZED.toString());
	// 	MockHttpServletResponse response = this.getPetition();

	// 	assertAll(
	// 			() -> assertNotNull(response),
	// 			() -> assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus())
	// 	);

	// 	logger.info("@Test -> getUserHttpCode401Test() Content: " + response.getContentAsString() +"'");
	// }

	// @Test @Disabled
    // public void getHelloHttpCode403Test() throws Exception {
	// 	this.setUrl(EndPointsEnum.FORBIDDEN.toString());
	// 	MockHttpServletResponse response = this.getPetition();

	// 	assertAll(
	// 			() -> assertNotNull(response),
	// 			() -> assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatus())
	// 	);

	// 	logger.info("@Test -> getUserHttpCode403Test() Content: " + response.getContentAsString() +"'");
	// }

	// @Test @Disabled
    // public void getHelloHttpCode404Test() throws Exception {
	// 	this.setUrl(EndPointsEnum.URL_BAD.toString());
	// 	MockHttpServletResponse response = this.getPetition();

	// 	assertAll(
	// 			() -> assertNotNull(response),
	// 			() -> assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus())
	// 		);
	// 	logger.info("@Test -> getUserHttpCode404Test() = Content: " + response.getContentAsString() +"'");
	// }

	// @Test @Disabled
    // public void getHelloHttpCode500Test() throws Exception {
	// 	this.setUrl(EndPointsEnum.INTERNAL_SERVER_ERROR.toString());
	// 	MockHttpServletResponse response = this.getPetition();

	// 	assertAll(
	// 			() -> assertNotNull(response),
	// 			() -> assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus())
	// 		);

	// 	logger.info("@Test -> getUserHttpCode500Test() Content: " + response.getContentAsString() +"'");
	// }
}
