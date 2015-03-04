package com.kirkk.base;

import java.math.BigDecimal;

/**
 * 相较于前一版本无变化（由cust包转移至base包）
 *
 */
public interface DiscountCalculator {
	public BigDecimal getDiscountAmount();
}