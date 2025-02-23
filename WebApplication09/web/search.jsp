
<%@page import="java.util.List"%>
<%@page import="dto.BookDTO"%>
<%-- 
    Document   : search
    Created on : Feb 13, 2025, 1:42:26 PM
    Author     : tungi
--%>

<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div style="min-height: 500px; padding: 20px">

            <%
                if (session.getAttribute("user") != null) {
                    UserDTO user = (UserDTO) session.getAttribute("user");
                    
            %>
            Welcome <b> <%=user.getFullname()%> </b>
            <br>
            <a href="MainController?action=logout">Logout</a>
            <form action="MainController">
                <input type ="hidden" name="action" value="logout"/>
                <input type ="submit" value="logout"/>
            </form>
            <hr/>
            <form action="MainController">
                <input type="hidden" name="action" value="search"/>
                Search Value <input type="text" name="searchTerm" />
                <input type="submit" value="Search"/>
            </form>
            <br>
            <br>
            <% if(request.getAttribute("books") != null){
                    List<BookDTO> books = (List<BookDTO>)request.getAttribute("books");
                    %>
                    <table border="1">
                        <tr>
                            <td>BookID</td>
                            <td>Title</td>
                            <td>Author</td>
                            <td>PublishYear</td>
                            <td>Price</td>
                            <td>Quantity</td>           
                        </tr>
                        <%
                        for (BookDTO item : books ) {
                                %>
                                <tr>
                                    <td><%=item.getBookID()%></td>
                                    <td><%=item.getTitle()%></td>
                                    <td><%=item.getAuthor()%></td>
                                    <td><%=item.getPublishYear()%></td>
                                    <td><%=item.getPrice()%></td>
                                    <td><%=item.getQuantity()%></td> 
                                </tr>
                                <%
                            }
                                %>
                    </table>
                    <%
                    }
                    %>
                    <% } else { %>
                    You do not have permission to access this content.
                    <% }%>
           
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
