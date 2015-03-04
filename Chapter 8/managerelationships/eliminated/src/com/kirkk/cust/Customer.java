package com.kirkk.cust;

import java.util.*;
import java.math.BigDecimal;
import com.kirkk.base.*;

/**
 * 相较于前一版本无变化
 *
 */
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

	public void addBill(Bill bill) {
		if (bills == null) {
			bills = new ArrayList();
		}
		bills.add(bill);
	}

}
