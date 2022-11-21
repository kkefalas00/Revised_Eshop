package com.Advantage.Eshop.Services;

import com.Advantage.Eshop.Domain.Order;
import com.Advantage.Eshop.Exceptions.OrderExceptions;
import com.Advantage.Eshop.Repository.OrderRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderImpl implements OrderInterface{

    private OrderRepository orderRepository;

    public OrderImpl(Connection connection){

        orderRepository=new OrderRepository(connection);

    }


    @Override
    public void createOrder(Order order) throws SQLException {

        orderRepository.create(order);

    }

    @Override
    public void deleteOrder(int id) throws SQLException {

        orderRepository.delete(id);

    }

    @Override
    public Order findOrder(int id) throws SQLException, OrderExceptions {
        Order o =orderRepository.find(id);
        return o;
    }

    @Override
    public List findAllOrders() throws SQLException, OrderExceptions {
        List orders=orderRepository.findAll();

        return orders;
    }

    @Override
    public void updateOrder(int id, String name) throws SQLException {

        orderRepository.update(id, name);

    }

    //find the products of each order

    public List productsOfEachOrderByOrderId(int id) throws OrderExceptions, SQLException {

        List productsOfEachOrder=orderRepository.eachOrderProductsById(id);

        return productsOfEachOrder;
    }

    //Find all orders written in a string Line

    public String AllOrdersInStringLine() throws OrderExceptions, SQLException {

        String lineOfOrders=orderRepository.findALLOrders();
        return lineOfOrders;
    }

}
