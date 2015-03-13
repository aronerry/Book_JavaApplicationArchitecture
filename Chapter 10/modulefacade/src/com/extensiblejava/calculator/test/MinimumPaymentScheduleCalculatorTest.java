package com.extensiblejava.calculator.test;

import java.io.File;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import com.extensiblejava.calculator.MinimumPaymentScheduleCalculator;
import com.extensiblejava.loan.Loan;
import com.extensiblejava.loan.LoanCalculator;
import com.extensiblejava.loan.Payment;
import com.extensiblejava.loan.PaymentSchedule;
import com.extensiblejava.loan.impl.LoanImpl;

public class MinimumPaymentScheduleCalculatorTest extends TestCase {

	private BigDecimal presentValue; // 本金
	private BigDecimal rate; // 利率
	private int term; // 周期

	public static void main(String[] args)
	{
		String[] testCaseName = { MinimumPaymentScheduleCalculatorTest.class.getName() };

		junit.textui.TestRunner.main(testCaseName);
	}

	protected void setUp() {
		this.presentValue = new BigDecimal("15000.00");
		this.rate = new BigDecimal("12.0");
		this.term = 60;
	}

	/**
	 * 获取本月的还款单（第一个月）
	 */
	public void testMonthlyPayment() {
		LoanCalculator loanCalculator = new MinimumPaymentScheduleCalculator();
		Loan loan = new LoanImpl(loanCalculator);
		PaymentSchedule paymentSchedule = loan.calculatePaymentSchedule(this.presentValue, this.rate, this.term);
		BigDecimal monthlyPayment = loan.getMonthlyPayment();
		assertTrue(monthlyPayment.equals(new BigDecimal("333.67")));
	}

	/**
	 * 获取最后一月的还款单
	 */
	public void testFinalPayment() {
		LoanCalculator loanCalculator = new MinimumPaymentScheduleCalculator();
		Loan loan = new LoanImpl(loanCalculator);
		PaymentSchedule paymentSchedule = loan.calculatePaymentSchedule(this.presentValue, this.rate, this.term);
		BigDecimal finalPayment = loan.getFinalPayment();
		assertTrue(finalPayment.equals(new BigDecimal("333.40")));
	}

	/**
	 * 获取需还款的月份数
	 */
	public void testNumberOfPayments() {
		LoanCalculator loanCalculator = new MinimumPaymentScheduleCalculator();
		Loan loan = new LoanImpl(loanCalculator);
		PaymentSchedule paymentSchedule = loan.calculatePaymentSchedule(this.presentValue, this.rate, this.term);
		Integer numberOfPayments = paymentSchedule.getNumberOfPayments();
		assertTrue(numberOfPayments.intValue() == 60);
	}

	/**
	 * 获取第一个月的利息
	 * @throws Exception
	 */
	public void testFirstInterestPayment() throws Exception {
		LoanCalculator loanCalculator = new MinimumPaymentScheduleCalculator();
		Loan loan = new LoanImpl(loanCalculator);
		PaymentSchedule paymentSchedule = loan.calculatePaymentSchedule(this.presentValue, this.rate, this.term);
		java.util.Iterator payments = paymentSchedule.getPayments();
		Payment payment = (Payment) payments.next();
		assertTrue(payment.getInterest().equals(new BigDecimal("150.00")));
	}

	/**
	 * 获取第一个月的本金
	 * @throws Exception
	 */
	public void testFirstPrincipalPayment() throws Exception {
		LoanCalculator loanCalculator = new MinimumPaymentScheduleCalculator();
		Loan loan = new LoanImpl(loanCalculator);
		PaymentSchedule paymentSchedule = loan.calculatePaymentSchedule(this.presentValue, this.rate, this.term);
		java.util.Iterator payments = paymentSchedule.getPayments();
		Payment payment = (Payment) payments.next();
		assertTrue(payment.getPrincipal().equals(new BigDecimal("183.67")));
	}

	/**
	 * 获取累积的利息
	 */
	public void testCumulativeInterest() {
		LoanCalculator loanCalculator = new MinimumPaymentScheduleCalculator();
		Loan loan = new LoanImpl(loanCalculator);
		PaymentSchedule paymentSchedule = loan.calculatePaymentSchedule(this.presentValue, this.rate, this.term);
		assertTrue(loan.getCumulativeInterest().equals(new BigDecimal("5019.93")));
	}

	/**
	 * 获取最后一个月的本金
	 */
	public void testVerifyFinalPrincipalPayment() {
		LoanCalculator loanCalculator = new MinimumPaymentScheduleCalculator();
		Loan loan = new LoanImpl(loanCalculator);
		PaymentSchedule paymentSchedule = loan.calculatePaymentSchedule(this.presentValue, this.rate, this.term);
		java.util.Iterator payments = paymentSchedule.getPayments();
		Payment payment = null;
		while (payments.hasNext()) {
			payment = (Payment) payments.next();
		}
		assertTrue(payment.getPrincipal().equals(new BigDecimal("330.10")));
	}

	/**
	 *  获取最后一个月的利息
	 */
	public void testVerifyFinalInterestPayment() {
		LoanCalculator loanCalculator = new MinimumPaymentScheduleCalculator();
		Loan loan = new LoanImpl(loanCalculator);
		PaymentSchedule paymentSchedule = loan.calculatePaymentSchedule(this.presentValue, this.rate, this.term);
		java.util.Iterator payments = paymentSchedule.getPayments();
		Payment payment = null;
		while (payments.hasNext()) {
			payment = (Payment) payments.next();
		}
		assertTrue(payment.getInterest().equals(new BigDecimal("3.30")));
	}

}
