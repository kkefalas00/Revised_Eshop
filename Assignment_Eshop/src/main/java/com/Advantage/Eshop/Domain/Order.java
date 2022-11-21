package com.Advantage.Eshop.Domain;

public class Order {

    private int orderId;
    private String transferType;

    private int customer_id;


    public Order(){}

    public Order(int customer_id,String transferType){

        this.transferType=transferType;
        this.customer_id=customer_id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", transferType='" + transferType + '\'' +
                ", customer_id=" + customer_id +
                '}';
    }
}
