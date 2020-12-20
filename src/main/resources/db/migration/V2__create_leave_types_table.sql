CREATE TABLE IF NOT EXISTS `leave_types` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(20),
    `max_duration` int(6)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;