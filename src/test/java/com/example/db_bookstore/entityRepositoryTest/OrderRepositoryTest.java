package com.example.db_bookstore.entityRepositoryTest;


import com.example.db_bookstore.entities.Customer;
import com.example.db_bookstore.entities.Order;
import com.example.db_bookstore.repository.CustomerRepository;
import com.example.db_bookstore.repository.OrderRepository;
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
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testAddNewOrder(){

        Long customerId = 6L;

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.get();

        Order order = new Order();

        order.setCustomer(customer);  // 6, '2023-06-15', 10.99
        order.setOrderDate("2023-06-15");
        order.setTotalAmount("22,99");

        Order savedOrder = orderRepository.save(order);

        Assertions.assertThat(savedOrder).isNotNull();
        Assertions.assertThat(savedOrder.getId()).isGreaterThan(0);

        System.out.println(savedOrder);

    }

    @Test
    public void testAllOrder(){

        Iterable<Order> orders = orderRepository.findAll();

        Assertions.assertThat(orders).hasSizeGreaterThan(0);
        for(Order allOrders : orders){
            System.out.println(allOrders);
        }
    }

    @Test
    public void testUpdateOrder(){

        Long orderId = 6L;
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        Order order = optionalOrder.get();

        //order.setOrderDate("2023-06-16");
        order.setTotalAmount("25,98");


        orderRepository.save(order);

        Order updateOrder = orderRepository.findById(orderId).get();

        //Assertions.assertThat(updateOrder.getOrderDate()).isEqualTo("2023-06-16");
        Assertions.assertThat(updateOrder.getTotalAmount()).isEqualTo("25,98");

        System.out.println(updateOrder);

    }

    @Test
    public void testGetOrder(){

        Long orderId = 3L;
        Optional<Order> optionalGetOrder = orderRepository.findById(orderId);

        Assertions.assertThat(optionalGetOrder).isPresent();

        System.out.println(optionalGetOrder.get());

    }

    @Test
    public void testDeleteOrder(){
        Long orderId = 6L;

        orderRepository.deleteById(orderId);

        Optional<Order>optionalDeleteOrder = orderRepository.findById(orderId);

        Assertions.assertThat(optionalDeleteOrder).isNotPresent();
    }

}
