package com.Advantage.Eshop.Services;


import java.sql.SQLException;

public interface DatabaseInterface {
    public void createTableForCustomer() throws SQLException;
    public void createTableForOrder() throws SQLException;
    public void createTableForProduct() throws SQLException;
}
