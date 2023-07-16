package com.example.db_bookstore.controller;

import com.example.db_bookstore.entities.Author;
import com.example.db_bookstore.entities.Order;
import com.example.db_bookstore.entities.OrderItem;
import com.example.db_bookstore.service.OrderItemService;
import com.example.db_bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/orders")
    public String showOrderList(Model model){

        List<Order> listOrders = orderService.listAllOrder();
        model.addAttribute("listOrders", listOrders);

        return "orders";
    }


    @GetMapping("/orders/new")
    public String showNewOrder(Model model){

        model.addAttribute("order", new Order());

        return "newOrder";
    }


    @PostMapping("/orders/save")
    public String saveNewOrder(Order order){

        orderService.saveOrder(order);

        return "redirect:/orders";
    }


    @GetMapping("/orders/delete/{id}")
    public String deleteOrderById(@PathVariable("id") Long id){

        orderService.deleteOrder(id);

        return "redirect:/orders";
    }


}