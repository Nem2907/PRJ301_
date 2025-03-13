<%@page import="java.util.List"%>
<%@page import="dto.ExamCategoriesDTO"%>
<%@page import="dao.ExamCategoriesDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create Exam</title>
    </head>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .form-container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }

        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }

        label {
            display: block;
            text-align: left;
            margin: 10px 0 5px;
            font-weight: bold;
        }

        input, select {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="submit"] {
            background-color: #28a745;
            color: white;
            font-size: 16px;
            border: none;
            cursor: pointer;
            padding: 10px;
            border-radius: 5px;
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        .error-message {
            color: red;
            font-size: 14px;
            margin-top: 10px;
        }

        .message {
            color: green;
            font-size: 14px;
            margin-top: 10px;
        }

        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            margin-top: 10px;
        }

        button:hover {
            background-color: #0056b3;
        }

    </style>
    <body>

        <div class="form-container">
            <h1>Create Exam</h1>
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="addExam"/>

                <label>Exam Title</label>
                <input type="text" name="exam_title" required/>

                <label>Subject</label>
                <input type="text" name="subject" required/>

                <label>Category</label>
                <select name="category_id">
                    <%
                        ExamCategoriesDAO dao = new ExamCategoriesDAO();
                        List<ExamCategoriesDTO> categories = dao.readAll();
                        if (categories != null) {
                            for (ExamCategoriesDTO category : categories) {
                    %>
                    <option value="<%= category.getCategory_id()%>"><%= category.getCategory_name()%></option>
                    <%
                        }
                    } else {
                    %>
                    <option disabled>No categories available</option>
                    <% } %>
                </select>

                <label>Total Marks</label>
                <input type="text" name="total_marks" required/>

                <label>Duration (minutes)</label>
                <input type="text" name="duration" required/>

                <input type="submit" value="Save"/>

                <%
                    String error_message = (String) request.getAttribute("Error_Message");
                    if (error_message != null && !error_message.equals("null") && !error_message.isEmpty()) {
                %>
                <span class="error-message"><%= error_message%></span>
                <% } %>
            </form>

            <%
                String message = (String) request.getAttribute("message");
                if (message != null && !message.equals("null") && !message.isEmpty()) {
            %>
            <span class="message"><%= message%></span>
            <% }%>

            <a href="viewCategories.jsp">
                <button>Back to Categories</button>
            </a>
        </div>

    </body>
</html>
