package com.Advantage.Eshop.Services;

import com.Advantage.Eshop.Exceptions.OrderExceptions;
import com.Advantage.Eshop.Domain.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderInterface {

    public void createOrder(Order order) throws SQLException;
    public void deleteOrder(int id) throws SQLException;
    public Order findOrder(int id) throws SQLException,OrderExceptions;
    public List findAllOrders() throws SQLException,OrderExceptions;
    public void updateOrder(int id,String name) throws SQLException;
}
