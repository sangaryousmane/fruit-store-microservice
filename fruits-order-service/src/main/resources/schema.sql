CREATE TABLE orders (
    orderId     VARCHAR(255) NOT NULL,
    customerId  INT          NOT NULL,
    fruitId     INT          NOT NULL,
    orderStatus VARCHAR(255) NULL,
    paymentType VARCHAR(255) NULL,
    CONSTRAINT pk_orders PRIMARY KEY (orderId)
);

ALTER TABLE orders
    ADD CONSTRAINT orderUnique UNIQUE (orderId);