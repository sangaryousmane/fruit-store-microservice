CREATE TABLE fruits(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    fruitName     VARCHAR(255)          NULL,
    `description` VARCHAR(255)          NULL,
    quantity      INT                   NOT NULL,
    price         DOUBLE                NOT NULL,
    CONSTRAINT pk_fruits PRIMARY KEY (id)
);