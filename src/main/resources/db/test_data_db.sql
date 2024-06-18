CREATE TABLE `events` (
	`id` INT(11) NULL DEFAULT '1',
	`name` TEXT NULL DEFAULT 'Imagine Dragons' COLLATE 'utf8mb4_general_ci',
	`city` TEXT NULL DEFAULT 'Kyiv' COLLATE 'utf8mb4_general_ci',
	`place` TEXT NULL DEFAULT 'Palats Sportu' COLLATE 'utf8mb4_general_ci',
	`time` DATETIME NULL DEFAULT '2024-03-20 19:00:00',
	`description` TEXT NULL DEFAULT 'An incredible concert' COLLATE 'utf8mb4_general_ci'
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;
