package com.extensiblejava.applicant;

import com.extensiblejava.loan.*;

/**
 * 获取还款计划表，以及Loan（可调用各类数据方法）
 *
 */
public class Applicant {
	Loan loan;
	PaymentSchedule paymentSchedule;
	
	public Applicant (Loan loan) {
		this.loan = loan;
		this.paymentSchedule = this.loan.calculatePaymentSchedule();
	}
	
	public Loan obtainLoanInformation () {
		return loan;
		//determine if applicant can afford the payments by running a credit check.
	}
}