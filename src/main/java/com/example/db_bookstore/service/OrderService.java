package com.example.db_bookstore.service;

import com.example.db_bookstore.entities.Order;
import com.example.db_bookstore.repository.OrderRepository;
import com.example.db_bookstore.service.entityException.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Order updateOrder(Long id) throws OrderException {

        Optional<Order> optionalGetOrder = orderRepository.findById(id);

        if(optionalGetOrder.isPresent()){
            return optionalGetOrder.get();
        }
        throw new OrderException("No any Order with ID: " + id);
    }

    public void deleteOrder(Long id){

        orderRepository.deleteById(id);
    }
}
