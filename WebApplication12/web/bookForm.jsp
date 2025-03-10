<%-- 
    Document   : bookForm
    Created on : Feb 27, 2025, 1:52:47 PM
    Author     : hoang
--%>

<%@page import="dto.BookDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            BookDTO book = new BookDTO();
            try {
                    book = (BookDTO)request.getAttribute("book");
                    
                } catch (Exception e) {
                }
            if(book == null){
                //nếu sách bị null , tạo mới để hiển thị
                book = new BookDTO();
            }
            String txtBookID_error = request.getAttribute("BookID_Error") + "";
            txtBookID_error = txtBookID_error.equals("null") ?  "" :txtBookID_error;
            
            String title_Error = request.getAttribute("title_Error") + "";
            title_Error = title_Error.equals("null") ?  "" : title_Error;
            
            String author_Error = request.getAttribute("author_Error") + "";
            author_Error = author_Error.equals("null") ?  "" :author_Error;
            
            String publishYear_Error = request.getAttribute("publishYear_Error") + "";
            publishYear_Error = publishYear_Error.equals("null") ?  "" : publishYear_Error;
            
            String price_Error = request.getAttribute("price_Error") + "";
            price_Error = price_Error.equals("null") ?  "" : price_Error;
            
            String quantity_Error = request.getAttribute("quantity_Error") + "";
            quantity_Error = quantity_Error.equals("null") ?  "" : quantity_Error;
            
        %>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="add"/>
            Book ID <input type="text" name="txtBookID" value="<%=book.getBookID()%>"/> <span style="color: red"><%=txtBookID_error%></span> 
            <br/>
            Title <input type="text" name="txtTitle"value="<%=book.getTitle()%>"/> <span style="color: red"><%=title_Error%></span>
            <br/>
            Author <input type="text" name="txtAuthor"value="<%=book.getAuthor()%>"/> <span style="color: red"><%=author_Error%></span>
            <br/>
            Publish Year <input type="number" name="txtPublishYear"value="<%=book.getPublishYear()%>"/> <span style="color: red"><%=publishYear_Error%></span>
            <br/>
            Price <input type="number" name="txtPrice" value="<%=book.getPrice()%>"/> <span style="color: red"><%=price_Error%></span>
            <br/>
            Quantity <input type="number" name="txtQuantity"value="<%=book.getQuantity()%>"/> <span style="color: red"><%=quantity_Error%></span>
            <br/>
            <input type="submit" value ="Save"/>
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>
