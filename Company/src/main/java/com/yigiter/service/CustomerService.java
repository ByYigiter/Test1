package com.yigiter.service;


import com.yigiter.domain.Customer;
import com.yigiter.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
       if (customerRepository.existsByEmail(customer.getEmail())){
           throw new RuntimeException("Customer already exists");
       }
       return customerRepository.save(customer);

    }

    public Customer deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Customer not found")
        );
        if (customerRepository.existsById(id)){
          customerRepository.deleteById(id);
        }else {
            throw new RuntimeException("Customer not found");
        }
        return customer;
    }

    public Customer updateCustomer(Long id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Customer not found"));
        //?
        if (existingCustomer.getEmail().equals(customer.getEmail()) || customerRepository.findByEmail(customer.getEmail()) == null) {
            existingCustomer.setName(customer.getName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhone(customer.getPhone());
            return customerRepository.save(existingCustomer);
        } else {
            throw new RuntimeException("Email already exists");
        }

    }
    // called from product
    public  Customer getCustomer(Long id) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Customer not found"));
        return existingCustomer;
    }
}
