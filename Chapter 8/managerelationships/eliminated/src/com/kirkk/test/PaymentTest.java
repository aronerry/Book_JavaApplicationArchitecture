package com.kirkk.test;

import junit.framework.TestCase;
import java.math.BigDecimal;
import java.util.*;
import com.kirkk.cust.*;
import com.kirkk.bill.*;
import com.kirkk.base.*;

/**
 * 相较于前一版本无变化
 *
 */
public class PaymentTest extends TestCase {

	public PaymentTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
	}

	protected void setUp() throws Exception {

	}
	
	/**
	 * Customer不依赖于bill包
	 * DefaultBill不依赖于cust包
	 */
	public void testPayment() {

		Customer customer = new Customer();
		customer.addBill(new DefaultBill(customer, new BigDecimal(500)));

		Iterator bills = customer.getBills().iterator();

		while (bills.hasNext()) {
			Bill bill = (Bill) bills.next();
			BigDecimal paidAmount = bill.pay();
			assertEquals("Paid amount not correct.", new BigDecimal(485).setScale(2), paidAmount);
		}
	}

	/**
	 * DefaultBill不依赖于cust包
	 */
	public void testPaymentWithoutCustomer() {
		Bill bill = new DefaultBill(new DiscountCalculator() {
			public BigDecimal getDiscountAmount() { return new BigDecimal(0.1); }
		}, new BigDecimal(500));

		BigDecimal paidAmount = bill.pay();
		assertEquals("Paid amount not correct.", new BigDecimal(450).setScale(2), paidAmount);
	}

}
