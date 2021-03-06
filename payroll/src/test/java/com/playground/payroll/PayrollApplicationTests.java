package com.playground.payroll;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.playground.payroll.controller.EmployeeController;
import com.playground.payroll.controller.PayslipController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayrollApplicationTests {
	
	@Autowired
    protected EmployeeController employeeController;
	
	@Autowired
    protected PayslipController payslipController;
	
	@Test
	public void contextLoads() {
		assertThat(employeeController).isNotNull();
		assertThat(payslipController).isNotNull();
	}

}
