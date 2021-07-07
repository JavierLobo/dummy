package com.javierlobo.dummy.controllers;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DummyController.class)
@WebAppConfiguration
public abstract class AbstractRestControlTest {
	
	protected MockMvc mvc;
	protected String url;

	@Autowired
	WebApplicationContext webApplicationContext;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	protected void setUp() {
		mvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.build();
	}

    protected MockHttpServletResponse getPetition() throws Exception {
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders
						.get(new URI(this.getUrl()))
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
	
		return mvcResult.getResponse();
	}
	
    protected MockHttpServletResponse postPetition(Object inputClass) throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(this.getUrl())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.mapToJson(inputClass)))
				.andReturn();

		return mvcResult.getResponse();
	}
	protected MockHttpServletResponse putPetition(Object inputClass) throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(this.getUrl())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.mapToJson(inputClass)))
				.andReturn();

		return mvcResult.getResponse();
	}

	protected MockHttpServletResponse deletePetition() throws Exception {
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders
						.delete(this.getUrl()))
				.andReturn();
		return mvcResult.getResponse();
	}
	
	protected String mapToJson(Object obj) 
			throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
	
	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
      
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
}
