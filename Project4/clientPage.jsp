<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Project 4 Enterprise System</title>
  <style>
    body {
      background-color: black;
      color: white;
      font-family: Arial, sans-serif;
      text-align: center;
      padding: 20px;
    }

    h1 {
      color: yellow;
      font-size: 28px;
    }

    h2 {
      color: limegreen;
      font-size: 22px;
      margin-bottom: 40px;
    }

    .info-text {
      font-size: 16px;
      margin-bottom: 20px;
    }

    .info-text .root {
      color: red;
      font-weight: bold;
    }

    .input-area {
      background-color: #9494ff;
      width: 80%;
      height: 150px;
      margin: 0 auto 20px;
      border: 2px solid #666;
      color: white;
      padding: 10px;
      font-size: 16px;
      resize: none;
    }

    .button-container {
      margin: 20px 0;
    }

    .button {
      font-weight: bold;
      padding: 10px 20px;
      margin: 0 10px;
      border: none;
      cursor: pointer;
      font-size: 16px;
    }

    .execute-btn {
      background-color: black;
      color: lime;
      border: 2px solid lime;
    }

    .execute-btn:hover {
      background-color: rgb(0, 122, 0);
      color: rgb(0, 0, 0);
    }

    .reset-btn {
      background-color: black;
      color: red;
      border: 2px solid red;
    }

    .reset-btn:hover {
      background-color: rgb(119, 0, 0);
      color: black;
    }

    .clear-btn {
      background-color: black;
      color: gold;
      border: 2px solid gold;
    }

    .clear-btn:hover {
      background-color: rgb(116, 98, 0);
      color: black;
    }

    .results-info {
      margin-top: 30px;
      font-size: 16px;
      color: lightgray;
    }

    .results-area {
      margin-top: 10px;
      text-align: center;
      width: 90%;
      margin-left: auto;
      margin-right: auto;
      border-top: 1px solid gray;
      padding-top: 15px;
    }

    .results-area h3 {
      color: white;
      font-size: 18px;
    }

    .results-content {
      background-color: #222;
      color: #0ff;
      padding: 10px;
      min-height: 100px;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 15px;
    }

    th, td {
      border: 1px solid #555;
      padding: 8px;
      text-align: left;
    }

    th {
      background-color: #333;
      color: #fff;
    }

    tr:nth-child(even) {
      background-color: #2a2a2a;
    }

    tr:hover {
      background-color: #444;
    }
  </style>
</head>
<body>
  <h1>Welcome to the Spring 2025 Project 4 Enterprise System</h1>
  <h2>A Servlet/JSP-based Multi-tiered Enterprise Application Using A Tomcat Container</h2>

  <div class="info-text">
    You are connected to the Project 4 Enterprise System database as a <span class="root">client-level</span> user.<br>
    Please enter any SQL query or update command in the box below.
  </div>

  <form action="client" method="post">
    <textarea class="input-area" placeholder="Enter SQL command here..." id="input" name="input"></textarea>

    <div class="button-container">
      <input type="submit" class="button execute-btn" value="Execute Command">
      <input type="button" class="button reset-btn" value="Reset Form" onclick="document.getElementById('input').value = '';">
      <input type="button" class="button clear-btn" value="Clear Results" onclick="document.getElementById('results').innerHTML = '';">
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
