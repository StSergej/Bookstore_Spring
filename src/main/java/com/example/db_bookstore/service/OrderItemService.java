package com.example.db_bookstore.service;

import com.example.db_bookstore.entities.OrderItem;
import com.example.db_bookstore.repository.OrderItemRepository;
import com.example.db_bookstore.service.entityException.OrderItemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public OrderItem updateOrderItem(Long id) throws OrderItemException {

        Optional<OrderItem> optionalGetOrderItem = orderItemRepository.findById(id);

        if(optionalGetOrderItem.isPresent()){
            return  optionalGetOrderItem.get();
        }
        throw new OrderItemException("No any orderItem with ID: " + id);
    }

    public void deleteOrderItem(Long id){

        orderItemRepository.deleteById(id);
    }

}
