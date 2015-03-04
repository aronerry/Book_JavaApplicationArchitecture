package com.kirkk.cust;

import java.util.*;
import java.math.BigDecimal;
import com.kirkk.bill.*;

public class Customer implements DiscountCalculator {
	private List bills;

	public BigDecimal getDiscountAmount() {
		if (bills.size() > 5) {
			return new BigDecimal(0.1);
		} else {
			return new BigDecimal(0.03);
		}
	}

	public List getBills() {
		return this.bills;
	}

	public void createBill(BigDecimal chargeAmount) {
		Bill bill = new Bill(this, chargeAmount);
		if (bills == null) {
			bills = new ArrayList();
		}
		bills.add(bill);
	}

}
