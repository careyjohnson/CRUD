DROP DATABASE IF EXISTS n0622;
CREATE DATABASE n0622;
USE n0622;

CREATE TABLE `position` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `emp_code` varchar(10) NOT NULL,
  `name` varchar(45) NOT NULL,
  `birthday` date NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `gender` varchar(45) NOT NULL DEFAULT '1',
  `position_id` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `emp_code_UNIQUE` (`emp_code`),
  CONSTRAINT `fk_position` FOREIGN KEY (`id`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT position (name) values
('Truong phong'),
('Giam doc'),
('Quan ly'),
('Nhan vien');

INSERT employee (emp_code,name,birthday,address,gender,position_id) values
('GD1','Nguyen Thanh Mai','2000-01-12','Ha Noi','0','2'),
('TP1','Nguyen Doan Trang','1997-04-09','Hai Phong','0','1'),
('QL1','Tran Tung Duong','1996-10-23','Nam Dinh','1','3'),
('NV1','Vu Khanh Toan','2002-08-17','Ha Noi','1','4');

select * from employee,position where employee.position_id=position.id
