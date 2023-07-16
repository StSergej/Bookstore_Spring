package com.example.db_bookstore.controller;

import com.example.db_bookstore.entities.Author;
import com.example.db_bookstore.entities.OrderItem;
import com.example.db_bookstore.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/orderItems")
    public String showOrderItemList(Model model){ //нет перехода на страницу http://localhost:8080/orderItems ???

        List<OrderItem> listOrderItems = orderItemService.listAllOrderItem();
        model.addAttribute("listOrderItems", listOrderItems);

        return "orderItems";
    }



    @GetMapping("/orderItems/new")
    public String showNewOrderItems(Model model){ //есть переход на страницу  http://localhost:8080/orderItems/new

        model.addAttribute("orderItem", new OrderItem());

        return "newOrderItem";
    }


}
