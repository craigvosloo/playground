package com.playground.payroll.controller;

import java.io.Serializable;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.playground.payroll.service.employee.EmployeeService;
import com.playground.payroll.service.payslip.PayslipService;
import com.playground.payroll.service.payslip.dto.PayslipDTO;

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
     * @see EmployeeService#findAll
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public HttpEntity<PayslipDTO> generatePayslip(@Valid @RequestBody PayslipRequestDTO payslipRequest) {  
	
    	return new ResponseEntity<PayslipDTO>(payslipService.generatePayslip(payslipRequest.getEmployeeId(), payslipRequest.getPayslipDate()), HttpStatus.OK);    	
    	
    }
    
    public static class PayslipRequestDTO implements Serializable {

		private static final long serialVersionUID = 1L;

		@NotNull
    	private Long employeeId;
    	@NotNull
    	private Date payslipDate;
    	
    	public PayslipRequestDTO() {}
		
    	public Long getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(Long employeeId) {
			this.employeeId = employeeId;
		}
		public Date getPayslipDate() {
			return payslipDate;
		}
		public void setPayslipDate(Date payslipDate) {
			this.payslipDate = payslipDate;
		}   	
    }

}
