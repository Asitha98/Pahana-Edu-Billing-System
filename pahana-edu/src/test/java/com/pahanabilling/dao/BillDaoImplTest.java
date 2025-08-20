package com.pahanabilling.dao;

import com.pahanabilling.dao.impl.BillDaoImpl;
import com.pahanabilling.dao.impl.CustomerDaoImpl;
import com.pahanabilling.model.Bill;
import com.pahanabilling.model.Customer;
import org.junit.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;
import static org.junit.Assume.assumeNotNull;

public class BillDaoImplTest {

    private final BillDao billDao = new BillDaoImpl();
    private final CustomerDao customerDao = new CustomerDaoImpl();

    @BeforeClass
    public static void initSchema() {
        try (Connection con = DBConnection.getConnection()) {
            assumeNotNull(con);
            try (Statement st = con.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS customers (" +
                        "customer_id INT AUTO_INCREMENT PRIMARY KEY," +
                        "account_no  VARCHAR(50)  NOT NULL," +
                        "name        VARCHAR(120) NOT NULL," +
                        "address     VARCHAR(255)," +
                        "phone       VARCHAR(30)," +
                        "units       INT NOT NULL DEFAULT 0," +
                        "UNIQUE KEY uq_customers_account_no (account_no))" +
                        " ENGINE=InnoDB");

                st.execute("CREATE TABLE IF NOT EXISTS bills (" +
                        "bill_id     INT AUTO_INCREMENT PRIMARY KEY," +
                        "customer_id INT NOT NULL," +
                        "units       INT NOT NULL," +
                        "amount      DECIMAL(12,2) NOT NULL," +
                        "created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                        "CONSTRAINT fk_bills_customer FOREIGN KEY (customer_id) " +
                        " REFERENCES customers(customer_id) ON DELETE CASCADE ON UPDATE RESTRICT)" +
                        " ENGINE=InnoDB");
            }
        } catch (SQLException e) {
            assumeNoException("DB not reachable", e);
        }
    }

    @Before
    public void clean() throws Exception {
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {
            st.execute("SET FOREIGN_KEY_CHECKS=0");
            st.execute("TRUNCATE TABLE bills");
            st.execute("TRUNCATE TABLE customers");
            st.execute("SET FOREIGN_KEY_CHECKS=1");
        }
    }

    private int seedCustomer(String accountNo, String name) {
        Customer c = new Customer();
        c.setAccountNo(accountNo);
        c.setName(name);
        c.setUnits(0);
        return customerDao.create(c);
    }

    @Test
    public void createFindListByCustomer() {
        int cid = seedCustomer("ACC-BILL-1", "Bill Customer");

        Bill b = new Bill();
        b.setCustomerId(cid);
        b.setUnits(12);
        b.setAmount(new BigDecimal("240.00"));
        int billId = billDao.create(b);
        assertTrue(billId > 0);

        Optional<Bill> found = billDao.findById(billId);
        assertTrue(found.isPresent());
        assertEquals(cid, found.get().getCustomerId());
        assertEquals(12, found.get().getUnits());
        assertEquals(new BigDecimal("240.00"), found.get().getAmount());
        assertNotNull(found.get().getCreatedAt());

        List<Bill> forCustomer = billDao.findByCustomerId(cid);
        assertEquals(1, forCustomer.size());
        assertEquals(billId, forCustomer.get(0).getBillId());
    }

    @Test
    public void findAllReturnsInserts() {
        int cid = seedCustomer("ACC-BILL-2", "Biller");
        for (int i = 1; i <= 3; i++) {
            Bill b = new Bill();
            b.setCustomerId(cid);
            b.setUnits(10 * i);
            b.setAmount(new BigDecimal(10 * i).multiply(new BigDecimal("5.00")));
            billDao.create(b);
        }
        List<Bill> all = billDao.findAll();
        assertEquals(3, all.size());
    }
}
