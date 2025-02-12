<%-- 
    Document   : example4
    Created on : Feb 10, 2025, 1:43:30 PM
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
        <%-- In ra bảng cửu chương--%>
        <br>
        <%
            for(int i = 2 ; i <= 9;i++){
                %>
                <h3>Bảng cửu Chương <%=i%></h3>
                <hr>
                <%
                for(int j = 1 ; j <= 9;j++){
                    %>
                        <%=i%> x <%=j%> = <%=(i * j)%> <br/>
                    <%
                }
            }
        %>
    </body>
</html>
