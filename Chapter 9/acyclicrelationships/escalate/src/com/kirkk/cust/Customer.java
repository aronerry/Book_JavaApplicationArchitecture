package com.kirkk.cust;

import java.util.*;
import java.math.BigDecimal;
import com.kirkk.bill.*;

/**
 * 相较于前一版本无变化
 *
 */
public class Customer {
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
		Bill bill = new Bill(chargeAmount);
		if (bills == null) {
			bills = new ArrayList();
		}
		bills.add(bill);
	}

}
