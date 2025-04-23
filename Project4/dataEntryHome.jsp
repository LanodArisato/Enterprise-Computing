<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Spring 2025 Project 4 Enterprise System</title>
<style>
    body {
        background-color: black;
        color: white;
        font-family: Verdana, sans-serif;
    }
    h1 {
        color: yellow;
        text-align: center;
    }
    h2 {
        color: limegreen;
        text-align: center;
    }
    .subtitle {
        text-align: center;
        color: white;
        margin-bottom: 20px;
    }
    .subtitle span {
        color: red;
    }
    .section {
        margin: 20px auto;
        padding: 10px;
        width: 90%;
        max-width: 1000px;
        border: 1px solid gray;
    }
    .form-box {
        display: flex;
        flex-wrap: wrap;
        gap: 10px;
        margin: 10px 0;
        border: 2px solid yellow;
        padding: 15px;
        justify-content: center;
    }
    .form-box input {
        background-color: #6666ff;
        color: white;
        border: 1px solid #999;
        padding: 10px;
        width: 150px;
    }
    .buttons {
        text-align: center;
        margin: 10px 0;
    }

    .buttons input[type=submit],
    .buttons input[type=reset],
    .buttons button {
        background-color: black;
        padding: 10px 20px;
        font-weight: bold;
        cursor: pointer;
        transition: all 0.3s ease;
    }

    /* Green button (Submit) */
    .buttons input[type=submit] {
        color: lime;
        border: 2px solid lime;
    }
    .buttons input[type=submit]:hover {
        background-color: #00cc00;
        color: black;
    }

    /* Red button (Reset) */
    .buttons input[type=reset],
    .buttons .clear-btn {
        color: red;
        border: 2px solid red;
    }
    .buttons input[type=reset]:hover,
    .buttons .clear-btn:hover {
        background-color: #990000;
        color: black;
    }

    /* Yellow button (Clear Results) */
    .buttons .clear-btn-yellow {
        border-color: gold;
        color: gold;
    }
    .buttons .clear-btn-yellow:hover {
        background-color: #e6c200;
        color: black;
    }

    .results-content {
        text-align: center;
        margin-top: 30px;
        padding: 15px;
        border-top: 2px solid gray;
        background-color: #1a1a1a;
    }
    .results-content p {
        color: cyan;
    }
</style>
</head>
<body>

    <h1>Welcome to the Spring 2025 Project 4 Enterprise System</h1>
    <h2>Data Entry Application</h2>
    <p class="subtitle">
        You are connected to the Project 4 Enterprise System database as a <span>data-entry-level</span> user.
    </p>

    <!-- Supplier Insert Section -->
    <div class="section">
        <form method="post" action="supplierInsert">
            <div class="form-box">
                <input name="snum" placeholder="snum">
                <input name="sname" placeholder="sname">
                <input name="status" placeholder="status">
                <input name="city" placeholder="city">
            </div>
            <div class="buttons">
                <input type="submit" value="Enter Supplier Record into Database">
                <input type="reset" value="Clear Data and Results" onclick = "document.getElementById('results').innerHTML = '';">
            </div>
        </form>
    </div>

    <!-- Parts Insert Section -->
    <div class="section">
        <form method="post" action="partInsert">
            <div class="form-box">
                <input name="pnum" placeholder="pnum">
                <input name="pname" placeholder="pname">
                <input name="color" placeholder="color">
                <input name="weight" placeholder="weight">
                <input name="city" placeholder="city">
            </div>
            <div class="buttons">
                <input type="submit" value="Enter Part Record into Database">
                <input type="reset" value="Clear Data and Results" onclick = "document.getElementById('results').innerHTML = '';">
            </div>
        </form>
    </div>

    <!-- Jobs Insert Section -->
    <div class="section">
        <form method="post" action="jobInsert">
            <div class="form-box">
                <input name="jnum" placeholder="jnum">
                <input name="jname" placeholder="jname">
                <input name="numworkers" placeholder="numworkers">
                <input name="city" placeholder="city">
            </div>
            <div class="buttons">
                <input type="submit" value="Enter Job Record into Database">
                <input type="reset" value="Clear Data and Results" onclick = "document.getElementById('results').innerHTML = '';">
            </div>
        </form>
    </div>

    <!-- Shipments Insert Section -->
    <div class="section">
        <form method="post" action="shipmentInsert">
            <div class="form-box">
                <input name="snum" placeholder="snum">
                <input name="pnum" placeholder="pnum">
                <input name="jnum" placeholder="jnum">
                <input name="quantity" placeholder="quantity">
            </div>
            <div class="buttons">
                <input type="submit" value="Enter Shipment Record into Database">
                <input type="reset" value="Clear Data and Results" onclick = "document.getElementById('results').innerHTML = '';">
            </div>
        </form>
    </div>

    <div class="results-area">
    <h3>Execution Results:</h3>
    <div class="results-content" id="results">
      <%
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
        } 
        else {
      %>
          <p>No results found.</p>
      <%
        }
      %>
    </div>
  </div>

</body>
</html>
