package com.playground.payroll.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.playground.payroll.util.RestControllerTest;

public class EmployeeControllerTest extends RestControllerTest {
	
	private static final String CONTROLLER_BASE_URL = "/employee";
	
	@Test
	public void findAll() throws Exception {
		mockMvc.perform(get(CONTROLLER_BASE_URL + "/")
				.contentType(contentType))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Andrew"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Baker"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("Chris"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value("Davies"));
	}
	
	@Test
	public void findOne() throws Exception {
		mockMvc.perform(get(CONTROLLER_BASE_URL + "/1")
				.contentType(contentType))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Andrew"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Baker"));
	}
	
	
	
}