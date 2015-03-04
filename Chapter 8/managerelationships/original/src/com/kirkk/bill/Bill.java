package com.kirkk.bill;

import java.math.BigDecimal;

/**
 * 根据折扣率计算应付金额
 * 
 */
public class Bill {

	private BigDecimal chargeAmount;
	private DiscountCalculator discounter;

	public Bill(DiscountCalculator discounter, BigDecimal chargeAmount) {
			this.discounter = discounter;
			this.chargeAmount = chargeAmount;
	}
	public BigDecimal getChargeAmount() {
		return this.chargeAmount;
	}

	/**
	 * BigDecimal.add() 加法运算
	 * BigDecimal.subtract() 减法运算
	 * BigDecimal.multiply() 乘法运算
	 * BigDecimal.divide() 除法运算
	 * 
	 * BigDecimal.setScale() 设置小数点位数
	 * BigDecimal.ROUND_HALF_UP 小数保留模式，四舍五入
	 * 
	 * @return
	 */
	public BigDecimal pay() {
		// 折扣
		BigDecimal discount = new BigDecimal(1).subtract(this.discounter.getDiscountAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);
		// 应付金额
		BigDecimal paidAmount = this.chargeAmount.multiply(discount).setScale(2);
		//make the payment...
		return paidAmount;
	}

}