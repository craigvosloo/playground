package com.playground.payroll.service.employee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.playground.payroll.service.employee.dto.EmployeeDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Test
	public void testFindAll() {
		List<EmployeeDTO> employeeList = employeeService.findAll();
		
		assertTrue(employeeList.size() > 0);
		
		assertEquals("Andrew Baker", employeeList.get(0).getFirstName() + " " + employeeList.get(0).getLastName());
		assertEquals("Chris Davies", employeeList.get(1).getFirstName() + " " + employeeList.get(1).getLastName());
	}
	
	@Test
	public void testFindOne() {
		EmployeeDTO employee = employeeService.findOne(1l);
		
		assertNotNull(employee);
		
		assertEquals("Andrew Baker", employee.getFirstName() + " " + employee.getLastName());
	}
	
	@Test
	public void testSaveEmployee() {
		EmployeeDTO employee = new EmployeeDTO();
		
		employee.setFirstName("Test");
		employee.setLastName("Tester");
		employee.setAnnualSalary(new BigDecimal(1));
		employee.setPensionContribution(new BigDecimal(2));
		employee.setStartDate(LocalDate.now());
		
		EmployeeDTO savedEmployee = employeeService.saveEmployee(employee);
		
		assertNotNull(savedEmployee);
		assertNotNull(savedEmployee.getId());
		
		assertEquals(employee.getFirstName(), savedEmployee.getFirstName());
		assertEquals(employee.getLastName(), savedEmployee.getLastName());
		assertEquals(employee.getAnnualSalary(), savedEmployee.getAnnualSalary());
		assertEquals(employee.getPensionContribution(), savedEmployee.getPensionContribution());
		assertEquals(employee.getStartDate(), savedEmployee.getStartDate());		
			
	}
}