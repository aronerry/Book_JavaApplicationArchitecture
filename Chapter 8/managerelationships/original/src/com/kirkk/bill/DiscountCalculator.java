package com.kirkk.bill;

import java.math.BigDecimal;

/**
 * 折扣率抽象方法
 *
 */
public interface DiscountCalculator {
	public BigDecimal getDiscountAmount();
}