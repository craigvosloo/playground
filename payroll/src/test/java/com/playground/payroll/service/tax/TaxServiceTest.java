package com.playground.payroll.service.tax;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxServiceTest {
	
	@Autowired
	private TaxService taxService;
	
	@Test
	public void testCalculateMonthlyTax() {
		BigDecimal monthlyTax = taxService.calculateMonthlyTax(new BigDecimal(60050));
		
		assertNotNull(monthlyTax);		
		assertTrue(monthlyTax.compareTo(new BigDecimal("921.94")) == 0);
	}
}