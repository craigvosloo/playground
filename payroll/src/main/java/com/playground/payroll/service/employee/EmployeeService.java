package com.playground.payroll.service.employee;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.playground.payroll.service.employee.dto.EmployeeDTO;

/**
 * Interface for the Employee Service.
 * 
 * @author	Craig Vosloo
 * @version	%I%, %G%
 * @see EmployeeServiceImpl
 * @since 1.0
 */
@Validated
public interface EmployeeService {
	
	/**@see EmployeeServiceImpl#findAll() */
	public List<EmployeeDTO> findAll();
	
	/**@see EmployeeServiceImpl#findOne(Long) */
	public EmployeeDTO findOne(Long id);
	
	/**@see EmployeeServiceImpl#saveEmployee(EmployeeDTO) */
	public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

}
