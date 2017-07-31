package com.playground.payroll.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.playground.payroll.service.employee.EmployeeService;
import com.playground.payroll.service.employee.dto.EmployeeDTO;

/**
 * Rest controller for Employee.
 * 
 * @author	Craig Vosloo
 * @version	%I%, %G%
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService; 
	
	 /**
     * @see EmployeeService#findAll()
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public HttpEntity<List<EmployeeDTO>> findAll() {  
	
    	return new ResponseEntity<List<EmployeeDTO>>(employeeService.findAll(), HttpStatus.OK);    	
    	
    }
    
    /**
     * @see EmployeeService#findOne()
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public HttpEntity<EmployeeDTO> findOne(@PathVariable Long id) {  
	
    	return new ResponseEntity<EmployeeDTO>(employeeService.findOne(id), HttpStatus.OK);    	
    	
    }
    
	 /**
     * @see EmployeeService#saveEmployee(EmployeeDTO)
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public HttpEntity<EmployeeDTO> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {  
	
    	return new ResponseEntity<EmployeeDTO>(employeeService.saveEmployee(employeeDTO), HttpStatus.OK);    	
    	
    }

}
