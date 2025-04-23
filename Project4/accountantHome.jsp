<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>Spring 2025 Project 4 Enterprise System</title>
    <style>
        body {
            background-color: black;
            color: white;
            font-family: Arial, sans-serif;
            text-align: center;
        }
        h1 {
            color: yellow;
        }
        h2 {
            color: limegreen;
        }
        .user-info {
            margin-top: 10px;
            color: white;
        }
        .user-info .accountant {
            color: red;
        }
        .option-list {
            background-color: #444;
            margin: 30px auto;
            width: 70%;
            padding: 20px;
            border-radius: 10px;
            text-align: left;
        }
        .option-list label {
            display: block;
            margin-bottom: 15px;
            font-size: 16px;
        }
        .option-list input[type="radio"] {
            margin-right: 10px;
        }
        .option-list a {
            color: #3399ff;
            text-decoration: none;
        }
        .option-list a:hover {
            text-decoration: underline;
        }

        .buttons {
            margin-top: 20px;
        }
        .buttons input {
            font-size: 16px;
            font-weight: bold;
            padding: 10px 20px;
            margin: 10px;
            border: 2px solid;
            border-radius: 5px;
            cursor: pointer;
            transition: all 0.3s ease;
        }
        .execute-btn {
            color: limegreen;
            border-color: limegreen;
            background-color: black;
        }
        .execute-btn:hover {
            background-color: #006400; /* dark green */
            color: black;
        }
        .clear-btn {
            color: red;
            border-color: red;
            background-color: black;
        }
        .clear-btn:hover {
            background-color: #8b0000; /* dark red */
            color: black;
        }

        .results-info {
            margin-top: 40px;
            font-size: 16px;
            color: white;
        }

        .results-area {
            background-color: #222;
            width: 85%;
            margin: 20px auto;
            padding: 20px;
            border-radius: 10px;
        }

        .results-area h3 {
            color: cyan;
        }

        .results-content {
            margin-top: 20px;
            color: white;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        th, td {
            padding: 12px 15px;
            border: 1px solid #555;
        }

        th {
            background-color: #333;
            color: lime;
        }

        td {
            background-color: #111;
            color: white;
        }

        tr:hover td {
            background-color: #333;
            color: black;
        }

        .results-content p {
            font-size: 16px;
            font-weight: bold;
        }

    </style>
</head>
<body>

<h1>Welcome to the Spring 2025 Project 4 Enterprise System</h1>
<h2>A Servlet/JSP-based Multi-tiered Enterprise Application Using A Tomcat Container</h2>

<div class="user-info">
    You are connected to the Project 4 Enterprise System database as an 
    <span class="accountant">accountant-level</span> user.<br>
    Please select the operation you would like to perform from the list below.
</div>

<form action="accountant" method="post">
    <div class="option-list">
        <label>
            <input type="radio" name="query" value="Get_The_Maximum_Status_Of_All_Suppliers">
            <a>Get The Maximum Status Value Of All Suppliers</a> (Returns a maximum value)
        </label>
        <label>
            <input type="radio" name="query" value="Get_The_Sum_Of_All_Parts_Weights">
            <a>Get The Total Weight Of All Parts</a> (Returns a sum)
        </label>
        <label>
            <input type="radio" name="query" value="Get_The_Total_Number_Of_Shipments">
            <a>Get The Total Number of Shipments</a> (Returns the current number of shipments in total)
        </label>
        <label>
            <input type="radio" name="query" value="Get_The_Name_Of_The_Job_With_The_Most_Workers">
            <a>Get The Name And Number Of Workers Of The Job With The Most Workers</a> (Returns two values)
        </label>
        <label>
            <input type="radio" name="query" value="List_The_Name_And_Status_Of_All_Suppliers">
            <a>List The Name And Status Of Every Supplier</a> (Returns a list of supplier names with their current status)
        </label>
    </div>

    <div class="buttons">
        <input type="submit" value="Execute Command" class="execute-btn">
        <input type="reset" value="Clear Results" class="clear-btn" onclick="document.getElementById('results').innerHTML='No results found.'">
    </div>
</form>

<div class="results-info">
    All execution results will appear below this line.
</div>

<div class="results-area">
    <h3>Execution Results:</h3>
    <div class="results-content" id="results">
      <%
        List<String> headers = (List<String>) request.getAttribute("headers");
        List<List<String>> data = (List<List<String>>) request.getAttribute("queryResults");
        String message = (String) request.getAttribute("message");
        String error = (String) request.getAttribute("error");

        if (error != null) {
      %>
          <p style="color: red;"><%= error %></p>
      <%
        } else if (message != null) {
      %>
          <p style="color: lightgreen;"><%= message %></p>
      <%
        } else if (headers != null && data != null && !data.isEmpty()) {
      %>
          <table>
            <thead>
              <tr>
                <% for (String header : headers) { %>
                  <th><%= header %></th>
                <% } %>
              </tr>
            </thead>
            <tbody>
              <% for (List<String> row : data) { %>
                <tr>
                  <% for (String cell : row) { %>
                    <td><%= cell != null ? cell : "NULL" %></td>
                  <% } %>
                </tr>
              <% } %>
            </tbody>
          </table>
      <%
        } else {
      %>
          <p>No results found.</p>
      <%
        }
      %>
    </div>
</div>

</body>
</html>
