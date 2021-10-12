/*
Creating Database
*/
CREATE DATABASE p2k_district
CHARACTER SET latin1
COLLATE latin1_general_cs;

USE p2k_district;

/*
Creating User Table
*/
CREATE TABLE `user`(
user_id INT UNSIGNED AUTO_INCREMENT NOT NULL,
username CHAR(8) NOT NULL UNIQUE,
`password` VARCHAR(25) NOT NULL,
first_name VARCHAR(25) NOT NULL,
middle_initial CHAR(1) NULL,
last_name VARCHAR(25) NOT NULL,
user_type ENUM('Student', 'Teacher', 'Guardian', 'Faculty', 'Admin') NOT NULL,
phone VARCHAR(15) NULL,
address VARCHAR(100) NULL,
school VARCHAR(100) NOT NULL,
PRIMARY KEY (user_id),
CONSTRAINT valid_username CHECK (username REGEXP '^[:lower:]{8}$'),
CONSTRAINT valid_password CHECK (LENGTH(`password`) BETWEEN 8 AND 25));

/*
Creating Index for User table according to user_type
*/
CREATE INDEX user_index ON `user` (user_type);

CREATE TABLE course(
course_id INT UNSIGNED AUTO_INCREMENT NOT NULL,
course_name VARCHAR(50) NOT NULL,
teacher_id INT UNSIGNED,
PRIMARY KEY (course_id),
FOREIGN KEY (teacher_id) REFERENCES `user`(user_id) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE assignment(
assignment_id INT UNSIGNED AUTO_INCREMENT NOT NULL,
assignment_name VARCHAR(100) NOT NULL,
deadline DATE NOT NULL,
course_id INT UNSIGNED,
student_id INT UNSIGNED,
earned_points INT UNSIGNED NULL,
total_points INT UNSIGNED NOT NULL,
score DECIMAL(5, 2) AS 
(CASE
	WHEN earned_points IS NULL THEN NULL
    ELSE (earned_points / total_points * 100)
END),
grade VARCHAR(2) AS
(CASE
	WHEN score IS NULL THEN NULL
    WHEN (score < 60) THEN 'F'
    WHEN (score >= 60 AND score < 63) THEN 'D-'
    WHEN (score >= 63 AND score < 67) THEN 'D'
    WHEN (score >= 67 AND score < 70) THEN 'D+'
    WHEN (score >= 70 AND score < 73) THEN 'C-'
    WHEN (score >= 73 AND score < 77) THEN 'C'
    WHEN (score >= 77 AND score < 80) THEN 'C+'
    WHEN (score >= 80 AND score < 83) THEN 'B-'
    WHEN (score >= 83 AND score < 87) THEN 'B'
    WHEN (score >= 87 AND score < 90) THEN 'B+'
    WHEN (score >= 90 AND score < 93) THEN 'A-'
    ELSE 'A'
END),
PRIMARY KEY (assignment_id),
FOREIGN KEY (course_id) REFERENCES course(course_id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (student_id) REFERENCES `user`(user_id) ON UPDATE CASCADE ON DELETE CASCADE);



INSERT INTO `user` VALUES
/*
Guaridans
*/
(NULL, 'pxaeqmlr', 'MetState8234', 'Timothy', NULL, 'Johnson', 'Guardian', '763-000-0000', '1000 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'riodrwrx', 'MetState8234', 'Martha', NULL, 'Johnson', 'Guardian', '763-000-0001', '1000 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'gqgcoczp', 'MetState8234', 'James', NULL, 'Wilson', 'Guardian', '763-000-0002', '1001 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'xuheebdm', 'MetState8234', 'Martha', NULL, 'Wilson', 'Guardian', '763-000-0003', '1001 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'mjfojite', 'MetState8234', 'Nicholas', NULL, 'Turner', 'Guardian', '763-000-0004', '1002 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'yxwjmcjr', 'MetState8234', 'Samantha', NULL, 'Turner', 'Guardian', '763-000-0005', '1002 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'ahcmzelq', 'MetState8234', 'Braden', 'F', 'Abrahamson', 'Guardian', '763-000-0006', '1003 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'kjzhzfqj', 'MetState8234', 'Abigail', 'Z', 'Abrahamson', 'Guardian', '763-000-0007', '1003 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'tmusatfs', 'MetState8234', 'William', NULL, 'Radley', 'Guardian', '763-000-0008', '1004 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'phkjavzt', 'MetState8234', 'Hannah', NULL, 'McCoy', 'Guardian', '763-000-0009', '1005 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
/*
Students
*/
(NULL, 'bztauzbk', 'MetState8234', 'Audrey', NULL, 'Johnson', 'Student', '763-000-0010', '1000 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'ozifgogc', 'MetState8234', 'Mark', NULL, 'Johnson', 'Student', '763-000-0001', '1000 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'aqlkrmtt', 'MetState8234', 'Stephanie', NULL, 'Johnson', 'Student', '763-000-0012', '1000 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'owmpesmh', 'MetState8234', 'James', NULL, 'Wilson', 'Student', '763-000-0013', '1001 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'ivytklfg', 'MetState8234', 'Gabriella', NULL, 'Wilson', 'Student', '763-000-0014', '1001 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'oiyddveh', 'MetState8234', 'Kathy', NULL, 'Turner', 'Student', '763-000-0015', '1002 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'uzspkqdu', 'MetState8234', 'Eric', 'P', 'Abrahamson', 'Student', '763-000-0016', '1003 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'piakhreg', 'MetState8234', 'Arthur', 'L', 'Radley', 'Student', '763-000-0017', '1004 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'dgyaqgry', 'MetState8234', 'Piper', 'D', 'Radley', 'Student', '763-000-0018', '1004 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'kveypqgm', 'MetState8234', 'Christine', NULL, 'McCoy', 'Student', '763-000-0019', '1005 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
/*
Teachers
*/
(NULL, 'maczvepd', 'MetState8234', 'Mary', NULL, 'Smith', 'Teacher', '763-000-0020', '1006 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'qjxphowl', 'MetState8234', 'Phillip', 'E', 'Doyle', 'Teacher', '763-000-0021', '1007 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
(NULL, 'wfjzxwbq', 'MetState8234', 'Patricia', NULL, 'Franklin', 'Teacher', '763-000-0022', '1008 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
/*
Faculty
*/
(NULL, 'ajdgxsvm', 'MetState8234', 'Stephen', NULL, 'Zimmerman', 'Faculty', '763-000-0023', '1009 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School'),
/*
Admin
*/
(NULL, 'kbpirnnp', 'MetState8234', 'Charles', NULL, 'King', 'Admin', '763-000-0024', '1010 21st Street, Blaine, MN, 55449', 'Spring Lake Park High School');


SELECT user_id, username
FROM `user`
WHERE user_type = 'Student';

/*
Adding Courses
*/
INSERT INTO course VALUES
(NULL, "Reading 101", 21),
(NULL, "Elementary Math 101", 21),
(NULL, "Computer Lab", 21),
(NULL, 'Precalculus', 22),
(NULL, 'Introduction to Art', 23);

/*
Adding Assignments
*/
INSERT INTO assignment (assignment_id, assignment_name, deadline, course_id, student_id, earned_points, total_points)
VALUES
/*
Assignments for Reading 101
*/
(NULL, 'Magic Treehouse #11 Summary', '2021-04-07', 1, 15, null, 20),
(NULL, 'Magic Treehouse #11 Summary', '2021-04-07', 1, 14, 16, 20),
/*
Assignments for Elementary Math 101
*/
(NULL, 'Algebra Chapter 6 Ex. 12 - 23', '2021-04-11', 2, 16, 0, 11),
(NULL, 'Algebra Chapter 1 Ex. 1 - 13', '2021-04-11', 2, 11, 10, 13),
/*
Assignments for Computer Lab
*/
(NULL, 'Scatterplot', '2021-04-18', 3, 19, 10, 10),
(NULL, 'Barchart', '2021-04-18', 3, 12, 11, 10),
(NULL, 'Super Database', '2021-04-30', 3, 18, 35, 40),
/*
Assignments for Precalculus
*/
(NULL, 'Precalculus Final', '2021-05-29', 4, 20, 196, 200),
(NULL, 'Precalculus Final', '2021-05-29', 4, 17, 127, 200),
/*
Assignments for Introduction to Art
*/
(NULL, 'Art Project', '2021-06-01', 5, 13, 151, 200);