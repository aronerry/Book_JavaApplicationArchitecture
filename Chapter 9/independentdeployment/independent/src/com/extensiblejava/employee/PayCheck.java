package com.extensiblejava.employee;

import java.math.BigDecimal;

/**
 * 相较于上一版本无变化
 *
 */
public class PayCheck {
	private BigDecimal pay;
	public PayCheck(BigDecimal pay) {
		this.pay = pay;
	}

	public BigDecimal getPay() { return this.pay; }
}