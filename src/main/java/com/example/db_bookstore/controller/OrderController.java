package com.example.db_bookstore.controller;

import com.example.db_bookstore.entities.Order;
import com.example.db_bookstore.service.OrderService;
import com.example.db_bookstore.service.entityException.OrderException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
        model.addAttribute("pageTitle", "Add New Order");

        return "newOrder";
    }


    @PostMapping("/orders/save")
    public String saveNewOrder(Order order){

        orderService.saveOrder(order);

        return "redirect:/orders";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/orders/edit/{id}")
    public String showEditOrder(@PathVariable("id") Long id, Model model){

        try {
            Order order = orderService.updateOrder(id);

            model.addAttribute("order", order);
            model.addAttribute("pageTitle", "Edit Order(ID: " + id + ")");

            return "newOrder";

        } catch (OrderException e) {
            e.printStackTrace();
        }
        return "redirect:/orders";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/orders/delete/{id}")
    public String deleteOrderById(@PathVariable("id") Long id){

        orderService.deleteOrder(id);

        return "redirect:/orders";
    }

}
