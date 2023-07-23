
INSERT INTO Authors (author_name, nationality, annotation)
VALUES
    ( 'Harper Lee', 'American', 'American author known for "To Kill a Mockingbird".'),
    ( 'George Orwell', 'British', 'English author famous for "1984" and "Animal Farm".'),
    ( 'F. Scott Fitzgerald', 'American', 'American author of "The Great Gatsby" and other novels.'),
    ( 'Jane Austen', 'British', 'English author known for "Pride and Prejudice" and other classics.'),
    ( 'J.K. Rowling', 'British', 'British author of the "Harry Potter" series.'),
    ( 'J.D. Salinger', 'American', 'American author renowned for "The Catcher in the Rye".'),
    ( 'Jenny Han', 'American', 'Korean-American author of "To All the Boys I''ve Loved Before" series.'),
    ( 'J.R.R. Tolkien', 'British', 'English author of "The Hobbit" and "The Lord of the Rings" trilogy.');

SELECT * FROM Authors;

INSERT INTO Authors (author_name, nationality, annotation)
VALUES
    ( 'Paulo Coelho', 'Brazilian', 'Brazilian author known for "The Alchemist" and other inspirational works.'),
    ('Charles Dickens', 'British', 'English novelist, generally considered the greatest of the Victorian era.');

DELETE FROM Authors
WHERE author_name = 'Paulo Coelho';
---------------------------------------------------------------------------------------------------------------

INSERT INTO Books (book_name, author_id, publisher_name, isbn, total_pages, price)
VALUES
    ( 'To Kill a Mockingbird', 1, 'HarperCollins','9780061120084', 336, 14.99),
    ( '1984', 2,'Penguin Books', '9780451524935', 328, 12.99),
    ( 'The Great Gatsby', 3, 'Scribner', '9780743273565', 280, 9.99),
    ( 'The Pat Hobby Stories', 3, 'Scribner', '9780684804422', 260, 10.99),
    ( 'Pride and Prejudice', 4,'Penguin Classics', '9780141439518', 432, 11.99),
    ( 'Harry Potter and the Sorcerer''s Stone', 5, 'Scholastic', '9780590353427', 320, 17.99),
    ( 'The Catcher in the Rye', 6, 'Little, Brown and Company', '9780316769488', 224, 10.99),
    ( 'To All the Boys I''ve Loved Before', 7,'Simon & Schuster', '9781442426719', 384, 13.99),
    ( 'The Hobbit', 8,'Houghton Mifflin', '9780547928227', 320, 16.99),
    ( 'The Lord of the Rings', 8, 'Mariner Books', '9780544003415', 1178, 29.99);

SELECT * FROM Books;

INSERT INTO Books (book_name, author_id, publisher_name, isbn, total_pages, price)
VALUES
    ( 'The Alchemist', 9, 'HarperOne', '9780062315007', 208, 25.99),
    ('David Copperfield', 1, 'HarperCollins Publishers', '9780004244716', 831, 19,99);
----------------------------------------------------------------------------------------------------------

INSERT INTO Customers (customer_name, email, phone, address)
VALUES
    ( 'John Smith', 'john@yahoo.com', '555-123-4567', '123 Main St'),
    ( 'Emily Johnson', 'emily@gmail.com', '555-987-6543', '456 Maple Ave'),
    ( 'Michael Davis', 'michael@hotmail.com', '555-567-5098', '789 Oakwood Dr'),
    ( 'Emma Anderson', 'emma@yahoo.com', '555-153-2249', '567 Pine St'),
    ( 'Oliver Taylor', 'oliver@aol.com', '555-234-5678', '890 Elm Rd');

SELECT * FROM Customers;

UPDATE Customers Set phone = '555-234-5678'
WHERE customer_name = 'Oliver Taylor';

INSERT INTO Customers (customer_name, email, phone, address)
VALUES
    ('Ann Braun', 'braun@hotmail.com', '555-484-6868', '750 Spruce Rd'),
    ('Steve Jackson', 'jackson@gmail.com', '555-223-6576', '81 Monroe St');

DELETE FROM Customers
WHERE customer_name = 'Oliver Taylor';
----------------------------------------------------------------------------------------------------------

INSERT INTO Orders (customer_id, order_date, total_amount)
VALUES
    ( 1, '2023-01-06', 37.97),
    ( 5, '2023-02-18', 49.96),
    ( 3, '2023-03-20', 28.98),
    ( 4, '2023-04-09', 37.97),
    ( 2, '2023-05-23', 89.97);

SELECT * FROM Orders;

INSERT INTO Orders (customer_id, order_date, total_amount)
VALUES
    ( 6, '2023-06-15', 10.99);
----------------------------------------------------------------------------------------------------------

INSERT INTO Order_Items (order_id, book_id, quantity, item_price)
VALUES
    ( 1,  3,  1,  9.99),
    ( 1,  4,  1,  10.99),
    ( 1,  9,  1,  16.99),
    ( 2,  2,  2,  12.99),
    ( 2,  5,  2,  11.99),
    ( 3,  1,  1,  14.99),
    ( 3,  8,  1,  13.99),
    ( 4,  3,  2,  9.99),
    ( 4,  6,  1,  17.99),
    ( 5, 10,  3,  29.99);

SELECT * FROM Order_Items;

INSERT INTO Order_Items (order_id, book_id, quantity, item_price)
VALUES ( 6,  7,  1,  10.99);
----------------------------------------------------------------------------------------------------------

DELIMITER /
CREATE PROCEDURE getNameAuthorGetNameCustomer()
begin
select author_name, book_name, customer_name, orders.id,
       orders.order_date,Order_Items.id, Order_Items.item_price
from Order_items
         inner join Books on Books.id = Order_Items.book_id
         inner join Authors on Authors.id = Books.author_id
         inner join Orders on Orders.id = Order_Items.order_id
         inner join Customers on Customers.id = Orders.customer_id
where Authors.author_name = 'F. Scott Fitzgerald';
end /

CALL getNameAuthorGetNameCustomer(); /

drop procedure getNameAuthorGetNameCustomer; /
DELIMiTER /


DELIMITER /
CREATE PROCEDURE getCustomers()
begin
select Orders.id, order_date, customer_name, phone, total_amount
from Orders
         inner join Customers on Customers.id = Orders.customer_id;
end /

CALL getCustomers(); /

drop procedure getCustomers;/
DELIMITER /


DELIMITER /
CREATE PROCEDURE getOrderAndCustomer()
begin
select Order_items.id as order_item_id, order_date, Order_Items.order_id as order_id, customer_name, book_name, author_name, quantity, item_price
from Order_Items
         inner join Books on Books.id = Order_Items.book_id
         inner join Authors on Authors.id = Books.author_id
         inner join Orders on Orders.id = Order_Items.order_id
         inner join Customers on Customers.id = Orders.customer_id
where Customers.customer_name = 'Emma Anderson';
end /

CALL getOrderAndCustomer(); /

drop procedure getOrderAndCustomer; /
DELIMITER /
