<%@page import="java.util.List"%>
<%@page import="dto.StartupProjectsDTO"%>
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
                dto.UserDTO user = (dto.UserDTO) session.getAttribute("user");
                if (user != null) {
            %>
            <p class="welcome-message">Hello, <strong><%= user.getName()%></strong>! You are logged in.</p>

            <form action="MainController">
                <input type="hidden" name="action" value="logout"/>
                <input type="submit" class="logout-btn" value="Logout"/>
            </form>
            <hr/>

            <%
                String searchTerm = (String) request.getAttribute("searchTerm") + "";
                searchTerm = searchTerm.equals("null") ? "" : searchTerm;
            %>
            
            <form action="MainController">
                <input type="hidden" name="action" value="search"/>
                <input type="text" name="searchTerm" placeholder="Search Project Name" value="<%= searchTerm %>"/>
                <input type="submit" value="Search"/>
            </form>

            <%
                List<StartupProjectsDTO> projects = (List<StartupProjectsDTO>) request.getAttribute("projects");
                if (projects != null && !projects.isEmpty()) {
            %>
            <table>
                <tr>
                    <th>Project ID</th> 
                    <th>Project Name</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Estimated Launch</th>
                </tr>
                <%
                    for (StartupProjectsDTO item : projects) {
                %>
                <tr>
                    <td><%= item.getProject_id() %></td>
                    <td><%= item.getProject_name() %></td>
                    <td><%= item.getDescription() %></td>
                    <td><%= item.getStatus() %></td>
                    <td><%= item.getEstimated_launch() %></td>
                </tr>
                <%
                    }
                %>
            </table>
            <% } else { %>
                <p>No projects found.</p>
            <% } %>

            <% } else { %>
            <p>You are not logged in. <a href="logout.jsp">Login here</a></p>
            <% } %>

            <% if (user != null && user.getRole().equalsIgnoreCase("founder")) { %>
            
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="add">
                <button type="submit">Create New Project</button>
            </form>

            <form action="MainController" method="get">
                <input type="hidden" name="action" value="updateStatus">
                <button type="submit">Update Project Status</button>
            </form>

            <% } %>
        </div>
    </body>
</html>
