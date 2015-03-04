package com.extensiblejava.ui;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.util.MessageResources;
import java.util.*;
import com.extensiblejava.bill.*;
import com.extensiblejava.audit.*;
import com.extensiblejava.audit.audit1.*;
import com.extensiblejava.bill.data.*;
import com.extensiblejava.mediator.*;

public class AuditAction extends Action {
	private static AuditFacade auditFacade;
	
	public ActionForward perform(ActionMapping mapping,
				 ActionForm form,
				 HttpServletRequest request,
				 HttpServletResponse response)
	throws IOException, ServletException {

		BillDetailForm billDetailForm = (BillDetailForm) form;
		Bill bill = Bill.loadBill(new DefaultBillEntityLoader(new Integer(billDetailForm.getBillId())));

		try {
			//Pass null in for BundleContext since we don't have a reference here...
			//System.out.println("AUDIT FACADE: " + AuditFacadeFactory.getAuditFacade(bill).getClass());
			//auditFacade = AuditFacadeFactory.getAuditFacade(bill);
			bill.audit(auditFacade);
			request.setAttribute("bill",bill);
			return (mapping.findForward("success"));
		} catch (AuditException e) {
			throw new ServletException(e);
		}
	}
	
	public static void setAuditor(AuditFacade facade) {
		auditFacade = facade;
	}

}