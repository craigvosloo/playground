package com.playground.payroll.service.employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playground.payroll.domain.Employee;
import com.playground.payroll.repository.EmployeeRepository;
import com.playground.payroll.service.employee.dto.EmployeeDTO;

/**
 * Service used for all methods regarding employees
 * 
 * @author	Craig Vosloo
 * @version	%I%, %G%
 * @see EmployeeService
 * @since 1.0
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepositiory;
	
	@Autowired
	private Mapper mapper;

	/**
	 * Method to retrieve a list of all employees
	 * @return the list of employees
	 * @see EmployeeDTO
	 */
	@Override
	public List<EmployeeDTO> findAll() {
		List<Employee> employeeList = (List<Employee>) employeeRepositiory.findAll();
		List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
		
		Iterator<Employee> employeeIterator = employeeList.iterator();
		
		while (employeeIterator.hasNext()) {
			EmployeeDTO employeeDTO = mapper.map(employeeIterator.next(),EmployeeDTO.class);
			employeeDTOList.add(employeeDTO);
		}
		
		return employeeDTOList;
	}

}
