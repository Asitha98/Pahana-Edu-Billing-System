package com.pahanabilling.service.impl;

import com.pahanabilling.dao.CustomerDao;
import com.pahanabilling.factory.DaoFactory;
import com.pahanabilling.model.Customer;
import com.pahanabilling.service.CustomerService;

import java.util.List;
import java.util.Optional;


public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    public CustomerServiceImpl() {
        this(DaoFactory.getCustomerDao());
    }

    // For tests (dependency injection)
    CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<Customer> list() {
        return customerDao.findAll();
    }

    @Override
    public Optional<Customer> get(int customerId) {
        return customerDao.findById(customerId);
    }

    @Override
    public int create(Customer customer) {
        return customerDao.create(customer);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public void delete(int customerId) {
        customerDao.delete(customerId);
    }
}
