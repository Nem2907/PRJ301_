<%@page import="java.util.List"%>
<%@page import="dto.ExamsDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Exam List</title>
        <style>
            body { font-family: Arial, sans-serif; }
            table { width: 100%; border-collapse: collapse; margin-top: 20px; }
            th, td { padding: 10px; border: 1px solid #ddd; text-align: center; }
            th { background-color: #28a745; color: white; }
            .error { color: red; font-weight: bold; }
        </style>
    </head>
    <body>

        <h2>Exam List</h2>

        <%
            List<ExamsDTO> examList = (List<ExamsDTO>) request.getAttribute("examList");
            String errorMessage = (String) request.getAttribute("errorMessage");

            if (errorMessage != null) {
        %>
        <p class="error"><%= errorMessage%></p>
        <%
        } else if (examList == null || examList.isEmpty()) {
        %>
        <p>No exams available for this category.</p>
        <%
        } else {
        %>
        <table>
            <tr>
                <th>Exam ID</th>
                <th>Exam Title</th>
                <th>Subject</th>
                <th>Total Marks</th>
                <th>Duration (mins)</th>
            </tr>
            <% for (ExamsDTO exam : examList) {%>
            <tr>
                <td><%= exam.getExam_id()%></td>
                <td><%= exam.getExam_tilte()%></td>
                <td><%= exam.getSubject()%></td>
                <td><%= exam.getToltal_marks()%></td>
                <td><%= exam.getDuration()%></td>
            </tr>
            <% } %>
        </table>
        <%
            }
        %>

        <a href="viewCategories.jsp">
            <button>Back to Categories</button>
        </a>

    </body>
</html>
