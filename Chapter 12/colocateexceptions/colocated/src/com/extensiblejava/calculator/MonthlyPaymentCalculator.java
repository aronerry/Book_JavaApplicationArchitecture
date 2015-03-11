package com.extensiblejava.calculator;

import java.math.BigDecimal;

class MonthlyPaymentCalculator {

	/**
	 * 月还款本息，本程序采用等额本息计算公式
	 * 
	 * 等额本息计算公式：〔贷款本金×月利率×（1＋月利率）＾还款月数〕÷〔（1＋月利率）＾还款月数－1〕
	 * 等额本金计算公式：每月还款金额 = （贷款本金 ÷ 还款月数）+（本金 — 已归还本金累计额）×每月利率其中＾符号表示乘方。
	 */
	public BigDecimal calculatePayment(BigDecimal presentValue, BigDecimal rate, int term) {
		// 贷款本金
		double dPresentValue = presentValue.doubleValue();
		// 月利率
		double dRate = rate.doubleValue() / (1200); // 12 * 100, 12个月，100分之几
		// (1+月利率)
		double revisedRate = dRate + 1;
		// 还款月数
		double dTerm = term;
		// (1+月利率)^还款月数
		double powRate = Math.pow(revisedRate, dTerm); // Math.pow(x,y)，求x的y次方
		
		/* 贷款本金 × (1+月利率)^还款月数 */ 
		double left = powRate * dPresentValue;
		/* 月利率 ÷ ((1+月利率)^还款月数-1) */
		double middle = dRate / (powRate - 1);
		
		double right = 1 / (1);
		
		BigDecimal payment = new BigDecimal(left * middle * right);
		return payment.setScale(2, BigDecimal.ROUND_UP);

	}
}