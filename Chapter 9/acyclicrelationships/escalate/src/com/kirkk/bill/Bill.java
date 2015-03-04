package com.kirkk.bill;

import java.math.BigDecimal;

/**
 * 相较于前一版本，移除了pay操作
 *
 */
public class Bill {

	private BigDecimal chargeAmount;

	public Bill(BigDecimal chargeAmount) {
			this.chargeAmount = chargeAmount;
	}
	public BigDecimal getChargeAmount() {
		return this.chargeAmount;
	}

}