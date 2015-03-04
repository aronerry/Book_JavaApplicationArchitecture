package com.kirkk.cust;

import java.math.BigDecimal;

/**
 * 相较于前一版本无变化（由bill包转移至cust包）
 *
 */
public interface DiscountCalculator {
	public BigDecimal getDiscountAmount();
}