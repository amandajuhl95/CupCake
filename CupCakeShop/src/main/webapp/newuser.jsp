<%-- 
    Document   : newuser
    Created on : 05-03-2019, 15:13:07
    Author     : aamandajuhl and sofieamalielandt
--%>

<%@page import="logic.User"%>
<%@page import="logic.NewUserConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "loginheader.jsp" %>
<style>
    body{
        background-image: url("Images/index_cupcake.jpg");
        background-position: 70% 30%;
    }
</style>
<center class="index">
    <div>
        <form method = "POST">   
            <%
                String res = (String) session.getAttribute("res");
            %>  

            <%
                out.println(res + "<br><br>");
            %>
            Username <input type ="text" name ="username" value="" minlength="4" required>
            <br><br>
            Password <input type ="password" name ="password" value="" minlength="4" required>
            <br><br>
            <button type="submit" formaction="CommandController?command=login">Login</button>
            <br><br>
            Email <input type ="text" name ="email" value="">
            <br><br>
            <button type="submit" formaction="CommandController?command=newuser">Create user</button>
        </form>
    </div>

</center>
</body>
</html>



