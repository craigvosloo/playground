package com.playground.payroll.service.payslip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.playground.payroll.service.payslip.dto.PayslipDTO;
import com.playground.payroll.service.payslip.dto.PayslipPeriodDTO;
import com.playground.payroll.service.payslip.dto.PayslipRequestDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayslipServiceTest {
	
	@Autowired
	private PayslipService payslipService;
	
	@Test
	public void testPayslipService() {
		
		List<PayslipDTO> payslipList = payslipService.getPayslipsByEmployee(1l);
		assertNotNull(payslipList);
		assertTrue(payslipList.size() > 0);
		
		PayslipDTO payslip = payslipList.get(0);		
		assertNotNull(payslip.getId());
		
		List<PayslipPeriodDTO> payslipPeriodList = payslipService.getPayslipPeriodsForEmployee(1l);
		assertNotNull(payslipPeriodList);
		assertTrue(payslipPeriodList.size() > 0);
		
		PayslipPeriodDTO payslipPeriodDTO = payslipPeriodList.get(0);
		assertNotNull(payslipPeriodDTO.getDisplayValue());
		assertNotNull(payslipPeriodDTO.getPayslipPeriodDate());
		
		PayslipRequestDTO payslipRequestDTO = new PayslipRequestDTO();
		payslipRequestDTO.setEmployeeId(1l);
		payslipRequestDTO.setPayslipDate(payslip.getPayslipDate());
		
		PayslipDTO generatedPayslip = payslipService.generatePayslip(payslipRequestDTO);
		assertNotNull(generatedPayslip);
		
		assertEquals(generatedPayslip.getGrossIncome(), payslip.getGrossIncome());
		assertTrue(generatedPayslip.getIncomeTax().compareTo(payslip.getIncomeTax()) == 0);
		assertTrue(generatedPayslip.getNetIncome().compareTo(payslip.getNetIncome()) == 0);
		assertEquals(generatedPayslip.getPayslipDate(), payslip.getPayslipDate());
		assertTrue(generatedPayslip.getPensionContribution().compareTo(payslip.getPensionContribution()) == 0);
		assertEquals(generatedPayslip.getProrated(), payslip.getProrated());
		
		PayslipDTO savedPayslip = payslipService.savePayslip(1l, generatedPayslip);
		
		assertNotNull(savedPayslip.getId());
		
		assertEquals(generatedPayslip.getGrossIncome(), savedPayslip.getGrossIncome());
		assertTrue(generatedPayslip.getIncomeTax().compareTo(savedPayslip.getIncomeTax()) == 0);
		assertTrue(generatedPayslip.getNetIncome().compareTo(savedPayslip.getNetIncome()) == 0);
		assertEquals(generatedPayslip.getPayslipDate(), savedPayslip.getPayslipDate());
		assertTrue(generatedPayslip.getPensionContribution().compareTo(savedPayslip.getPensionContribution()) == 0);
		assertEquals(generatedPayslip.getProrated(), savedPayslip.getProrated());
		
	}
}