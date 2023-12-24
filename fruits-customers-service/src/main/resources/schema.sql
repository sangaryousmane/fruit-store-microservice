CREATE TABLE customers(
    customerId VARCHAR(255) NOT NULL,
    firstName  VARCHAR(255) NULL,
    lastName   VARCHAR(255) NULL,
    email      VARCHAR(255) NULL,
    country    VARCHAR(255) NULL,
    postalCode VARCHAR(255) NULL,
    city       VARCHAR(255) NULL,
    CONSTRAINT pk_customers PRIMARY KEY (customerId)
);

ALTER TABLE customers
    ADD CONSTRAINT uniqueCustomer UNIQUE (email);