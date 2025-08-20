# 🗄️ Project Three: A Two-Tier Client-Server Application Using MySQL and JDBC

---

## 📖 Overview  
This project focuses on building a **two-tier Java client-server application** that connects to a **MySQL database** using **JDBC**. The goal is to gain hands-on experience with **JDBC features**, database connectivity, and permissions management through different client roles.  

The project consists of two Java applications:  
1. **General Client Application** – A GUI-based tool allowing users to execute SQL DDL and DML commands.  
2. **Specialized Accountant Application** – A restricted interface that provides query access to an operational logging database.  

In addition, the application maintains a **transaction log** in a separate database, tracking the number of queries and updates executed by each user across all sessions.  

---

## ✨ Features  
- **Java GUI Front-End:**  
  - Execute SQL DDL and DML commands through a user-friendly interface.  
  - Supports multiple concurrent user connections.  
  - Buttons for connecting/disconnecting, clearing input, and resetting results.  

- **Client Roles & Permissions:**  
  - **root:** full permissions across databases.  
  - **client1:** limited to `SELECT` on `project3` and `bikedb`.  
  - **client2:** limited to `SELECT` and `UPDATE` on `project3` and `bikedb`.  
  - **project3app:** manages inserts/updates into the operations log database.  
  - **theaccountant:** restricted to `SELECT` queries on the logging database.  

- **Transaction Logging:**  
  - Maintains a separate `operationslog` database.  
  - Tracks the number of queries and updates per user.  
  - Logging performed automatically in the background, invisible to clients.  

- **Error Handling:**  
  - Verifies credentials against properties files before allowing a connection.  
  - Displays error messages for invalid credentials.  
  - Non-query commands provide execution status messages.  

- **PreparedStatement Enforcement:**  
  - All client commands must use JDBC `PreparedStatement` (no exceptions).  

---

## 📂 Files  
- `project3dbscript.sql` → Creates and populates the `project3` database.  
- `project3operationslog.sql` → Creates the `operationslog` database.  
- `clientCreationScriptProject3.sql` → Creates four client-level users (`client1`, `client2`, `project3app`, `theaccountant`).  
- `clientPermissionsScriptProject3.sql` → Assigns appropriate permissions to each user.  
- `project3rootscript.sql` → Commands to test functionality for the `root` user.  
- `project3client1script.sql` → Commands for testing as `client1`.  
- `project3client2script.sql` → Commands for testing as `client2`.  

---

## 🛠️ Tech Stack  
- **Language:** Java (JDBC API)  
- **Database:** MySQL 9.2.0  
- **Tools:** MySQL Workbench, Command Line Client  
- **Frameworks/Libraries:** Swing (Java GUI), JDBC Driver  
- **Environment:**  
  - Multi-user support (up to 151 concurrent connections by default).  
  - Properties files for user credentials and DB connection details.  

---
