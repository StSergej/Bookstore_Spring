CREATE TABLE Authors (
    author_id int NOT NULL auto_increment,
    author_name VARCHAR(120) NOT NULL,
    nationality VARCHAR(50),
    annotation VARCHAR(255)NOT NULL,
    PRIMARY KEY(author_id)
);


CREATE TABLE Books (
    book_id int NOT NULL auto_increment,
    book_name VARCHAR(120) NOT NULL,
    author_id int NOT NULL,
    publisher_name VARCHAR(120) NOT NULL,
    isbn VARCHAR(120) NOT NULL,
    edition VARCHAR(50),
    total_pages int,
    printing int,
    city VARCHAR(50),
    price decimal(8,2)NOT NULL,
    PRIMARY KEY(book_id),
    FOREIGN KEY (author_id) REFERENCES Authors (author_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);


CREATE TABLE Customers (
    customer_id int NOT NULL auto_increment,
    customer_name VARCHAR(120) NOT NULL,
    email VARCHAR(120) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(200) NOT NULL,
    PRIMARY KEY(customer_id)
);


CREATE TABLE Orders (
    order_id int NOT NULL auto_increment,
    customer_id int NOT NULL,
    order_date DATE NOT NULL,
    total_amount decimal(8,2) NOT NULL,
    PRIMARY KEY(order_id),
    FOREIGN KEY (customer_id) REFERENCES Customers (customer_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);


CREATE TABLE Order_Items (
    order_item_id int NOT NULL auto_increment,
    order_id int NOT NULL,
    book_id int NOT NULL,
    quantity int NOT NULL,
    item_price decimal(8,2) NOT NULL,
    PRIMARY KEY(order_item_id),
    FOREIGN KEY (order_id) REFERENCES Orders (order_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES Books (book_id)
    ON UPDATE CASCADE ON DELETE CASCADE
);