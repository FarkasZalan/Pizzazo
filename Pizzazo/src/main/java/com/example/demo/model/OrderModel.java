package com.example.demo.model;

public class OrderModel {
    // this class is help to display the orders list for a user

    private String orderIdentify;
    private String orderDate;
    // this variable will contain all the items name
    private String orderItemsOnDisplay;
    private String displayQuantity;
    private String unitPrice;
    private String orderTotalAmount;

    public OrderModel(String orderIdentify, String orderDate, String orderItemsOnDisplay, String displayQuantity, String unitPrice, String orderTotalAmount) {
        this.orderIdentify = orderIdentify;
        this.orderDate = orderDate;
        this.orderItemsOnDisplay = orderItemsOnDisplay;
        this.displayQuantity = displayQuantity;
        this.unitPrice = unitPrice;
        this.orderTotalAmount = orderTotalAmount;
    }

    public String getOrderIdentify() {
        return orderIdentify;
    }

    public void setOrderIdentify(String orderIdentify) {
        this.orderIdentify = orderIdentify;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderItemsOnDisplay() {
        return orderItemsOnDisplay;
    }

    public void setOrderItemsOnDisplay(String orderItemsOnDisplay) {
        this.orderItemsOnDisplay = orderItemsOnDisplay;
    }

    public String getDisplayQuantity() {
        return displayQuantity;
    }

    public void setDisplayQuantity(String displayQuantity) {
        this.displayQuantity = displayQuantity;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(String orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }
}
