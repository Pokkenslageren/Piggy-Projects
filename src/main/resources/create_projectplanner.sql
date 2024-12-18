USE project_planner;

CREATE DATABASE IF NOT EXISTS project_planner;

DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS subprojects;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS companies;

CREATE TABLE companies (
                           company_id int NOT NULL AUTO_INCREMENT,
                           company_name varchar(255),
                           PRIMARY KEY (company_id)
);

CREATE TABLE users (
                       user_id int NOT NULL AUTO_INCREMENT,
                       company_id int,
                       user_name varchar(255),
                       user_password varchar(64) NOT NULL,
                       PRIMARY KEY (user_id),
                       FOREIGN KEY (company_id) REFERENCES companies (company_id)
);

CREATE TABLE projects (
                          project_id int NOT NULL AUTO_INCREMENT,
                          company_id int,
                          user_id int DEFAULT NULL,
                          project_name varchar(255),
                          start_date date NOT NULL,
                          end_date date,
                          total_estimated_cost double,
                          total_assigned_employees int,
                          is_complete boolean,
                          project_description text,
                          PRIMARY KEY (project_id),
                          FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE subprojects (
                             subproject_id int NOT NULL AUTO_INCREMENT,
                             project_id int DEFAULT NULL,
                             subproject_name varchar(255),
                             start_date date NOT NULL,
                             end_date date,
                             total_estimated_cost double,
                             total_assigned_employees int NOT NULL DEFAULT 0,
                             total_actual_cost double,
                             is_complete boolean,
                             subproject_description text,
                             hours_allocated int,
                             priority varchar(10),
                             PRIMARY KEY (subproject_id),
                             FOREIGN KEY (project_id) REFERENCES projects (project_id)
);

CREATE TABLE tasks (
                       task_id int NOT NULL AUTO_INCREMENT,
                       subproject_id int DEFAULT NULL,
                       task_name varchar(255),
                       start_date date,
                       end_date date,
                       estimated_cost double,
                       assigned_employees int,
                       is_complete boolean,
                       task_description text,
                       hours_allocated int,
                       priority varchar(10),
                       PRIMARY KEY (task_id),
                       FOREIGN KEY (subproject_id) REFERENCES subprojects (subproject_id)
);