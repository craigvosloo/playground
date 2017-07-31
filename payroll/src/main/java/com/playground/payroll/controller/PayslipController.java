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

import com.playground.payroll.service.payslip.PayslipService;
import com.playground.payroll.service.payslip.dto.PayslipDTO;
import com.playground.payroll.service.payslip.dto.PayslipPeriodDTO;
import com.playground.payroll.service.payslip.dto.PayslipRequestDTO;

/**
 * Rest controller for Payslip
 * 
 * @author	Craig Vosloo
 * @version	%I%, %G%
 */
@RestController
@RequestMapping("/payslip")
public class PayslipController { 
	
	@Autowired
	private PayslipService payslipService;
	
	 /**
     * @see PayslipService#generatePayslip
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public HttpEntity<PayslipDTO> generatePayslip(@Valid @RequestBody PayslipRequestDTO payslipRequest) {  
	
    	return new ResponseEntity<PayslipDTO>(payslipService.generatePayslip(payslipRequest), HttpStatus.OK);    	
    	
    }
    
	 /**
     * @see PayslipService#savePayslip
     */
    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public HttpEntity<PayslipDTO> savePayslip(@PathVariable Long employeeId, @RequestBody PayslipDTO payslipDTO) {  
	
    	return new ResponseEntity<PayslipDTO>(payslipService.savePayslip(employeeId, payslipDTO), HttpStatus.OK);    	
    	
    }
    
    /**
     * @see PayslipService#getPayslipsByEmployee
     */
    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.GET, produces = "application/json")
    public HttpEntity<List<PayslipDTO>> getPayslipsByEmployee(@PathVariable Long employeeId) {  
	
    	return new ResponseEntity<List<PayslipDTO>>(payslipService.getPayslipsByEmployee(employeeId), HttpStatus.OK);    	
    	
    }
    
	 /**
     * @see PayslipService#getPayslipPeriodsForEmployee
     */
    @RequestMapping(value = "/period/employee/{employeeId}", method = RequestMethod.GET, produces = "application/json")
    public HttpEntity<List<PayslipPeriodDTO>> getPayslipPeriodsForEmployee(@PathVariable Long employeeId) {  
	
    	return new ResponseEntity<List<PayslipPeriodDTO>>(payslipService.getPayslipPeriodsForEmployee(employeeId), HttpStatus.OK);    	
    	
    }
    
}
