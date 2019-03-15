<%-- 
    Document   : error
    Created on : 05-03-2019, 20:48:55
    Author     : aamandajuhl and sofieamalielandt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String errormessage = "Error occurred...";
    if(request.getAttribute("errormessage") != null)
    {
        errormessage = (String) request.getAttribute("errormessage");
    }
%>

<h2>Error</h2>

<p>Error message: <% out.println(errormessage);%></p>



