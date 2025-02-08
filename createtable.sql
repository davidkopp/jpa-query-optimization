USE testdb;

DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS Department;

CREATE TABLE Department (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES Department(id)
);

-- Insert 50 departments
INSERT INTO Department (id, name) VALUES
    (1, 'Department 1'),
    (2, 'Department 2'),
    (3, 'Department 3'),
    (4, 'Department 4'),
    (5, 'Department 5'),
    (6, 'Department 6'),
    (7, 'Department 7'),
    (8, 'Department 8'),
    (9, 'Department 9'),
    (10, 'Department 10'),
    (11, 'Department 11'),
    (12, 'Department 12'),
    (13, 'Department 13'),
    (14, 'Department 14'),
    (15, 'Department 15'),
    (16, 'Department 16'),
    (17, 'Department 17'),
    (18, 'Department 18'),
    (19, 'Department 19'),
    (20, 'Department 20'),
    (21, 'Department 21'),
    (22, 'Department 22'),
    (23, 'Department 23'),
    (24, 'Department 24'),
    (25, 'Department 25'),
    (26, 'Department 26'),
    (27, 'Department 27'),
    (28, 'Department 28'),
    (29, 'Department 29'),
    (30, 'Department 30'),
    (31, 'Department 31'),
    (32, 'Department 32'),
    (33, 'Department 33'),
    (34, 'Department 34'),
    (35, 'Department 35'),
    (36, 'Department 36'),
    (37, 'Department 37'),
    (38, 'Department 38'),
    (39, 'Department 39'),
    (40, 'Department 40'),
    (41, 'Department 41'),
    (42, 'Department 42'),
    (43, 'Department 43'),
    (44, 'Department 44'),
    (45, 'Department 45'),
    (46, 'Department 46'),
    (47, 'Department 47'),
    (48, 'Department 48'),
    (49, 'Department 49'),
    (50, 'Department 50');

-- Insert 150 employees (3 per department)
INSERT INTO Employee (id, name, department_id) VALUES
    (1, 'Employee 1A', 1),
    (2, 'Employee 1B', 1),
    (3, 'Employee 1C', 1),
    (4, 'Employee 2A', 2),
    (5, 'Employee 2B', 2),
    (6, 'Employee 2C', 2),
    (7, 'Employee 3A', 3),
    (8, 'Employee 3B', 3),
    (9, 'Employee 3C', 3),
    (10, 'Employee 4A', 4),
    (11, 'Employee 4B', 4),
    (12, 'Employee 4C', 4),
    (13, 'Employee 5A', 5),
    (14, 'Employee 5B', 5),
    (15, 'Employee 5C', 5),
    (16, 'Employee 6A', 6),
    (17, 'Employee 6B', 6),
    (18, 'Employee 6C', 6),
    (19, 'Employee 7A', 7),
    (20, 'Employee 7B', 7),
    (21, 'Employee 7C', 7),
    (22, 'Employee 8A', 8),
    (23, 'Employee 8B', 8),
    (24, 'Employee 8C', 8),
    (25, 'Employee 9A', 9),
    (26, 'Employee 9B', 9),
    (27, 'Employee 9C', 9),
    (28, 'Employee 10A', 10),
    (29, 'Employee 10B', 10),
    (30, 'Employee 10C', 10),
    (31, 'Employee 11A', 11),
    (32, 'Employee 11B', 11),
    (33, 'Employee 11C', 11),
    (34, 'Employee 12A', 12),
    (35, 'Employee 12B', 12),
    (36, 'Employee 12C', 12),
    (37, 'Employee 13A', 13),
    (38, 'Employee 13B', 13),
    (39, 'Employee 13C', 13),
    (40, 'Employee 14A', 14),
    (41, 'Employee 14B', 14),
    (42, 'Employee 14C', 14),
    (43, 'Employee 15A', 15),
    (44, 'Employee 15B', 15),
    (45, 'Employee 15C', 15),
    (46, 'Employee 16A', 16),
    (47, 'Employee 16B', 16),
    (48, 'Employee 16C', 16),
    (49, 'Employee 17A', 17),
    (50, 'Employee 17B', 17),
    (51, 'Employee 17C', 17),
    (52, 'Employee 18A', 18),
    (53, 'Employee 18B', 18),
    (54, 'Employee 18C', 18),
    (55, 'Employee 19A', 19),
    (56, 'Employee 19B', 19),
    (57, 'Employee 19C', 19),
    (58, 'Employee 20A', 20),
    (59, 'Employee 20B', 20),
    (60, 'Employee 20C', 20),
    (61, 'Employee 21A', 21),
    (62, 'Employee 21B', 21),
    (63, 'Employee 21C', 21),
    (64, 'Employee 22A', 22),
    (65, 'Employee 22B', 22),
    (66, 'Employee 22C', 22),
    (67, 'Employee 23A', 23),
    (68, 'Employee 23B', 23),
    (69, 'Employee 23C', 23),
    (70, 'Employee 24A', 24),
    (71, 'Employee 24B', 24),
    (72, 'Employee 24C', 24),
    (73, 'Employee 25A', 25),
    (74, 'Employee 25B', 25),
    (75, 'Employee 25C', 25),
    (76, 'Employee 26A', 26),
    (77, 'Employee 26B', 26),
    (78, 'Employee 26C', 26),
    (79, 'Employee 27A', 27),
    (80, 'Employee 27B', 27),
    (81, 'Employee 27C', 27),
    (82, 'Employee 28A', 28),
    (83, 'Employee 28B', 28),
    (84, 'Employee 28C', 28),
    (85, 'Employee 29A', 29),
    (86, 'Employee 29B', 29),
    (87, 'Employee 29C', 29),
    (88, 'Employee 30A', 30),
    (89, 'Employee 30B', 30),
    (90, 'Employee 30C', 30),
    (91, 'Employee 31A', 31),
    (92, 'Employee 31B', 31),
    (93, 'Employee 31C', 31),
    (94, 'Employee 32A', 32),
    (95, 'Employee 32B', 32),
    (96, 'Employee 32C', 32),
    (97, 'Employee 33A', 33),
    (98, 'Employee 33B', 33),
    (99, 'Employee 33C', 33),
    (100, 'Employee 34A', 34),
    (101, 'Employee 34B', 34),
    (102, 'Employee 34C', 34),
    (103, 'Employee 35A', 35),
    (104, 'Employee 35B', 35),
    (105, 'Employee 35C', 35),
    (106, 'Employee 36A', 36),
    (107, 'Employee 36B', 36),
    (108, 'Employee 36C', 36),
    (109, 'Employee 37A', 37),
    (110, 'Employee 37B', 37),
    (111, 'Employee 37C', 37),
    (112, 'Employee 38A', 38),
    (113, 'Employee 38B', 38),
    (114, 'Employee 38C', 38),
    (115, 'Employee 39A', 39),
    (116, 'Employee 39B', 39),
    (117, 'Employee 39C', 39),
    (118, 'Employee 40A', 40),
    (119, 'Employee 40B', 40),
    (120, 'Employee 40C', 40),
    (121, 'Employee 41A', 41),
    (122, 'Employee 41B', 41),
    (123, 'Employee 41C', 41),
    (124, 'Employee 42A', 42),
    (125, 'Employee 42B', 42),
    (126, 'Employee 42C', 42),
    (127, 'Employee 43A', 43),
    (128, 'Employee 43B', 43),
    (129, 'Employee 43C', 43),
    (130, 'Employee 44A', 44),
    (131, 'Employee 44B', 44),
    (132, 'Employee 44C', 44),
    (133, 'Employee 45A', 45),
    (134, 'Employee 45B', 45),
    (135, 'Employee 45C', 45),
    (136, 'Employee 46A', 46),
    (137, 'Employee 46B', 46),
    (138, 'Employee 46C', 46),
    (139, 'Employee 47A', 47),
    (140, 'Employee 47B', 47),
    (141, 'Employee 47C', 47),
    (142, 'Employee 48A', 48),
    (143, 'Employee 48B', 48),
    (144, 'Employee 48C', 48),
    (145, 'Employee 49A', 49),
    (146, 'Employee 49B', 49),
    (147, 'Employee 49C', 49),
    (148, 'Employee 50A', 50),
    (149, 'Employee 50B', 50),
    (150, 'Employee 50C', 50);
