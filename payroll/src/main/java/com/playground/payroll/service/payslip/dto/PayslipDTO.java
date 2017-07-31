package com.playground.payroll.service.payslip.dto;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/**
 * DTO containing a payslip record.
 * 
 * @author Craig Vosloo
 * @version %I%, %G%
 */
public class PayslipDTO {

	private Long id;

	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM yyyy");
	private static final String pattern = "R ###,###";
	private static final DecimalFormat decimalFormat = new DecimalFormat(pattern);
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate payslipDate;
	
	private BigDecimal grossIncome;
	private String grossIncomeFormatted;
	private BigDecimal incomeTax;
	private String incomeTaxFormatted;
	private BigDecimal netIncome;
	private String netIncomeFormatted;
	private BigDecimal pensionContribution;
	private String pensionContributionFormatted;
	private Boolean prorated;
	private String payslipDisplayDate;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getPayslipDate() {
		return payslipDate;
	}
	public void setPayslipDate(LocalDate payslipDate) {
		this.payslipDate = payslipDate;
		this.payslipDisplayDate = payslipDate.format(dtf);
	}
	public BigDecimal getGrossIncome() {
		return grossIncome;
	}
	public void setGrossIncome(BigDecimal grossIncome) {
		this.grossIncome = grossIncome;
		this.grossIncomeFormatted = decimalFormat.format(grossIncome);
	}
	public BigDecimal getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(BigDecimal incomeTax) {
		this.incomeTax = incomeTax;
		this.incomeTaxFormatted = decimalFormat.format(incomeTax);
	}
	public BigDecimal getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(BigDecimal netIncome) {
		this.netIncome = netIncome;
		this.netIncomeFormatted = decimalFormat.format(netIncome);
	}
	public BigDecimal getPensionContribution() {
		return pensionContribution;
	}
	public void setPensionContribution(BigDecimal pensionContribution) {
		this.pensionContribution = pensionContribution;
		this.pensionContributionFormatted = decimalFormat.format(pensionContribution);
	}
	public Boolean getProrated() {
		return prorated;
	}
	public void setProrated(Boolean prorated) {
		this.prorated = prorated;
	}
	public String getPayslipDisplayDate() {
		return payslipDisplayDate;
	}
	public String getGrossIncomeFormatted() {
		return grossIncomeFormatted;
	}
	public String getIncomeTaxFormatted() {
		return incomeTaxFormatted;
	}
	public String getNetIncomeFormatted() {
		return netIncomeFormatted;
	}
	public String getPensionContributionFormatted() {
		return pensionContributionFormatted;
	}
}
