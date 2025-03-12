<%@page import="dao.ExamCategoriesDAO"%>
<%@page import="dto.ExamCategoriesDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Dashboard</title>
        <style>
            /* Reset mặc định */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

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

            h2 {
                color: #333;
                margin-bottom: 15px;
            }

            .welcome-message {
                font-size: 16px;
                margin-bottom: 10px;
            }

            /* Form */
            form {
                margin: 10px 0;
            }

            input[type="text"] {
                padding: 8px;
                width: 70%;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
            }

            input[type="submit"], button {
                padding: 8px 12px;
                border: none;
                background-color: #28a745;
                color: white;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                font-weight: bold;
                transition: 0.3s;
            }

            button {
                margin-top: 5px;
                width: 100%;
            }

            input[type="submit"]:hover,
            button:hover {
                background-color: #218838;
            }

            /* Bảng dữ liệu */
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 15px;
            }

            table, th, td {
                border: 1px solid #ddd;
            }

            th, td {
                padding: 10px;
                text-align: center;
            }

            th {
                background-color: #28a745;
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            /* Logout */
            .logout-btn {
                background-color: #dc3545;
            }

            .logout-btn:hover {
                background-color: #c82333;
            }

            /* Hiển thị lỗi */
            .error-message {
                color: red;
                font-size: 14px;
                margin-left: 5px;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <h2>Welcome to Project Dashboard</h2>
            <%
                ExamCategoriesDAO categoryDAO = new ExamCategoriesDAO();
                List<ExamCategoriesDTO> categories = (List<ExamCategoriesDTO>) categoryDAO.readAll();
            %>

            <%
                dto.UserDTO user = (dto.UserDTO) session.getAttribute("user");
                if (user != null) {
            %>
            <p class="welcome-message">Hello, <strong><%= user.getName()%></strong>! You are logged in.</p>
            <%}%>
            <form action="MainController">
                <input type="hidden" name="action" value="logout"/>
                <input type="submit" class="logout-btn" value="Logout"/>
            </form>
            <table>
                <tr>
                    <th>Category ID</th>
                    <th>Category Name</th>
                    <th>Description</th>
                </tr>
                <%
                    if (categories != null) {
                        for (ExamCategoriesDTO category : categories) {
                %>
                <tr>
                    <td><%= category.getCategory_id()%></td>
                    <td><%= category.getCategory_name()%></td>
                    <td><%= category.getDescription()%></td>
                </tr>
                <%
                        }
                    }
                %>             
            </table>
            <form action="MainController" method="GET">
                <input type="hidden" name="action" value="viewCategories">
                <input type="submit" value="View Categories">
            </form>            
            
            <a href="ViewExams?category_id=${category.categoryId}">
                <button>View Exams</button>
            </a>

            <%
                if (user != null && user.getRole().equals("Instructors ")) {


            %>

            <%                    }
            %>
        </div>

    </body>
</html>
