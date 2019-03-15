<%-- 
    Document   : adminInvoice
    Created on : 07-03-2019, 13:42:38
    Author     : aamandajuhl and sofieamalielandt
--%>

<%@page import="logic.LineItem"%>
<%@page import="logic.ShoppingCart"%>
<%@page import="logic.InvoiceConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "header.jsp" %>

<%    
    ShoppingCart invoice = (ShoppingCart) session.getAttribute("invoice");
%>
<form method="POST">
    <center id="shoppingcart">
        <div>
            <%
                out.println("<b>Ordernumber: " + invoice.getInvoice_id() + "&nbsp;&nbsp;&nbsp;&nbsp;Orderdate: " + invoice.getDate() + "</b>");
                out.println("<br><br>Username: " + invoice.getUser().getUserName() + " - id: " + invoice.getUser().getId());
                out.println("<br><br>Email: " + invoice.getUser().getEmail() + "<br><br>");
            %>
            <table id="shoppingcart"> 
                <tr>
                    <th><b>Cupcake</b></th>
                    <th><b>Quantity</b></th>
                    <th><b>Price</b></th>
                    <th><b>Total</b></th>
                </tr>
                <%
                    for (LineItem l : invoice.getLineItems())
                    {
                        out.println("<tr>");
                        out.println("<td>" + l.getCupcake() + "</td>");
                        out.println("<td>" + l.getQuantity() + "</td>");
                        out.println("<td>" + l.getCupcake().getPrice() + "</td>");
                        out.println("<td>" + l.getPrice() + "</td>");
                        out.println("</tr>");
                    }
                %>
            </table>
            <%
                out.println("<br><br><b>Total price: " + invoice.getTotalPrice() + "</b>");
            %>
        </div>
    </center>
</form>

</body>
</html>
