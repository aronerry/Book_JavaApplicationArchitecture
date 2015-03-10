package com.extensiblejava.payroll;

import java.math.BigDecimal;

/**
 * 相较于上一版本无变化
 *
 */
public class Payroll {

	private BigDecimal salary;
	public Payroll(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal run() {
		//calculate the pay...
		return new BigDecimal("20000.00");
	}
}