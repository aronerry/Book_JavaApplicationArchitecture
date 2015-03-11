package com.extensiblejava.facade;

import java.math.BigDecimal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.extensiblejava.loan.Loan;
import com.extensiblejava.loan.PaymentSchedule;

public class LoanFacade {
	
	public PaymentSchedule calculatePaymentSchedule(BigDecimal presentValue, BigDecimal rate, int term) {
		ApplicationContext appContext = new FileSystemXmlApplicationContext("src/com/extensiblejava/facade/AppContext.xml");
		Loan loan = (Loan) appContext.getBean("loan");
		PaymentSchedule paymentSchedule = loan.calculatePaymentSchedule(presentValue, rate, term);
		return paymentSchedule;
	}
	
	public BigDecimal getMonthlyPayment(BigDecimal presentValue, BigDecimal rate, int term) {
		ApplicationContext appContext = new FileSystemXmlApplicationContext("src/com/extensiblejava/facade/AppContext.xml");
		Loan loan = (Loan) appContext.getBean("loan");
		// 必须计算PaymentSchedule，否则loan.getMonthlyPayment()不能正常运行
		// 连接MinimumPaymentScheduleCalculator计算PaymentSchedule
		PaymentSchedule paymentSchedule = loan.calculatePaymentSchedule(presentValue, rate, term);
		BigDecimal monthlyPayment = loan.getMonthlyPayment();
		return monthlyPayment;
	}
}