package com.example.db_bookstore.controller;

import com.example.db_bookstore.entities.Customer;
import com.example.db_bookstore.service.CustomerService;
import com.example.db_bookstore.service.entityException.CustomerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public String showCustomerList(Model model){

        List<Customer> listCustomers = customerService.listAllCustomer();
        model.addAttribute("listCustomers", listCustomers);

        return "customers";
    }

    @GetMapping("/customers/new")
    public String showNewCustomer(Model model){

        model.addAttribute("customer", new Customer());
        model.addAttribute("pageTitle", "Add New Customer");

        return "newCustomer";
    }

    @PostMapping("/customers/save")
    public String saveNewCustomer(Customer customer){

        customerService.saveCustomer(customer);

        return "redirect:/customers";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/customers/edit/{id}")
    public String showEditCustomer(@PathVariable("id") Long id, Model model){

        try {

            Customer customer = customerService.updateCustomer(id);

            model.addAttribute("customer", customer);
            model.addAttribute("pageTitle", "Edit Customer(ID: " + id + ")");

            return "newCustomer";

        } catch (CustomerException e) {
            e.printStackTrace();
            return "redirect:/customers";
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/customers/delete/{id}")
    public String deleteCustomerById(@PathVariable("id") Long id){

            customerService.deleteCustomer(id);

            return "redirect:/customers";
    }

}
