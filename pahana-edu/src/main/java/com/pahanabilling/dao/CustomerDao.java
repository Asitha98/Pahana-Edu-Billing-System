package com.pahanabilling.dao;

import com.pahanabilling.model.Customer;
import java.util.*;

public interface CustomerDao {
    Optional<Customer> findById(int customerId);
    Optional<Customer> findByAccountNo(String accountNo);
    List<Customer> findAll();
    int create(Customer c);     // returns generated customerId
    void update(Customer c);
    void delete(int customerId);
}
