package com.javierlobo.dummy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@Autowired
	private IDummyService dummyService;
	
	@ApiOperation(value = "Retorna Hola mundo." )//, description = "shows hello world")
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "The request has succeeded."),
        @ApiResponse(code = 401, message = "The request requires user authentication."),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden."),
        @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI.")
	})
    @GetMapping("/")
    public String hello() {
        return "Hello World!!!";
    }
	
	@ApiOperation(value = "Obtiene un objeto 'User' aportando un nombre para identificarlo." )
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "The request has succeeded."),
        @ApiResponse(code = 401, message = "The request requires user authentication."),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden."),
        @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI.")
	})
	@GetMapping("/api/user/{userName}")
	public User getUser(
		@ApiParam(
            value = "Nombre de usuario.", 
            required = true)
		@PathVariable("userName") String userName) {
        return dummyService.getUserByName(userName);
    }
	
	@ApiOperation(value = "Obtiene una lista de todos los objetos 'User' que hemos creado hasta el momento." )
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "The request has succeeded."),
        @ApiResponse(code = 401, message = "The request requires user authentication."),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden."),
        @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI.")
	})
	@GetMapping("/api/user/list")
	public List<User> getUserList() {
        return dummyService.getUserList();
    }
	
	@ApiOperation(value = "Crea un objeto User en una lista con los valores que le hemos aportado." )
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "The request has succeeded."),
        @ApiResponse(code = 401, message = "The request requires user authentication."),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden."),
        @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI.")
	})
	@PostMapping("/api/user")
	public User postUser(User user) {
		return dummyService.createUser(user);
	}
	
	@ApiOperation(value = "Busca una coincidencia por el campo nombre en la lista que hemos creado, si existe, modifica el objeto User existente." )
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "The request has succeeded."),
        @ApiResponse(code = 401, message = "The request requires user authentication."),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden."),
        @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI.")
	})
	@PutMapping("/api/user")
	public User putUser(User user) {
		return dummyService.modifiUser(user);
	}

	@ApiOperation(value = "Busca una coincidencia por el campo nombre en la lista que hemos creado y si existe elimina el objeto User existente." )
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "The request has succeeded."),
        @ApiResponse(code = 401, message = "The request requires user authentication."),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden."),
        @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI.")
	})
	@DeleteMapping("/api/user/{userName}")
	public User deleteUser(
		@ApiParam(
            value = "Nombre de usuario.", 
            required = true)
		@PathVariable("userName") String userName) {
		return dummyService.deleteUser(userName);
	}
}