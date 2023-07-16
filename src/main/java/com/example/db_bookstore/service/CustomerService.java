package com.example.db_bookstore.service;

import com.example.db_bookstore.entities.Customer;
import com.example.db_bookstore.repository.CustomerRepository;
import com.example.db_bookstore.service.entityException.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> listAllCustomer(){

        return (List<Customer>) customerRepository.findAll();
    }

    public void saveCustomer(Customer customer){

        customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id) throws CustomerException{

        Optional<Customer> optionalGetCustomer = customerRepository.findById(id);

        if(optionalGetCustomer.isPresent()){
            return optionalGetCustomer.get();
        }
        throw new CustomerException("No any customer with ID: " + id);
    }

    public void deleteCustomer(Long id) {

        customerRepository.deleteById(id);
    }
}
