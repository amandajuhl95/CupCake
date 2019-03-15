<%-- 
    Document   : adminpage
    Created on : 07-03-2019, 12:55:04
    Author     : aamandajuhl and sofieamalielandt
--%>

<%@page import="logic.ShoppingCart"%>
<%@page import="java.util.List"%>
<%@page import="logic.InvoiceConnector"%>
<%@page import="logic.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "header.jsp" %>

<form method ="POST"> 
    <center id="invoice">
        <ul>
            <li><p><h1> All invoices</h1></p></li>
            <%            
                List<ShoppingCart> invoices = (List<ShoppingCart>) session.getAttribute("allinvoices");
                for (ShoppingCart invoice : invoices)
                {
                    out.println("<li><a href=\"CommandController?command=admininvoice&selected=" + invoice.getInvoice_id() + "\">" + invoice + "</a></li>");
                }
            %>
        </ul>
    </center>
</form>
</body>
</html>
