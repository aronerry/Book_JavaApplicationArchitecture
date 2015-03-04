package com.extensiblejava.order;

import java.math.BigDecimal;

public class DefaultOrderBuilder implements OrderBuilder {
	public Order[] build() {
		Order[] orders = new Order[3];
		orders[0] = new Order(new Integer(3), new BigDecimal("120.00"));
		orders[0].setDiscountAmount(new BigDecimal("0.05"));
		orders[1] = new Order(new Integer(50), new BigDecimal("2000.00"));
		orders[1].setDiscountAmount(new BigDecimal("0.10"));
		orders[2] = new Order(new Integer(45), new BigDecimal("1840.00"));
		orders[2].setDiscountAmount(new BigDecimal("0.10"));
		return orders;
	}
}