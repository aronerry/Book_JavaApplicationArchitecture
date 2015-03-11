package com.extensiblejava.calculator;

import java.math.*;
import com.extensiblejava.loan.*;
import com.extensiblejava.loan.impl.*;

public class DesiredPaymentScheduleCalculator implements LoanCalculator {
	private BigDecimal presentValue;
	private BigDecimal rate;
	private BigDecimal desiredPayment;
	private int term;
	private BigDecimal cumulativePrincipal = new BigDecimal("0");
	private BigDecimal cumulativeInterest = new BigDecimal("0");

	public DesiredPaymentScheduleCalculator(BigDecimal desiredPayment, BigDecimal presentValue, BigDecimal rate, int term) {
		this.desiredPayment = desiredPayment;
		this.presentValue = presentValue;
		this.rate = rate;
		this.term = term;
	}

	public PaymentSchedule calculatePaymentSchedule() {
		PaymentSchedule paymentSchedule = new PaymentScheduleImpl();
		// 校正月利率
		BigDecimal adjustedRate = rate.divide(new BigDecimal("1200"), 2, BigDecimal.ROUND_UNNECESSARY);

		// 每月还款本息
		MonthlyPaymentCalculator paymentCalculator = new MonthlyPaymentCalculator();
		BigDecimal monthlyPayment = paymentCalculator.calculatePayment(this.presentValue, this.rate, this.term);
		
		// 若期望的月还款金额小于计算得到的金额，抛异常
		if (this.desiredPayment.doubleValue() < monthlyPayment.doubleValue()) {
			throw new LoanException("The desired payment is less than the minimum monthly allowed of " + monthlyPayment.doubleValue() +
					" for the loan term of " + this.term + " at a rate of " + this.rate.toString());
		}
		
		BigDecimal loanBalance = new BigDecimal(this.presentValue.toString());
		// 每月固定还款额desiredPayment
		while (loanBalance.doubleValue() > this.desiredPayment.doubleValue()) {
			// 1. 月还款利息
			BigDecimal interest = loanBalance.multiply(adjustedRate);
			interest = interest.setScale(2, BigDecimal.ROUND_HALF_UP);
			// 2. 月还款本金
			BigDecimal principal = this.desiredPayment.subtract(interest);
			principal = principal.setScale(2, BigDecimal.ROUND_HALF_UP);
			// 3. 组装Payment，并添加至还款计划PaymentSchedule
			Payment payment = new PaymentImpl(principal, interest);
			paymentSchedule.addPayment(payment);

			// 累积利息
			this.cumulativeInterest = this.cumulativeInterest.add(interest).setScale(2, BigDecimal.ROUND_HALF_UP);
			// 累积本金
			this.cumulativePrincipal = this.cumulativePrincipal.add(principal).setScale(2, BigDecimal.ROUND_HALF_UP);
			// 减去已还本金
			loanBalance = loanBalance.subtract(principal);
		}

		// 最后一次还款，利息、本金、累积利息、累积本金
		BigDecimal interest = loanBalance.multiply(adjustedRate).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal principal = loanBalance.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.cumulativeInterest = this.cumulativeInterest.add(interest).setScale(2, BigDecimal.ROUND_HALF_UP);
		this.cumulativePrincipal = this.cumulativePrincipal.add(principal).setScale(2, BigDecimal.ROUND_HALF_UP);
		// 添加最后一次的Payment
		Payment payment = new PaymentImpl(principal, interest);
		paymentSchedule.addPayment(payment);
		
		return paymentSchedule;
	}

	public BigDecimal getCumulativeInterest() { return this.cumulativeInterest; }
	public BigDecimal getCumulativePrincipal() { return this.cumulativePrincipal; }
}