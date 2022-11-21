package com.Advantage.Eshop.Domain;

public class Product {

    private int productId;
    private int orderId;
    private String productName;
    private String category;

    private float price;

    public Product(){}

    public Product(int orderId, String productName, String category, float price){

        this.orderId=orderId;
        this.productName=productName;
        this.category=category;
        this.price=price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", orderId=" + orderId +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }


}
