<%@page import="prj301demo.Student.StudentDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Details</title>
    </head>
    <body>
         <jsp:include page="/menu.jsp" flush="true" />
         
         <h1>Student Details </h1>         
         <p> Login user: ${sessionScope.usersession.username}</p>
                 
         <table>
         
         <tr><td>Id</td><td>${requestScope.student.id}</td></tr>
         <tr><td>First name</td><td>${requestScope.student.firstName }</td></tr>
         <tr><td>Last name</td><td>${requestScope.student.lastName}</td></tr>	
         <tr><td>Age</td><td>${requestScope.student.age}</td></tr>
         
         </table>
         
        
         <form action="StudentController">
             <input type=hidden name="id" value="${requestScope.student.id}">
             <input type=hidden name="action" value="edit">
             <input type=submit value="Edit"></form>
         </form>
        
         
    </body>
</html>
