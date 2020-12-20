CREATE TABLE IF NOT EXISTS `users` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_name` varchar(20),
    `password` varchar(20),
    `active` int(6),
    `roles` varchar(50),
    `annual_leave_days` int(6)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;