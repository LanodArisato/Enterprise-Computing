# 🛒 Project 1: Event Driven Programming 

## 📖 Project Overview  
This project is a **Java-based GUI application** that simulates a simplified e-store called **Nile Dot Com** (a nod to Amazon.com).  

The application allows users to browse inventory, add items to a shopping cart, and generate an invoice upon checkout. It also maintains a transaction log for all purchases, simulating high-level enterprise e-commerce functionality.  

---

## ✨ Features  
- **Inventory Management**: Reads items from `inventory.csv`, which contains item ID, description, stock status, quantity on hand, and unit price.  
- **Shopping Cart**: Supports up to **5 unique items** per transaction.  
- **GUI Interface**: Built using **Java Swing**, includes input fields, shopping cart display, and six main control buttons (**search, add to cart, delete, checkout, reset, exit**).  
- **Error Handling**: Displays message dialogs for **out-of-stock items, insufficient stock, and invalid item IDs**.  
- **Checkout & Invoicing**: Generates an order summary with **subtotal, tax (6%), discounts (if applicable), and final total**.  
- **Transaction Logging**: Appends each transaction to `transactions.csv`, uniquely identified by a **date/time-based transaction ID**.  

---

## 📂 Files  
- **inventory.csv** – Input file containing store inventory.  
- **transactions.csv** – Output log file storing all completed transactions.  
- ***.java** – Source files implementing the GUI and application logic.  

---

## 📸 Deliverables  
- **Executable GUI application**.  
- **Screenshots showcasing major use cases**:  
  - Initial GUI  
  - Item search and add-to-cart process  
  - Checkout invoice  
  - Out-of-stock and invalid item handling  
  - Cart reset and deletion  

- **Example transaction log** with at least 5 transactions.  

---

## 🛠 Tech Stack  
- **Language**: Java  
- **Paradigm**: Event-driven programming  
- **Libraries**: Java Swing (for GUI)  
