CREATE TABLE IF NOT EXISTS `leaves` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `start_date` date,
    `end_date` date,
    `leave_type_id` int(6),
    `user_id` int(6)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;