package com.kirkk.cust;

import java.util.*;
import java.math.BigDecimal;
import com.kirkk.bill.*;

/**
 * 定义折扣率，并添加Bill
 *
 */
public class Customer implements DiscountCalculator {
	private List bills;

	public BigDecimal getDiscountAmount() {
		if (bills.size() > 5) {
			return new BigDecimal(0.1); // 大于5件商品，折扣率0.1
		} else {
			return new BigDecimal(0.03);
		}
	}

	public List getBills() {
		return this.bills;
	}

	public void createBill(BigDecimal chargeAmount) { // 商品价格
		Bill bill = new Bill(this, chargeAmount);
		if (bills == null) {
			bills = new ArrayList();
		}
		bills.add(bill);
	}

}
