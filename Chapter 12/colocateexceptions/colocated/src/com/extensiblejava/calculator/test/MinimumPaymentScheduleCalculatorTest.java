package com.extensiblejava.calculator.test;

import junit.framework.*;
import junit.textui.*;

import java.math.*;

import com.extensiblejava.loan.*;
import com.extensiblejava.calculator.*;

import org.springframework.context.*;
import org.springframework.context.support.*;

public class MinimumPaymentScheduleCalculatorTest extends TestCase
{

	private BigDecimal presentValue;
	private BigDecimal rate;
	private int term;

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
		ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath*:com/extensiblejava/calculator/test/TestContext.xml");
		MinimumPaymentScheduleCalculator loanCalculator = (MinimumPaymentScheduleCalculator) appContext.getBean("loanCalculator");
		Loan loan = new LoanMock(loanCalculator);
		PaymentSchedule paymentSchedule = loan.calculatePaymentSchedule(this.presentValue, this.rate, this.term);
		BigDecimal monthlyPayment = loan.getMonthlyPayment();
		assertTrue(monthlyPayment.equals(new BigDecimal("333.67")));
	}

	/**
	 * 获取最后一月的还款单
	 */
	public void testFinalPayment() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath*:com/extensiblejava/calculator/test/TestContext.xml");
		MinimumPaymentScheduleCalculator loanCalculator = (MinimumPaymentScheduleCalculator) appContext.getBean("loanCalculator");
		Loan loan = new LoanMock(loanCalculator);
		PaymentSchedule paymentSchedule = loan.calculatePaymentSchedule(this.presentValue, this.rate, this.term);
		BigDecimal finalPayment = loan.getFinalPayment();
		assertTrue(finalPayment.equals(new BigDecimal("333.40")));
	}

	/**
	 * 获取需还款的月份数
	 */
	public void testNumberOfPayments() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath*:com/extensiblejava/calculator/test/TestContext.xml");
		MinimumPaymentScheduleCalculator loanCalculator = (MinimumPaymentScheduleCalculator) appContext.getBean("loanCalculator");
		Loan loan = new LoanMock(loanCalculator);
		PaymentSchedule paymentSchedule = loan.calculatePaymentSchedule(this.presentValue, this.rate, this.term);
		Integer numberOfPayments = paymentSchedule.getNumberOfPayments();
		assertTrue(numberOfPayments.intValue() == 60);
	}

	/**
	 * 获取第一个月的利息
	 * @throws Exception
	 */
	public void testFirstInterestPayment() throws Exception {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath*:com/extensiblejava/calculator/test/TestContext.xml");
		MinimumPaymentScheduleCalculator loanCalculator = (MinimumPaymentScheduleCalculator) appContext.getBean("loanCalculator");
		Loan loan = new LoanMock(loanCalculator);
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
		ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath*:com/extensiblejava/calculator/test/TestContext.xml");
		MinimumPaymentScheduleCalculator loanCalculator = (MinimumPaymentScheduleCalculator) appContext.getBean("loanCalculator");
		Loan loan = new LoanMock(loanCalculator);
		PaymentSchedule paymentSchedule = loan.calculatePaymentSchedule(this.presentValue, this.rate, this.term);
		java.util.Iterator payments = paymentSchedule.getPayments();
		Payment payment = (Payment) payments.next();
		assertTrue(payment.getPrincipal().equals(new BigDecimal("183.67")));
	}

	/**
	 * 获取累积的利息
	 */
	public void testCumulativeInterest() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath*:com/extensiblejava/calculator/test/TestContext.xml");
		MinimumPaymentScheduleCalculator loanCalculator = (MinimumPaymentScheduleCalculator) appContext.getBean("loanCalculator");
		Loan loan = new LoanMock(loanCalculator);
		PaymentSchedule paymentSchedule = loan.calculatePaymentSchedule(this.presentValue, this.rate, this.term);
		assertTrue(loan.getCumulativeInterest().equals(new BigDecimal("5019.93")));
	}

	/**
	 * 获取最后一个月的本金
	 */
	public void testVerifyFinalPrincipalPayment() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath*:com/extensiblejava/calculator/test/TestContext.xml");
		MinimumPaymentScheduleCalculator loanCalculator = (MinimumPaymentScheduleCalculator) appContext.getBean("loanCalculator");
		Loan loan = new LoanMock(loanCalculator);
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
		ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath*:com/extensiblejava/calculator/test/TestContext.xml");
		MinimumPaymentScheduleCalculator loanCalculator = (MinimumPaymentScheduleCalculator) appContext.getBean("loanCalculator");
		Loan loan = new LoanMock(loanCalculator);
		PaymentSchedule paymentSchedule = loan.calculatePaymentSchedule(this.presentValue, this.rate, this.term);
		java.util.Iterator payments = paymentSchedule.getPayments();
		Payment payment = null;
		while (payments.hasNext()) {
			payment = (Payment) payments.next();
		}
		assertTrue(payment.getInterest().equals(new BigDecimal("3.30")));
	}

}
