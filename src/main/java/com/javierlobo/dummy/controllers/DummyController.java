package com.javierlobo.dummy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javierlobo.dummy.service.IDummyService;
import com.javierlobo.dummy.view.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "", description = "API Rest dedicada para servir de pruebas de funcionamiento.")
public class DummyController {
	
	private static final String REQUEST_HAS_SUCCEEDED = "The request has succeeded.";
	private static final String REQUEST_REQUIRES_USER_AUTHENTICATION = "The request requires user authentication.";
	private static final String RESOURCE_IS_FORBIDDEN = "Accessing the resource you were trying to reach is forbidden.";
	private static final String SERVER_NOT_FOUND = "The server has not found anything matching the Request-URI.";

	
	@Autowired
	private IDummyService dummyService;
	
	@ApiOperation(value = "Retorna Hola mundo." )//, description = "shows hello world")
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = REQUEST_HAS_SUCCEEDED),
        @ApiResponse(code = 401, message = REQUEST_REQUIRES_USER_AUTHENTICATION),
        @ApiResponse(code = 403, message = RESOURCE_IS_FORBIDDEN),
        @ApiResponse(code = 404, message = SERVER_NOT_FOUND)
	})
    @GetMapping("/")
    public @ResponseBody String hello() {
        return "Hello World!!!";
    }
	
	@ApiOperation(value = "Obtiene un objeto 'User' aportando un nombre para identificarlo." )
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = REQUEST_HAS_SUCCEEDED),
        @ApiResponse(code = 401, message = REQUEST_REQUIRES_USER_AUTHENTICATION),
        @ApiResponse(code = 403, message = RESOURCE_IS_FORBIDDEN),
        @ApiResponse(code = 404, message = SERVER_NOT_FOUND)
	})
	@GetMapping("/api/user/{userName}")
	public @ResponseBody User getUser(
		@ApiParam(
            value = "Nombre de usuario.", 
            required = true)
		@PathVariable("userName") String userName) {
        return dummyService.getUserByName(userName);
    }
	
	@ApiOperation(value = "Obtiene una lista de todos los objetos 'User' que hemos creado hasta el momento." )
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = REQUEST_HAS_SUCCEEDED),
        @ApiResponse(code = 401, message = REQUEST_REQUIRES_USER_AUTHENTICATION),
        @ApiResponse(code = 403, message = RESOURCE_IS_FORBIDDEN),
        @ApiResponse(code = 404, message = SERVER_NOT_FOUND)
	})
	@GetMapping("/api/user/list")
	public @ResponseBody List<User> getUserList() {
        return dummyService.getUserList();
    }
	
	@ApiOperation(value = "Crea un objeto User en una lista con los valores que le hemos aportado." )
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = REQUEST_HAS_SUCCEEDED),
        @ApiResponse(code = 401, message = REQUEST_REQUIRES_USER_AUTHENTICATION),
        @ApiResponse(code = 403, message = RESOURCE_IS_FORBIDDEN),
        @ApiResponse(code = 404, message = SERVER_NOT_FOUND)
	})
	@PostMapping("/api/user")
	public @ResponseBody User postUser(
			@RequestBody User user) {
		return dummyService.createUser(user);
	}
	
	@ApiOperation(value = "Busca una coincidencia por el campo nombre en la lista que hemos creado, si existe, modifica el objeto User existente." )
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = REQUEST_HAS_SUCCEEDED),
        @ApiResponse(code = 401, message = REQUEST_REQUIRES_USER_AUTHENTICATION),
        @ApiResponse(code = 403, message = RESOURCE_IS_FORBIDDEN),
        @ApiResponse(code = 404, message = SERVER_NOT_FOUND)
	})
	@PutMapping("/api/user")
	public @ResponseBody User putUser(
			@RequestBody User user) {
		return dummyService.modifiUser(user);
	}

	@ApiOperation(value = "Busca una coincidencia por el campo nombre en la lista que hemos creado y si existe elimina el objeto User existente." )
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = REQUEST_HAS_SUCCEEDED),
        @ApiResponse(code = 401, message = REQUEST_REQUIRES_USER_AUTHENTICATION),
        @ApiResponse(code = 403, message = RESOURCE_IS_FORBIDDEN),
        @ApiResponse(code = 404, message = SERVER_NOT_FOUND)
	})
	@DeleteMapping("/api/user/{userName}")
	public User deleteUser(
		@ApiParam(
            value = "Nombre de usuario.", 
            required = true)
		@PathVariable("userName") String userName) throws Exception {
		return dummyService.deleteUser(userName);
	}
}