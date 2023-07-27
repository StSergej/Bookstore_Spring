package com.example.db_bookstore.controller;

import com.example.db_bookstore.entities.OrderItem;
import com.example.db_bookstore.service.OrderItemService;
import com.example.db_bookstore.service.entityException.OrderItemException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/orderItems")
    public String showOrderItemList(Model model){

        List<OrderItem> listOrderItems = orderItemService.listAllOrderItem();
        model.addAttribute("listOrderItems", listOrderItems);

        return "orderItems";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/orderItems/new")
    public String showNewOrderItems(Model model){

        model.addAttribute("orderItem", new OrderItem());
        model.addAttribute("pageTitle", "Add New Order Item");

        return "newOrderItem";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/orderItems/save")
    public String saveNewOrderItem(OrderItem orderItem){

        orderItemService.saveOrderItem(orderItem);

        return "redirect:/orderItems";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("orderItems/edit/{id}")
    public String showEditOrderItem(@PathVariable("id") Long id, Model model){

        try {
            OrderItem orderItem = orderItemService.updateOrderItem(id);

            model.addAttribute("orderItem", orderItem);
            model.addAttribute("pageTitle", "Edit Order Item(ID: " + id + ")");

            return "newOrderItem";

        } catch (OrderItemException e) {
            e.printStackTrace();
        }
        return "redirect:/orderItems";

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/orderItems/delete/{id}")
    public String deleteOrderItemById(@PathVariable("id") Long id){

        orderItemService.deleteOrderItem(id);

        return "redirect:/orderItems";
    }

}
