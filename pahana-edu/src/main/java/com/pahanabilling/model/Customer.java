package com.pahanabilling.model;

import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private int customerId;   // matches your existing naming
    private String accountNo; // unique per customer
    private String name;
    private String address;
    private String phone;
    private int units;        // current/last recorded units (if applicable)

    public Customer() {}

    public Customer(int customerId, String accountNo, String name, String address, String phone, int units) {
        this.customerId = customerId;
        this.accountNo = accountNo;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.units = units;
    }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getAccountNo() { return accountNo; }
    public void setAccountNo(String accountNo) { this.accountNo = accountNo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public int getUnits() { return units; }
    public void setUnits(int units) { this.units = units; }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", accountNo='" + accountNo + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", units=" + units +
                '}';
    }
}
