package com.pahanabilling.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    private int itemId;
    private String sku;           // unique item code
    private String name;
    private BigDecimal unitPrice; // price per unit

    public Item() {}

    public Item(int itemId, String sku, String name, BigDecimal unitPrice) {
        this.itemId = itemId;
        this.sku = sku;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
