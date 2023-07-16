package com.example.db_bookstore.service;

import com.example.db_bookstore.entities.Order;
import com.example.db_bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> listAllOrder(){

        return (List<Order>) orderRepository.findAll();
    }

    public void saveOrder(Order order){

        orderRepository.save(order);
    }

    public void deleteOrder(Long id){

        orderRepository.deleteById(id);
    }
}
