<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <title>Student Details</title>
     </head>
     <body>

          <jsp:include page="/menu.jsp" flush="true" /> 

          <h1>Student Edit </h1>


          <p>Login user: ${sessionScope.usersession.username} </p>

          <form action="StudentController" method="POST">
               <table>
                    <tr>
                         <td>Id</td>
                         <td><input type="text" name="id" value="${requestScope.student.id}"></td>
                    </tr>
                    <tr>
                         <td>First name</td>
                         <td><input type="text" name="firstName" value="${requestScope.student.firstName}"></td>
                    </tr>
                    <tr>
                         <td>Last name</td>
                         <td><input type="text" name="lastName" value="${requestScope.student.lastName}"></td>
                    </tr>
                    <tr>
                         <td>Age</td>
                         <td><input type="text" name="age" value="${requestScope.student.age}"></td>
                    </tr>
                    <tr>
                         <td>
                              <input name="action" value="${requestScope.nexaction}" type="hidden">
                              <input type="Submit" value="Save">
                         </td>
                    </tr>

               </table>
          </form>


     </body>
</html>
