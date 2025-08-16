package com.pahanabilling.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;

    private int billId;
    private int customerId;
    private int units;
    private BigDecimal amount;        // total = units * rate (or tariff logic)
    private LocalDateTime createdAt;  // when the bill was generated

    public Bill() {}

    public Bill(int billId, int customerId, int units, BigDecimal amount, LocalDateTime createdAt) {
        this.billId = billId;
        this.customerId = customerId;
        this.units = units;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getUnits() { return units; }
    public void setUnits(int units) { this.units = units; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", customerId=" + customerId +
                ", units=" + units +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                '}';
    }
}
