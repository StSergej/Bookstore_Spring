package com.example.db_bookstore.service;

import com.example.db_bookstore.entities.OrderItem;
import com.example.db_bookstore.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;


    public List<OrderItem> listAllOrderItem(){

        return (List<OrderItem>) orderItemRepository.findAll();
    }

    public void saveOrderItem(OrderItem orderItem){

        orderItemRepository.save(orderItem);

    }

}
