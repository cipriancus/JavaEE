CREATE DATABASE students;

USE students;

CREATE TABLE student_record (student_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, student_name VARCHAR(100), student_email VARCHAR(50), student_password VARCHAR(20), student_gender VARCHAR(1), student_address VARCHAR(100));

INSERT INTO student_record (student_name, student_email, student_password, student_gender, student_address) VALUES ('John', 'john@jcg.com', 'access@123', 'M', 'America');
INSERT INTO student_record (student_name, student_email, student_password, student_gender, student_address) VALUES ('Monica', 'monica@jcg.com', 'access@123', 'F', 'Iceland');
INSERT INTO student_record (student_name, student_email, student_password, student_gender, student_address) VALUES ('Raymond', 'raymond@jcg.com', 'access@123', 'M', 'Greece');
INSERT INTO student_record (student_name, student_email, student_password, student_gender, student_address) VALUES ('Jane', 'jane@jcg.com', 'access@123', 'F', 'Norway');
INSERT INTO student_record (student_name, student_email, student_password, student_gender, student_address) VALUES ('Rachel', 'rachel@jcg.com', 'access@123', 'F', 'France');

SELECT * FROM student_record;

use students;

CREATE TABLE project_record (project_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, project_name VARCHAR(100), project_description VARCHAR(100), project_max_number int);

INSERT INTO project_record (project_name, project_description, project_max_numberproject_record) VALUES ('CryMa', 'Cryptocurency manager', 3);
INSERT INTO project_record (project_name, project_description, project_max_number) VALUES ('DaCa', 'Online media convertor', 4);
INSERT INTO project_record (project_name, project_description, project_max_number) VALUES ('RaZa', 'Provides data about the sun', 5);
INSERT INTO project_record (project_name, project_description, project_max_number) VALUES ('GeO', 'Geology monitor', 6);
INSERT INTO project_record (project_name, project_description, project_max_number) VALUES ('Fake News Detector', 'Fake news detection o twitter', 4);
