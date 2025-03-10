<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f8f9fa;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    .container {
        background: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
        width: 350px;
        text-align: center;
    }

    h1 {
        color: #343a40;
        font-size: 20px;
        margin-bottom: 15px;
    }

    p {
        color: red;
        font-weight: bold;
    }

    label {
        font-weight: bold;
        display: block;
        text-align: left;
        margin-top: 10px;
    }

    input, select {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 14px;
    }

    input[type="submit"] {
        background-color: #28a745;
        color: white;
        font-weight: bold;
        border: none;
        padding: 12px;
        cursor: pointer;
        margin-top: 15px;
        transition: 0.3s;
    }

    input[type="submit"]:hover {
        background-color: #218838;
    }
</style>

<body>
    <div class="container">
        <h1>Update Project Status</h1>

        <% String message = (String) request.getAttribute("message"); %>
        <% if (message != null) { %>
            <p><%= message %></p>
        <% } %>

        <form action="MainController" method="post">
            <input type="hidden" name="action" value="updateStatus"/>

            <label for="project_id">Project ID:</label>
            <input type="text" id="project_id" name="project_id" required/>

            <label for="newStatus">New Status:</label>
            <select id="newStatus" name="newStatus" required>
                <option value="Development">Development</option>
                <option value="Launch">Launch</option>
                <option value="Scaling">Scaling</option>
                <option value="Ideation">Ideation</option>
            </select>

            <input type="submit" value="Update Status"/>
        </form>
    </div>
</body>
