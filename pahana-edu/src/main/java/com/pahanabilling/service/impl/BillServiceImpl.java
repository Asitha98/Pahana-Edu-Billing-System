package com.pahanabilling.service.impl;

import com.pahanabilling.dao.BillDao;
import com.pahanabilling.factory.DaoFactory;
import com.pahanabilling.model.Bill;
import com.pahanabilling.service.BillService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class BillServiceImpl implements BillService {

    private final BillDao billDao;

    public BillServiceImpl() {
        this(DaoFactory.getBillDao());
    }

    // For tests (dependency injection)
    BillServiceImpl(BillDao billDao) {
        this.billDao = billDao;
    }

    @Override
    public int generateBill(int customerId, int units, BigDecimal ratePerUnit) {
        if (ratePerUnit == null) throw new IllegalArgumentException("ratePerUnit cannot be null");
        if (units < 0) throw new IllegalArgumentException("units must be >= 0");

        BigDecimal amount = ratePerUnit.multiply(BigDecimal.valueOf(units));

        Bill bill = new Bill();
        bill.setCustomerId(customerId);
        bill.setUnits(units);
        bill.setAmount(amount);
        bill.setCreatedAt(LocalDateTime.now());

        return billDao.create(bill);
    }

    @Override
    public List<Bill> list() {
        return billDao.findAll();
    }

    @Override
    public List<Bill> listByCustomer(int customerId) {
        return billDao.findByCustomerId(customerId);
    }

    @Override
    public Optional<Bill> get(int billId) {
        return billDao.findById(billId);
    }
}
