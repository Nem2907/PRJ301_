<%-- 
    Document   : ouput
    Created on : Feb 10, 2025, 2:12:08 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            int n = (int)request.getAttribute("n"); 
        %>
        <%
        if( n % 2 ==0 ){
            %>
            <h1><%=n%> là số chẵn</h1>
            <%
        }else{
            %>
            <h1><%=n%> là số lẽ</h1>
            <%
        }
        %>
    </body>
</html>
