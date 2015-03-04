package com.kirkk.test;

import junit.framework.TestCase;
import java.math.BigDecimal;
import java.util.*;
import com.kirkk.cust.*;
import com.kirkk.bill.*;

public class PaymentTest extends TestCase {

	public PaymentTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
	}

	protected void setUp() throws Exception {

	}

	/**
	 * 由Customer类调用
	 * 
	 * Customer类依赖于bill包
	 */
	public void testPayment() {

		Customer customer = new Customer();
		customer.createBill(new BigDecimal(500)); // 添加一件价格为500的商品

		Iterator bills = customer.getBills().iterator();

		while (bills.hasNext()) {
			Bill bill = (Bill) bills.next();
			BigDecimal paidAmount = bill.pay(); //　计算应付金额
			assertEquals("Paid amount not correct.", new BigDecimal(485).setScale(2), paidAmount);
		}
	}

	/**
	 * Bill类自调用
	 * 
	 * Bill在bill包内部调用
	 */
	public void testPaymentWithoutCustomer() {
		Bill bill = new Bill(new DiscountCalculator() {
			public BigDecimal getDiscountAmount() { return new BigDecimal(0.1); }
		}, new BigDecimal(500));

		BigDecimal paidAmount = bill.pay();
		assertEquals("Paid amount not correct.", new BigDecimal(450).setScale(2), paidAmount);
	}

}
