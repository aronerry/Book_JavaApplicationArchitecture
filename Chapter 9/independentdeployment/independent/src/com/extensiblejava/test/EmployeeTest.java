package com.extensiblejava.test;

import java.math.BigDecimal;

import junit.framework.TestCase;

import com.extensiblejava.employee.Employee;
import com.extensiblejava.employee.Name;
import com.extensiblejava.employee.PayCheck;
import com.extensiblejava.employee.PayrollRunner;
import com.extensiblejava.facade.PayFacade;

public class EmployeeTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(EmployeeTest.class);
	}

	public void testEmployeePayWithoutPayFacade() {

		PayrollRunner runner = new PayrollRunner() {
			public BigDecimal runPayroll(BigDecimal salary) { return new BigDecimal("500.00"); }
		};
		Employee employee = new Employee(new Name(), new BigDecimal("20000.00"));
		PayCheck payCheck = employee.pay(runner);
		assertEquals(payCheck.getPay(), new BigDecimal("500.00"));

	}
	
	public void testEmployeePay() {
		PayFacade runner = new PayFacade();
		Employee employee = new Employee(new Name(), new BigDecimal("20000.00"));
		PayCheck payCheck = employee.pay(runner);
		assertEquals(payCheck.getPay(), new BigDecimal("20000.00"));
	}
}
