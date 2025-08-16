package com.pahanabilling.service;

import com.pahanabilling.model.Bill;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BillService {
    /** Create a bill and return the generated billId. */
    int generateBill(int customerId, int units, BigDecimal ratePerUnit);

    /** List all bills (newest first recommended in impl). */
    List<Bill> list();

    /** List bills for a specific customer. */
    List<Bill> listByCustomer(int customerId);

    /** Find a single bill by id (optional helper). */
    Optional<Bill> get(int billId);
}
