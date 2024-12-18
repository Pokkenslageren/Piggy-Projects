use project_planner;

-- Insert test company
INSERT INTO companies (company_name)
VALUES ('Alpha Solutions');

-- Insert test user
INSERT INTO users (company_id, user_name, user_password)
VALUES (1, 'test_user', 'test123');

-- Insert test projects
INSERT INTO projects (company_id, user_id, project_name, start_date, end_date,
                      is_complete, project_description)
VALUES
    (1, 1, 'Website Redesign', '2024-01-01', '2024-06-30',
     false, 'Complete overhaul of company website'),
    (1, 1, 'Mobile App Development', '2024-02-01', '2024-08-31',
     false, 'New customer service mobile application');

-- Insert test subprojects for Website Redesign (project_id = 1)
INSERT INTO subprojects (project_id, subproject_name, start_date, end_date,
                         is_complete, subproject_description, priority)
VALUES
    (1, 'Frontend Development', '2024-01-01', '2024-04-30',
     false, 'Implement new user interface', 'HIGH'),
    (1, 'Backend Development', '2024-02-01', '2024-05-31',
     false, 'Update server architecture', 'HIGH');

-- Insert test subprojects for Mobile App (project_id = 2)
INSERT INTO subprojects (project_id, subproject_name, start_date, end_date,
                         is_complete, subproject_description, priority)
VALUES
    (2, 'App Design', '2024-02-01', '2024-03-31',
     false, 'Design user interface and experience', 'HIGH');

-- Insert test tasks for Frontend Development (subproject_id = 1)
INSERT INTO tasks (subproject_id, task_name, start_date, end_date,
                   estimated_cost, assigned_employees, is_complete,
                   task_description, hours_allocated, priority)
VALUES
    (1, 'Design Homepage', '2024-01-01', '2024-02-28',
     20000.0, 2, false, 'Create new homepage design', 80, 'HIGH'),
    (1, 'Implement Responsive Design', '2024-02-01', '2024-03-31',
     15000.0, 1, false, 'Make website mobile-friendly', 60, 'MEDIUM');

-- Insert test tasks for Backend Development (subproject_id = 2)
INSERT INTO tasks (subproject_id, task_name, start_date, end_date,
                   estimated_cost, assigned_employees, is_complete,
                   task_description, hours_allocated, priority)
VALUES
    (2, 'Database Migration', '2024-02-01', '2024-03-31',
     30000.0, 2, false, 'Migrate to new database structure', 100, 'HIGH'),
    (2, 'Database Optimization', '2024-03-31', '2024-03-31',
     50000.0, 4, false, 'Optimize data model', 250, 'HIGH');