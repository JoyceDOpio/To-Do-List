#To-Do-List

The application was built using Spring Boot and Angular.

Requirements:
- nodejs
- angular
- @angular-devkit/build-angular
- MYSQL Server

Database setting:

CREATE DATABASE IF NOT EXISTS toDo_db;

USE toDo_db;

CREATE TABLE IF NOT EXISTS lists(
	id CHAR(36) PRIMARY KEY, 
	list_title VARCHAR(100) UNIQUE
);

CREATE TABLE IF NOT EXISTS tasks(
	id CHAR(36) PRIMARY KEY, 
	task_name VARCHAR(100), 
	is_checked BOOLEAN, 
	list_id CHAR(36) REFERENCES lists(id)
);
