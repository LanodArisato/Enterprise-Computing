# 🖥️ CNT 4714 – Enterprise Computing Projects  

This repository contains a series of projects for **CNT 4714 – Enterprise Computing**, focusing on event-driven programming, databases, client-server systems, and multi-tier web applications.  

Each project builds upon the previous, gradually simulating enterprise-level software systems.  

---

## 📦 Project 1: Nile Dot Com (Java E-Store Simulation)  
A **Java Swing GUI** application simulating an e-store.  
- Reads inventory from `inventory.csv`  
- Allows users to add up to 5 items to a shopping cart  
- Generates invoices with tax & discounts  
- Logs all transactions to `transactions.csv`  

[Read More →](./Project1/README.md)  

---

## 🚂 Project 2: Train Yard  
A **Java multithreading project** simulating a train yard environment.  
- Implements multiple trains moving concurrently on shared tracks  
- Demonstrates **thread synchronization and locking** to prevent collisions and deadlocks  
- Emphasizes safe concurrent access to shared resources using Java concurrency mechanisms  
- Focuses on the design and coordination of threads rather than database transactions    

[Read More →](./Project2/README.md)  

---

## 🗄️ Project 3: Two-Tier Client-Server Application  
A **Java client-server application** using **JDBC and MySQL**.  
- Allows multiple clients to execute SQL commands on a remote database  
- Implements role-based permissions for root, client, and accountant users  
- Logs all user operations in a separate database  
- GUI front-end validates user credentials and displays query results  

---

## 🌐 Project 4: Three-Tier Distributed Web Application  
A **multi-tier enterprise application** using **Servlets, JSP, JDBC, and MySQL**.  
- User login with role-based access (root, client, data-entry, accountant)  
- Enforces business rules (supplier status updates, shipments)  
- Implements stored procedures and prepared statements  
- Runs on **Apache Tomcat** with a persistent MySQL backend  

[Read More →](./Project4/README.md)  

---
