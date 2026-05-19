# Java Product Sales & Database Management System

A Java desktop application for managing products and recording sales using Java Swing, MySQL, and JDBC. The system supports full CRUD operations (Create, Read, Update, Delete) and demonstrates object-oriented programming principles.

---

## Features

- Add new products
- Update existing products
- Delete products
- View all products in a table
- Record sales transactions
- Calculate total sales
- MySQL database integration
- Java Swing graphical user interface

---

## Technologies Used

- Java (OOP)
- Java Swing (GUI)
- MySQL Database
- JDBC (Database Connectivity)

---

## Project Structure
src/
│
├── database/
│ └── DBConnection.java
│
├── model/
│ └── Product.java
│
├── dao/
│ └── ProductDAO.java
│
├── gui/
│ └── ProductManagementGUI.java
│
└── Main.java

---

## Database Setup

### 1. Create Database

Run the following SQL in phpMyAdmin or MySQL Workbench:

```sql
CREATE DATABASE ProductSalesDB;

USE ProductSalesDB;

CREATE TABLE products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100),
    category VARCHAR(100),
    price DOUBLE,
    quantity INT
);

CREATE TABLE sales (
    sale_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100),
    quantity_sold INT,
    total_price DOUBLE,
    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
