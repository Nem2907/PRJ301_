

<%-- 
    Document   : search
    Created on : Feb 13, 2025, 1:42:26 PM
    Author     : tungi
--%>
<%@page import="dto.BookDTO"%>
<%@page import="java.awt.print.Book"%>
<%@page import="java.util.List"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-16WWW">
        <title>JSP Page</title>
        
        <style>
            .book-table {
                width: 100%;
                border-collapse: collapse;
                margin: 25px 0;
                font-size: 14px;
                font-family: Arial, sans-serif;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            }

            .book-table thead th {
                background-color: #009879;
                color: #ffffff;
                text-align: left;
                font-weight: bold;
                padding: 12px 15px;
            }

            .book-table tbody tr {
                border-bottom: 1px solid #dddddd;
            }

            .book-table tbody tr:nth-of-type(even) {
                background-color: #f3f3f3;
            }

            .book-table tbody tr:last-of-type {
                border-bottom: 2px solid #009879;
            }

            .book-table tbody td {
                padding: 12px 15px;
            }

            .book-table tbody tr:hover {
                background-color: #f5f5f5;
                transition: 0.3s ease;
            }

            /* Responsive design */
            @media screen and (max-width: 600px) {
                .book-table {
                    font-size: 12px;
                }

                .book-table thead th,
                .book-table tbody td {
                    padding: 8px 10px;
                }
            }
        </style> 
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
            <form action="MainController">
                <input type ="hidden" name="action" value="logout"/>
                <input type ="submit" value="logout"/>
            </form>
            <hr/>
            <br>
            
            <%
                String searchTerm = request.getAttribute("searchTerm")+"";
                searchTerm = searchTerm.equals("null") ?"":searchTerm ;
            %>
            <form action="MainController">
                <input type="hidden" name="action" value="search"/>
                Search Value <input type="text" name="searchTerm" value="<%=searchTerm%>"/>
                <input type="submit" value="Search"/>
            </form>
            <br>
            <br>
            <% if(request.getAttribute("books") != null){
                    List<BookDTO> books = (List<BookDTO>)request.getAttribute("books");
                    %>
                    <table class="book-table">
                        <tr>
                            <td>BookID</td> 
                            <td>Title</td>
                            <td>Author</td>
                            <td>PublishYear</td>
                            <td>Price</td>
                            <td>Quantity</td>       
                            <td>ACtion</td> 
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
                                    <td>><a href="MainController?action=delete&id=<%=item.getBookID()%>&searchTerm=<%=searchTerm%>">
                                            <img src="/assets/Hopstarter-3d-Cartoon-Vol3-Windows-Close-Program.256.png"/>
                                        </a>
                                    </td> 
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
