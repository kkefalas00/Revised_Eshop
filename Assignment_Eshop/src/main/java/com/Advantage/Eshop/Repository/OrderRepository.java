package com.Advantage.Eshop.Repository;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Advantage.Eshop.Domain.Customer;
import com.Advantage.Eshop.Domain.Order;
import com.Advantage.Eshop.Domain.Product;
import com.Advantage.Eshop.Exceptions.CustomerExceptions;
import com.Advantage.Eshop.Exceptions.OrderExceptions;
import com.Advantage.Eshop.Exceptions.ProductExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OrderRepository implements CrudInterface<Order> {

    private Connection connection;

    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);

    public OrderRepository(Connection connection){

        this.connection=connection;
    }


    @Override
    public void create(Order order) throws SQLException {


        Statement statement = connection.createStatement();
        String sql = "INSERT INTO orders(customer_id,transfer_type) VALUES ('" + order.getCustomer_id()+ "','" + order.getTransferType() + "')";
        statement.executeUpdate(sql);
        logger.info("order is inserted");

    }

    @Override
    public void update(int id, String name) throws SQLException {

        Statement statement = connection.createStatement();
        String sql="UPDATE orders SET transfer_type ='"+name +"'  WHERE order_id="+id;
        statement.executeUpdate(sql);
        logger.info("order is updated");

    }

    @Override
    public void delete(int id) throws SQLException {

        Statement statement = connection.createStatement();
        String sql="delete from orders WHERE order_id="+id;
        statement.executeUpdate(sql);
        logger.info("order with id"+id+" is deleted");


    }

    @Override
    public Order find(int id) throws SQLException, OrderExceptions{
        Statement statement = connection.createStatement();
        String sql = "select * from orders where order_id=" + id;
        ResultSet rs = statement.executeQuery(sql);
        Order order = null;
        if (rs.next()) {

            int customer_id= Integer.parseInt(rs.getString("customer_id"));
            String transfer_type = rs.getString("transfer_type");

            order = new Order(customer_id,transfer_type);
        }
        rs.close();
        if (order == null) {
            throw new OrderExceptions("Cannot find order with id=" + id);
        }
        return order;
    }

    @Override
    public List<Order> findAll() throws SQLException, OrderExceptions{

        Statement statement = connection.createStatement();
        String sql = "select * from orders";
        ResultSet rs = statement.executeQuery(sql);
        List<Order> orders = new ArrayList<>();
        while (rs.next()) {

            int customer_id= Integer.parseInt(rs.getString("customer_id"));
            String transfer_type = rs.getString("transfer_type");

            Order o = new Order(customer_id,transfer_type);

            orders.add(o);
        }
        rs.close();
        if (orders == null) {
            throw new OrderExceptions("Cannot find any order");
        }
        return orders;


    }

    //Find how many products has each order//

    public List<Product> eachOrderProductsById(int id) throws SQLException, OrderExceptions {

        Statement statement = connection.createStatement();
        String sql = "select * from orders,product where product.order_id="+id;
        ResultSet rs = statement.executeQuery(sql);
        List<Product> products = new ArrayList<>();
        while (rs.next()) {

            int prodId= Integer.parseInt(rs.getString("customer_id"));
            int ordId= Integer.parseInt(rs.getString("order_id"));
            String productName = rs.getString("product_name");
            float price = Float.parseFloat(rs.getString("price"));
            String category = rs.getString("category");

            Product p = new Product(ordId,productName,category,price);

            products.add(p);
        }
        rs.close();
        if (products == null) {
            throw new OrderExceptions("Cannot find any products in order with id"+id);
        }
        return products;



    }

    //Return all orders using a string line

    public String findALLOrders() throws SQLException,OrderExceptions {


        Statement statement = connection.createStatement();
        String sql = "select * from orders";
        ResultSet rs = statement.executeQuery(sql);
        String lineOfOrders="";
        while (rs.next()) {

            int ordId= Integer.parseInt(rs.getString("order_id"));
            String transfer_type = rs.getString("transfer_type");

            lineOfOrders+=ordId+","+transfer_type+"\n";
        }
        rs.close();
        if (lineOfOrders == null) {
            throw new OrderExceptions("Cannot find any customer");
        }
        return lineOfOrders;

    }


}
