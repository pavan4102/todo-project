# To-Do Application with Project-Based Organization

A project-based To-Do application built with Spring Boot and Thymeleaf, allowing users to create projects and manage tasks with CRUD operations, status updates, and organized project views. Ideal for structured task management with a simple and responsive design.

## Features

- *User Authentication*: Register and login functionalities for users.
- *Project Management*: Create, view, edit, and delete projects.
- *Task Management*: Within each project, users can add, view, edit, and delete To-Do items.
- *Task Status Update*: Tasks can be marked as "Completed" or "Pending" based on the user’s progress.
- *Responsive Design*: Styled with Bootstrap for a clean and user-friendly interface.

## Technology Stack

- *Backend*: Spring Boot, Hibernate JPA
- *Frontend*: Thymeleaf, Bootstrap 5
- *Database*: MySQL
- *Build Tool*: Maven

## Database Schema

The application uses a MySQL database with the following tables:

1. *User*: Stores user information (ID, username, password).
2. *Project*: Stores project details, each linked to a specific user.
3. *To-Do*: Stores individual To-Do items, each associated with a specific project.

## Getting Started

### Prerequisites

- *Java* (version 8 or later)
- *Maven* (for building the project)
- *MySQL* (for the database)

### Installation and Setup

1. *Clone the repository*:
   bash
   git clone https://github.com/pavan4102/todo-project.git
   
2. *Navigate into the project directory*:
   bash
   cd your-repo-name
   
3. *Configure the Database*:
   bash
   CREATE DATABASE todo_db;
   
4. **Update the application.properties file with your MySQL database credentials**:
   bash
   spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   
5. **Run the project in the IDE with ToDoApplication.java as the main file**
