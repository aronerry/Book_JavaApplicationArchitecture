package com.kirkk.calc;

import java.math.*;

/**
 * 集成了原始版本Customer中d getDiscountAmount()方法
 *
 */
public class DiscountCalculator {
	private Integer numBills;

	public DiscountCalculator(Integer numBills) {
		this.numBills = numBills;
	}

	public BigDecimal getDiscountAmount() {
		if (numBills.intValue() > 5) {
			return new BigDecimal(0.1);
		} else {
			return new BigDecimal(0.03);
		}
	}
}