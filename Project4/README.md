# 🌐 Project 4: Three-Tier Distributed Web-Based Application

## Overview
This project is a **Java-based three-tier distributed web application** that integrates **Servlets, JSPs, JDBC, and MySQL** to simulate an enterprise-level system. Users log in with different credentials, which determine their access level and the type of operations they can perform. The system enforces business logic, manages a persistent database, and demonstrates full-stack enterprise application development.

---

## Features

**Multi-Tier Architecture**  
- **Front-End (Client):** HTML landing page for login, JSP pages for different user roles.  
- **Middle-Tier (Business Logic):** Java Servlets process requests, enforce rules, and interact with the database.  
- **Back-End (Database):** MySQL database (`project4`) storing suppliers, parts, jobs, and shipments.  

**User Roles**  
- **Root User:** Full access, including query/updates with business logic enforcement.  
- **Client User:** Read-only access (queries only).  
- **Data Entry User:** Can add/update records via parameterized `PreparedStatement`.  
- **Accountant User:** Runs stored procedures via `CallableStatement` for report generation.  

**Business Logic**  
- Automatically increments a supplier’s status by 5 when involved in a shipment with quantity ≥ 100.  

**Database Structure**  
- **Suppliers:** `snum, sname, status, city`  
- **Parts:** `pnum, pname, color, weight, city`  
- **Jobs:** `jnum, jname, numworkers, city`  
- **Shipments:** `snum, pnum, jnum, quantity` (composite key with foreign keys)  

**Error Handling**  
- MySQL errors are caught and displayed in the GUI.  
- Invalid or unauthorized operations produce descriptive error messages.  

---

## Files

- `project4DBscript.sql` – Creates and populates the `project4` database.  
- `credentialsDBscript.sql` – Creates authentication database and user credentials.  
- `ClientCreationPermissionsScript.sql` – Grants privileges for root, client, dataentry, and accountant roles.  
- `project4rootcommands.sql` – SQL commands for root-level user.  
- `project4clientcommands.sql` – SQL commands for client-level user.  
- `project4dataentrycommands.sql` – SQL commands for data-entry user.  
- `project4accountantcommands.txt` – Stored procedure calls for accountant role.  
- `*.java` – Servlets implementing authentication, business logic, and database connectivity.  
- JSP/HTML files – User-facing pages for login and role-based interfaces.  

---

## Tech Stack

- **Language:** Java  
- **Frameworks:** Servlets, JSP  
- **Database:** MySQL 9.2.0  
- **Libraries:** JDBC  
- **Server:** Apache Tomcat 11.0.4  
- **Paradigm:** Event-driven, three-tier architecture  
