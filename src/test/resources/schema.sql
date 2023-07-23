
CREATE TABLE Authors (
    id bigint NOT NULL auto_increment,
    author_name VARCHAR(120) NOT NULL,
    nationality VARCHAR(50),
    annotation VARCHAR(255)NOT NULL,
    PRIMARY KEY(id)
);


CREATE TABLE Books (
    id bigint NOT NULL auto_increment,
    book_name VARCHAR(120) NOT NULL,
    author_id bigint NOT NULL,
    publisher_name VARCHAR(120) NOT NULL,
    isbn VARCHAR(120) NOT NULL,
    total_pages bigint,
    price decimal(8,2)NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (author_id) REFERENCES Authors (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);


CREATE TABLE Customers (
    id bigint NOT NULL auto_increment,
    customer_name VARCHAR(120) NOT NULL,
    email VARCHAR(120) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(200) NOT NULL,
    PRIMARY KEY(id)
);


CREATE TABLE Orders (
    id bigint NOT NULL auto_increment,
    customer_id bigint NOT NULL,
    order_date DATE NOT NULL,
    total_amount decimal(8,2) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (customer_id) REFERENCES Customers (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);


CREATE TABLE Order_Items (
    id bigint NOT NULL auto_increment,
    order_id bigint NOT NULL,
    book_id bigint NOT NULL,
    quantity bigint NOT NULL,
    item_price decimal(8,2) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (order_id) REFERENCES Orders (id)
    ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES Books (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);