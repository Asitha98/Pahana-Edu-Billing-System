package com.pahanabilling.service;

import com.pahanabilling.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> list();
    Optional<Customer> get(int customerId);
    int create(Customer customer);   // returns new customerId
    void update(Customer customer);
    void delete(int customerId);
}
