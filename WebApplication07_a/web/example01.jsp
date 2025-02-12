<%-- 
    Document   : example01
    Created on : Feb 10, 2025, 12:55:18 PM
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
        <%--<%! int a = 9 ; đây là code khai báo , cú pháp : ! %>--%>
        <%! int a = 9 ; %>
        
        <%
            double b;
            b = Math.sqrt(a);
        %>
        Kết quả của Sqrt(<%=a%>) = <span style="color: blueviolet"><%=b%> </span>
        
        <%--<%=a%>  : cú pháp này muốn lấy ra dữ liệu (trong trường hợp này là dữ liệu a) cho chúng ta sử dụng--%>
    </body>
</html>
