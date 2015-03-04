package com.extensiblejava.policy.test;

import junit.framework.*;
import com.extensiblejava.policy.*;
import com.extensiblejava.builder.xml.*;
import java.util.*;

public class PolicyXMLTest extends TestCase
{

	public static void main(String[] args)
	{
		String[] testCaseName = { PolicyXMLTest.class.getName() };

		junit.textui.TestRunner.main(testCaseName);
	}

	protected void setUp() {
	}

	public void testPolicy() throws Exception {

		String policyXML = "<policy>"+
							"<firstname>Jane</firstname>"+
							"<lastname>Doe</lastname>"+
							"<maritalstatus>M</maritalstatus>"+
							"<dateofbirth>01/10/1967</dateofbirth>"+
							"<tobaccouser>N</tobaccouser>"+
							"</policy>";
		Policy policy = Policy.buildPolicy(new PolicyXMLBuilder(policyXML));
		assertEquals("Jane", policy.getFirstName());
		assertEquals("Doe", policy.getLastName());
		assertEquals("M", policy.getMaritalStatus());
		assertEquals("N", policy.getTobaccoUser());

		Calendar cal = Calendar.getInstance();
		cal.setTime(policy.getDateOfBirth());
		assertEquals(10, cal.get(Calendar.DAY_OF_MONTH));
		assertEquals(1967, cal.get(Calendar.YEAR));
		assertEquals(1, cal.get(Calendar.MONTH) + 1);
	}
}
