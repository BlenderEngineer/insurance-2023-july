-- liquibase formatted sql

-- changeset dovias:1
CREATE TABLE `insurance_manager_v1`.`user_profile_roles` (
    `user_id` UUID NOT NULL,
    `company_manager` BOOLEAN,
    `consumer` BOOLEAN,
    `company_claim_manager` BOOLEAN,
    `consumer_claim_manager` BOOLEAN,
    `company_setting_manager` BOOLEAN,
    `company_report_manager` BOOLEAN,

    FOREIGN KEY (`user_id`)
    -- comment: if you're getting syntax error on an IDE on `users` table name,
    -- comment: ignore it, its due to the fact that the definition is in another file.
    REFERENCES `insurance_manager_v1`.`users` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE
);
-- rollback DROP TABLE `users_profile_roles`;