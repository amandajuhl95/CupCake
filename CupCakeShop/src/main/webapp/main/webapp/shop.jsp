<%-- 
    Document   : shop
    Created on : 05-03-2019, 13:39:32
    Author     : aamandajuhl and sofieamalielandt
--%>

<%@page import="logic.ShoppingCart"%>
<%@page import="logic.LineItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="logic.Topping"%>
<%@page import="logic.User"%>
<%@page import="java.util.List"%>
<%@page import="logic.CupcakeConnector"%>
<%@page import="logic.Bottom"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "header.jsp" %>

<form method = "POST">
    <center id="shop">
        <div>
            <br><br><b>Bottoms</b>
            <select name="bottom">
                <option disabled selected>Choose bottom</option>
                <%                    
                    List<Bottom> bottoms = (List<Bottom>) session.getAttribute("bottoms");
                    for (Bottom b : bottoms)
                    {
                        out.println("<option>" + b.toString() + "</option>");
                    }
                %>
            </select>
            &nbsp;&nbsp;
            <b>Topping</b>
            <select name="topping">
                <option disabled selected>Choose topping</option>
                <%
                    List<Topping> toppings = (List<Topping>) session.getAttribute("toppings");;
                    for (Topping t : toppings)
                    {
                        out.println("<option>" + t.toString() + "</option>");
                    }
                %>
            </select>
            &nbsp;&nbsp;
            <b>Quantity</b>
            <input type ="number" name ="quantity" value="1" size="2" min="1" max="100" required><br>
            <%
                String reply = (String) session.getAttribute("reply");

                if (reply != null)
                {
                    out.println("<br><br><br>" + reply);
                    session.removeAttribute("reply");
                }
            %>
            <br><br><br><button type="submit" formaction= "CommandController?command=shoppingcart">Add to cart</button>
            
        </div>
    </center>
</form>
</body>
</html>



