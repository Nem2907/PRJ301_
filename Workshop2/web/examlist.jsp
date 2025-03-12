<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="dto.ExamsDTO"%>
<%@page import="dto.UserDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exam List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            padding: 20px;
        }
        .container {
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            width: 600px;
            text-align: center;
        }
        h2 { color: #333; margin-bottom: 15px; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        table, th, td { border: 1px solid #ddd; }
        th, td { padding: 10px; text-align: center; }
        th { background-color: #28a745; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .logout-btn { background-color: #dc3545; }
        .logout-btn:hover { background-color: #c82333; }
        .back-btn, .logout-btn { padding: 8px 12px; border: none; color: white; border-radius: 5px; cursor: pointer; }
        .back-btn { background-color: #007bff; margin-top: 10px; }
        .back-btn:hover { background-color: #0056b3; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Exam List</h2>
        <c:if test="${not empty sessionScope.user}">
            <p class="welcome-message">Hello, <strong>${sessionScope.user.name}</strong>! You are logged in.</p>
            <form action="MainController" method="POST">
                <input type="hidden" name="action" value="logout"/>
                <input type="submit" class="logout-btn" value="Logout"/>
            </form>
        </c:if>
        <table>
            <tr>
                <th>Exam Title</th>
                <th>Subject</th>
                <th>Total Marks</th>
                <th>Duration (mins)</th>
            </tr>
            <c:forEach var="exam" items="${examList}">
                <tr>
                    <td>${exam.examTitle}</td>
                    <td>${exam.subject}</td>
                    <td>${exam.totalMarks}</td>
                    <td>${exam.duration}</td>
                </tr>
            </c:forEach>
        </table>
        <a href="dashboard.jsp">
            <button class="back-btn">Back to Categories</button>
        </a>
    </div>
</body>
</html>
