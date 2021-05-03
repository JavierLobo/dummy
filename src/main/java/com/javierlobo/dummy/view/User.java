package com.javierlobo.dummy.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "<p><b>Clase Usuario (User)</b></p>"
		+ "Solo contiene tres propiedades:\n"
		+ "<ul>\n"
		+ " <li>userName: Nombre del usuario.</li>\n"
		+ " <li>salary: Salario que combra el usuario.</li>\n"
		+ " <li>status: Estado del usuario.</li>\n"
		+ "</ul>"
		+ "<p>Ejemplo:</p>"
		+ "<div style='border:1px solid black; background-color:#EEEEFF; width:300px; overflow:auto; padding:10px;'>"
		+ "<code style='font-size:0.9em;'>  {\n<br>"
		+ "    \"salary\": 1000,\n<br>"
		+ "    \"userName\": \"Manolo\",\n<br>"
		+ "    \"status\": \"active or inactive or banned\"\n<br>"
		+ "  }\n"
		+ "</code>"
		+ "</div><br><br>")
public class User {

    @ApiModelProperty(required = true, position = 2,
    		example = "Manolo", 
    		value = "Nombre del usuario.")
    private String userName;

    @ApiModelProperty(position = 1,
    		example = "1000",
    		value = "Salario del usuario.")
    private Integer salary;

    @ApiModelProperty(required = true, position = 3,
    		example = "active or inactive or banned",
    		value = "Valores permitidos:",
    		allowableValues = "active, inactive, banned")
    private String status;
	
    public User() {
		super();
	}
	
	public User(String userName, Integer salary, String status) {
		super();
		this.userName = userName;
		this.salary = salary;
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}