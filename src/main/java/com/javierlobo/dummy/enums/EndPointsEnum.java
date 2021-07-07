package com.javierlobo.dummy.enums;

/**
 * 
 */
public enum EndPointsEnum {

	URL_HOME ("/"),
	
	URL_HOLA_MUNDO("/holamundo"),
	
	URL_USER_LIST ("/api/user/list"), 

	URL_USER ("/api/user"),
	
	URL_USER_PEPE ("/api/user/Pepe%20Martinez"),
	
	URL_BAD ("/badUrl"),
	
	FORBIDDEN("forbidden"), 
	
	UNAUTHORIZED("unauthorized"), 
	
	INTERNAL_SERVER_ERROR("internalServerErrorvoy ");
	
	private final String url;
	
	EndPointsEnum(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}
	
}
