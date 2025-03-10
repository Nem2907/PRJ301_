<%@page import="dto.StartupProjectsDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Project</title>
        <style>
            /* Định dạng toàn trang */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            /* Container chính */
            .form-container {
                background: #fff;
                padding: 25px;
                border-radius: 10px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
                text-align: center;
                width: 400px;
            }

            h1 {
                color: #333;
                margin-bottom: 15px;
                font-size: 22px;
            }

            /* Ô input */
            input[type="text"],
            input[type="date"] {
                width: 100%;
                padding: 10px;
                margin: 8px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
                background: #f8f9fa;
            }

            input[type="text"]:focus,
            input[type="date"]:focus {
                outline: none;
                border: 1px solid #28a745;
                background: #e9f5ee;
            }

            /* Radio button */
            .radio-group {
                text-align: left;
                margin: 10px 0;
            }

            .radio-group label {
                display: block;
                font-size: 16px;
                padding: 5px 0;
            }

            /* Nút */
            input[type="submit"],
            input[type="reset"] {
                width: 48%;
                background: #28a745;
                color: white;
                padding: 10px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                font-weight: bold;
                transition: 0.3s;
            }

            input[type="reset"] {
                background: #dc3545;
            }

            input[type="submit"]:hover {
                background: #218838;
            }

            input[type="reset"]:hover {
                background: #c82333;
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
        <%
            StartupProjectsDTO project = (StartupProjectsDTO) request.getAttribute("projects");
            if (project == null) {
                project = new StartupProjectsDTO();
            }
            String project_id_error = request.getAttribute("project_id_error") + "";
            project_id_error = project_id_error.equals("null") ? "" : project_id_error;

            String project_name_error = request.getAttribute("project_name_error") + "";
            project_name_error = project_name_error.equals("null") ? "" : project_name_error;

            String description_error = request.getAttribute("description_error") + "";
            description_error = description_error.equals("null") ? "" : description_error;

            String status_error = request.getAttribute("status_error") + "";
            status_error = status_error.equals("null") ? "" : status_error;

            String estimated_launch_error = request.getAttribute("estimated_launch_error") + "";
            estimated_launch_error = estimated_launch_error.equals("null") ? "" : estimated_launch_error;
        %>

        <div class="form-container">
            <h1>Create Project</h1>
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="add"/>
                
                <label>Project ID</label>
                <input type="text" name="txtProjectID"/>
                <span class="error-message"><%= project_id_error %></span> 

                <label>Project Name</label>
                <input type="text" name="txtProjectName"/>
                <span class="error-message"><%= project_name_error %></span> 
                 
                <label>Description</label>
                <input type="text" name="txtDescription"/>
                <span class="error-message"><%= description_error %></span>
                
                <label>Status:</label>
                <div class="radio-group">
                    <label><input type="radio" name="txtStatus" value="Ideation" checked> Ideation</label>
                    <label><input type="radio" name="txtStatus" value="Development"> Development</label>
                    <label><input type="radio" name="txtStatus" value="Launch"> Launch</label>
                    <label><input type="radio" name="txtStatus" value="Scaling"> Scaling</label>
                </div>
                <span class="error-message"><%= status_error %></span>
                
                <label>Estimated Launch</label>
                <input type="date" name="txtDate"/>
                <span class="error-message"><%= estimated_launch_error %></span>
                
                <br/>
                <input type="submit" value="Save"/>
                <input type="reset" value="Reset"/>
            </form> 
        </div>
    </body>
</html>
