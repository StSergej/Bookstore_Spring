package com.example.db_bookstore.entityRepositoryTest;


import com.example.db_bookstore.entities.Book;
import com.example.db_bookstore.entities.Order;
import com.example.db_bookstore.entities.OrderItem;
import com.example.db_bookstore.repository.BookRepository;
import com.example.db_bookstore.repository.OrderItemRepository;
import com.example.db_bookstore.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class OrderItemRepositoryTest {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Test
    public void testAddNewOrderItem(){

        Long orderId = 6L;
        Long bookId = 7L;

        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order order = optionalOrder.get();

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Book book = optionalBook.get();

        OrderItem orderItem = new OrderItem();


        orderItem.setOrder(order);  // 6,  7,  1,  10.99 // 7,  9,  2,  33.98
        orderItem.setBook(book);
        orderItem.setQuantity(1L);
        orderItem.setItemPrice("10,99");

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        Assertions.assertThat(savedOrderItem).isNotNull();
        Assertions.assertThat(savedOrderItem.getId()).isGreaterThan(0);

        System.out.println(savedOrderItem);

    }

    @Test
    public void testAllOrderItem(){
        Iterable<OrderItem> orderItems = orderItemRepository.findAll();

        Assertions.assertThat(orderItems).hasSizeGreaterThan(0);

        for(OrderItem allOrderItems : orderItems){
            System.out.println(allOrderItems);
        }
    }

    @Test
    public void testUpdateOrderItem(){
        Long orderItemId = 11L;

        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(orderItemId);

        OrderItem orderItem = optionalOrderItem.get();
        orderItem.setItemPrice("12,99");
        orderItem.setQuantity(2L);

        orderItemRepository.save(orderItem);

        OrderItem updateOrderItem = orderItemRepository.findById(orderItemId).get();

        Assertions.assertThat(updateOrderItem.getItemPrice()).isEqualTo("12,99");
        Assertions.assertThat(updateOrderItem.getQuantity()).isEqualTo(2L);

        System.out.println(updateOrderItem);
    }

    @Test
    public void testGetOrderItem(){
        Long orderItemId = 5L;

        Optional<OrderItem> optionalGetOrderItem = orderItemRepository.findById(orderItemId);

        Assertions.assertThat(optionalGetOrderItem).isPresent();

        System.out.println(optionalGetOrderItem.get());
    }

    @Test
    public void testDeleteOrderItem(){
        Long orderItemId = 11L;

        orderItemRepository.deleteById(orderItemId);

        Optional<OrderItem>optionalDeleteOrderItem = orderItemRepository.findById(orderItemId);

        Assertions.assertThat(optionalDeleteOrderItem).isNotPresent();
    }
}
