package com.example.db_bookstore.entityRepositoryTest;

import com.example.db_bookstore.entities.Customer;
import com.example.db_bookstore.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    public void testAddNewCustomer(){

        Customer customer = new Customer();

        customer.setCustomerName("Steve Jackson");
        customer.setEmail("jackson@gmail.com");
        customer.setPhone("555-223-6576");
        customer.setAddress("81 Monroe St");

        Customer savedCustomer = customerRepository.save(customer);

        Assertions.assertThat(savedCustomer).isNotNull();
        Assertions.assertThat(savedCustomer.getId()).isGreaterThan(0);

        System.out.println(savedCustomer);
    }

    @Test
    public void testAllCustomer(){

        Iterable<Customer> customers = customerRepository.findAll();

        Assertions.assertThat(customers).hasSizeGreaterThan(0);
        for (Customer allCustomers : customers) {
            System.out.println(allCustomers);
        }
    }

    @Test
    public void testUpdateCustomer(){
        Long customerId = 5L;
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        Customer customer = optionalCustomer.get();

        //customer.setEmail("jackson1122@gmail.com");
        customer.setPhone("555-333-1111");
        //customer.setAddress("18 Monroe St");


        customerRepository.save(customer);

        Customer updatedCustomer = customerRepository.findById(customerId).get();

        //Assertions.assertThat(updatedCustomer.getEmail()).isEqualTo("jackson1122@gmail.com");
        Assertions.assertThat(updatedCustomer.getPhone()).isEqualTo("555-333-1111");
        //Assertions.assertThat(updatedCustomer.getAddress()).isEqualTo("18 Monroe St");

        System.out.println(updatedCustomer);
    }

    @Test
    public void testGetCustomer(){
        Long customerId = 4L;
        Optional<Customer> optionalGetCustomer = customerRepository.findById(customerId);

        Assertions.assertThat(optionalGetCustomer).isPresent();

        System.out.println(optionalGetCustomer.get());
    }

    @Test
    public void testDeleteCustomer(){
        Long customerId = 5L;

        customerRepository.deleteById(customerId);
        Optional<Customer> optionalDeleteCustomer = customerRepository.findById(customerId);

        Assertions.assertThat(optionalDeleteCustomer).isNotPresent();
    }

}
