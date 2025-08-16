package com.pahanabilling.dao;

import com.pahanabilling.model.Bill;
import java.util.*;

public interface BillDao {
    Optional<Bill> findById(int billId);
    List<Bill> findAll();
    List<Bill> findByCustomerId(int customerId);
    int create(Bill bill); // returns generated billId
}
