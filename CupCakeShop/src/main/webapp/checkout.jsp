<%-- 
    Document   : checkout
    Created on : 06-03-2019, 12:21:52
    Author     : aamandajuhl and sofieamalielandt
--%>
<%@page import="logic.InvoiceConnector"%>
<%@page import="java.util.ArrayList"%>
<%@page import="logic.Topping"%>
<%@page import="logic.Bottom"%>
<%@page import="java.util.List"%>
<%@page import="logic.LineItem"%>
<%@page import="logic.User"%>
<%@page import="logic.ShoppingCart"%>
<%@page import="logic.Cupcake"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "header.jsp" %>

<%    
    String checkout = (String) session.getAttribute("checkout");
    ShoppingCart invoice = (ShoppingCart) session.getAttribute("invoice");
%>
<form method = "POST">
    <center id="checkout">
        <%
            if (checkout.equals("Thank you for your order"))
            {
        %>
        <div>
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
                out.println("<br><br>" + checkout);
            } else
            {
            %>
            <div>
                <%
                        out.println("<br><br>" + checkout);
                    }
                %>
                <br><br><button type="submit" formaction= "CommandController?command=shop">Go back</button>
            </div>
        </div>
    </center>
</form>
</body>
</html>
