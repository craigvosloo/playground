package com.playground.payroll.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.playground.payroll.util.RestControllerTest;

public class PayslipControllerTest extends RestControllerTest {
	
	private static final String CONTROLLER_BASE_URL = "/payslip";
	
	@Test
	public void getPayslipsByEmployee() throws Exception {
		mockMvc.perform(get(CONTROLLER_BASE_URL + "/employee/1")
				.contentType(contentType))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$[0].payslipDisplayDate").value("March 2016"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].grossIncome").value(5004))
		        .andExpect(MockMvcResultMatchers.jsonPath("$[1].netIncome").value(4082.06))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].pensionContributionFormatted").value("R 450"));
	}
	
	@Test
	public void getPayslipPeriodsForEmployee() throws Exception {
		mockMvc.perform(get(CONTROLLER_BASE_URL + "/period/employee/1")
				.contentType(contentType))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$[0].displayValue").value("March 2016"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].displayValue").value("April 2016"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$[2].displayValue").value("May 2016"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[3].displayValue").value("June 2016"));
	}
	
}