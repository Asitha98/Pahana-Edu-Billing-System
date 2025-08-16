package com.pahanabilling.factory;

import com.pahanabilling.dao.BillDao;
import com.pahanabilling.dao.CustomerDao;
import com.pahanabilling.dao.ItemDao;
import com.pahanabilling.dao.UserDao;
import com.pahanabilling.dao.impl.BillDaoImpl;
import com.pahanabilling.dao.impl.CustomerDaoImpl;
import com.pahanabilling.dao.impl.ItemDaoImpl;
import com.pahanabilling.dao.impl.UserDaoImpl;

/**
 * Central factory for DAO singletons.
 * - Use DaoFactory.getXxxDao() from services/controllers.
 * - For tests, you can inject a mock via setXxxDao(...), then call reset() after.
 */
public final class DaoFactory {

    private static volatile BillDao billDao;
    private static volatile CustomerDao customerDao;
    private static volatile ItemDao itemDao;
    private static volatile UserDao userDao;

    private DaoFactory() {}

    public static BillDao getBillDao() {
        if (billDao == null) {
            synchronized (DaoFactory.class) {
                if (billDao == null) {
                    billDao = new BillDaoImpl();
                }
            }
        }
        return billDao;
    }

    public static CustomerDao getCustomerDao() {
        if (customerDao == null) {
            synchronized (DaoFactory.class) {
                if (customerDao == null) {
                    customerDao = new CustomerDaoImpl();
                }
            }
        }
        return customerDao;
    }

    public static ItemDao getItemDao() {
        if (itemDao == null) {
            synchronized (DaoFactory.class) {
                if (itemDao == null) {
                    itemDao = new ItemDaoImpl();
                }
            }
        }
        return itemDao;
    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            synchronized (DaoFactory.class) {
                if (userDao == null) {
                    userDao = new UserDaoImpl();
                }
            }
        }
        return userDao;
    }

    // ---- Optional: allow injection/mocking in tests ----
    public static void setBillDao(BillDao custom) { billDao = custom; }
    public static void setCustomerDao(CustomerDao custom) { customerDao = custom; }
    public static void setItemDao(ItemDao custom) { itemDao = custom; }
    public static void setUserDao(UserDao custom) { userDao = custom; }

    /** Reset all singletons (useful between tests). */
    public static void reset() {
        billDao = null;
        customerDao = null;
        itemDao = null;
        userDao = null;
    }
}
