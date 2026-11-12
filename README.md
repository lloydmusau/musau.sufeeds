# 🎓 Sufeeds (Student Feedback System)

A Java-based desktop application designed for students to securely register, log in, submit course feedback, and view feedback shared by their peers. Built using **Java Swing** for the graphical user interface and **MySQL** for backend data storage.

---

## ✨ Features

* **User Authentication:** Secure registration and login system with duplicate-username prevention.
* **Interactive Dashboard:** Submit feedback categorized by Course Name, Unit/Topic, and Lecturer Name.
* **Peer Feedback Stream:** View real-time feedback submitted by other students in a clean table layout.
* **Modern Java Swing GUI:** Built using custom layouts, split panes, and component grouping.
* **Object-Oriented Design:** Implements core OOP principles like Inheritance (`Academics` parent class and `Student` child class).

---

## 🛠️ Tech Stack & Architecture

* **Language:** Java (JDK 8+)
* **GUI Framework:** Java Swing & AWT (`javax.swing`, `java.awt`)
* **Database:** MySQL
* **Database Connectivity:** JDBC (`java.sql`)

### Project Class Structure (`com.musau.sufeeds`)
1. **`SufeedsGUI.java`** – Manages the graphical interface, screens (`CardLayout`), and user click actions.
2. **`databaseOperations.java`** – Handles all SQL queries for user registration, login authentication, and feedback management.
3. **`dbConnection.java`** – Manages the secure database connection string and credentials.
4. **`Student.java`** – Child class representing individual student feedback entries (inherits from `Academics`).
5. **`Academics.java`** – Parent class holding shared academic attributes (Course Name and Topic).

---

## 🗄️ Database Setup

To run this application, you will need a MySQL database configured with the following tables:

1. **`tbl_students`** (Stores login credentials)
   ```sql
   CREATE TABLE tbl_students (
       id INT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(50) UNIQUE NOT NULL,
       password VARCHAR(255) NOT NULL
   );
