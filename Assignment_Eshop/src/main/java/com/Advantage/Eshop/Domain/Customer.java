package com.Advantage.Eshop.Domain;

import java.util.Objects;

public class Customer {
    private int customerId;
    private int phone;
    private String customerName;
    private String type;
    private String address;


    public Customer(){}

    public Customer(String customerName, int phone,String address, String type){

        this.customerName=customerName;
        this.phone=phone;
        this.address=address;
        this.type=type;

    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", phone=" + phone +
                ", customerName='" + customerName + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                '}';
    }




}
