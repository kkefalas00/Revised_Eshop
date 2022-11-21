package com.Advantage.Eshop.Services;

import com.Advantage.Eshop.Domain.Customer;
import com.Advantage.Eshop.Exceptions.CustomerExceptions;
import com.Advantage.Eshop.Exceptions.OrderExceptions;
import com.Advantage.Eshop.Exceptions.ProductExceptions;

import java.sql.SQLException;
import java.util.List;

public interface CustomerInterface {

    public void createCustomer(Customer customer) throws SQLException;
    public void deleteCustomer(int id) throws SQLException;
    public Customer findStudent(int id) throws SQLException, CustomerExceptions, OrderExceptions, ProductExceptions;
    public List findAllCustomers() throws SQLException, CustomerExceptions, OrderExceptions, ProductExceptions;
    public void updateCustomer(int id,String name) throws SQLException;

}
