package com.extensiblejava.loan.impl;
import java.util.*;
import com.extensiblejava.loan.*;

/**
 * 所有周期的还款计划
 *
 */
public class PaymentScheduleImpl implements PaymentSchedule {
	private TreeMap payments = new TreeMap();
	public void addPayment(Payment payment) {
		payments.put(new Integer(payments.size() + 1), payment);
	}

	public Iterator getPayments() {
		return payments.values().iterator();
	}

	public Integer getNumberOfPayments() { return new Integer(payments.size()); }
}