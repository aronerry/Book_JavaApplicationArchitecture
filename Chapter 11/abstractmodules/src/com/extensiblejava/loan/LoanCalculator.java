package com.extensiblejava.loan;

import java.math.*;

/**
 * 相较于10.4.1版本基本一致
 *
 */
public interface LoanCalculator {
	public PaymentSchedule calculatePaymentSchedule();
	public BigDecimal getCumulativeInterest();
	public BigDecimal getCumulativePrincipal();

}