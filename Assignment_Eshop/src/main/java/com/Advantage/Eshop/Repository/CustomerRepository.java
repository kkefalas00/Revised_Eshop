package com.Advantage.Eshop.Repository;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.Advantage.Eshop.Domain.Customer;
import com.Advantage.Eshop.Domain.Order;
import com.Advantage.Eshop.Exceptions.CustomerExceptions;
import com.Advantage.Eshop.Exceptions.OrderExceptions;
import com.Advantage.Eshop.Exceptions.ProductExceptions;
import com.Advantage.Eshop.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomerRepository implements CrudInterface<Customer> {

    private Connection connection;

    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);

    public CustomerRepository(Connection connection){

        this.connection=connection;
    }


    @Override
    public void create(Customer customer) throws SQLException {

        Statement statement = connection.createStatement();
        String sql = "INSERT INTO customer(phone,customerName,type,address) VALUES ('" + customer.getPhone() + "','" + customer.getCustomerName() + "','" + customer.getType() + "','" + customer.getAddress() + "')";
        statement.executeUpdate(sql);
        logger.info("customer is inserted");


    }

    @Override
    public void update(int id, String name) throws SQLException {

        Statement statement = connection.createStatement();
        String sql="UPDATE customer SET customerName ='"+name +"'  WHERE customer_id="+id;
        statement.executeUpdate(sql);
        logger.info("customer is updated");

    }

    @Override
    public void delete(int id) throws SQLException {

        Statement statement = connection.createStatement();
        String sql="delete from customer WHERE customer_id="+id;
        statement.executeUpdate(sql);
        logger.info("customer with id"+id+" is deleted");

    }

    @Override
    public Customer find(int id) throws SQLException, CustomerExceptions, OrderExceptions, ProductExceptions {
        Statement statement = connection.createStatement();
        String sql = "select * from customer where customer_id=" + id;
        ResultSet rs = statement.executeQuery(sql);
        Customer customer = null;
        if (rs.next()) {

            int phone= Integer.parseInt(rs.getString("phone"));
            String customerName = rs.getString("customerName");
            String type = rs.getString("type");
            String address = rs.getString("address");

            customer = new Customer(customerName,phone,type,address);
        }
        rs.close();
        if (customer == null) {
            throw new CustomerExceptions("Cannot find customer with id=" + id);
        }
        return customer;
    }

    @Override
    public List<Customer> findAll() throws SQLException, CustomerExceptions, OrderExceptions, ProductExceptions {
        Statement statement = connection.createStatement();
        String sql = "select * from customer";
        ResultSet rs = statement.executeQuery(sql);
        List<Customer> customers = new ArrayList<>();
        while (rs.next()) {

            int phone= Integer.parseInt(rs.getString("phone"));
            String customerName = rs.getString("customerName");
            String type = rs.getString("type");
            String address = rs.getString("address");

            Customer c = new Customer(customerName,phone,type,address);
            customers.add(c);
        }
        rs.close();
        if (customers == null) {
            throw new CustomerExceptions("Cannot find any customer");
        }
        return customers;
    }

    //Find the orders of each customer//

    public List<Order> ListofCustomerOrders(int id) throws SQLException, OrderExceptions {

        Statement statement = connection.createStatement();
        String sql = "select * from customer,orders where orders.customer_id=customer."+id;
        ResultSet rs = statement.executeQuery(sql);
        List<Order> orders = new ArrayList<>();
        while (rs.next()) {

            int custId=Integer.parseInt(rs.getString("customer_id"));
            int order_id= Integer.parseInt(rs.getString("order_id"));
            String transferType = rs.getString("transfer_type");

            Order o = new Order(order_id,transferType);
            orders.add(o);
        }
        rs.close();
        if (orders == null) {
            throw new OrderExceptions("Cannot find any order related to customer with id="+id);
        }
        return orders;

    }

    //find all the customers using a string line

    public String findALLCustomers() throws SQLException, CustomerExceptions {


        Statement statement = connection.createStatement();
        String sql = "select * from customer";
        ResultSet rs = statement.executeQuery(sql);
        String lineOfCustomers="";
        while (rs.next()) {

            int phone= Integer.parseInt(rs.getString("phone"));
            String customerName = rs.getString("customerName");
            String type = rs.getString("type");
            String address = rs.getString("address");

            lineOfCustomers+=customerName+","+phone+","+type+","+address+"\n";
        }
        rs.close();
        if (lineOfCustomers == null) {
            throw new CustomerExceptions("Cannot find any customer");
        }
        return lineOfCustomers;

    }

    public String findCustomerReport(int id) throws SQLException, CustomerExceptions {

        int i=1;
        Statement statement = connection.createStatement();
        String sql = "select product_name,customerName,type,orders.order_id,price from customer,orders,product where customer.customer_id=orders.customer_id and orders.order_id=product.order_id and customer.customer_id="+id;
        ResultSet rs = statement.executeQuery(sql);
        String lineOfCustomer="";

        while (rs.next()) {

            String cname= rs.getString("customerName");
            String pname = rs.getString("product_name");
            String type = rs.getString("type");
            int ordId = Integer.parseInt(rs.getString("order_id"));
            float price = Float.parseFloat(rs.getString("price"));

            if(type.equals("B2B")){

                price = (float) (Float.parseFloat(rs.getString("price")) - (Float.parseFloat(rs.getString("price"))*0.2));
            }

            if(type.equals("B2G")){
                price = (float) (Float.parseFloat(rs.getString("price")) - (Float.parseFloat(rs.getString("price"))*0.5));
            }
            int totalPurchase=i;

            lineOfCustomer+="CustomerName,ProductName,Type,OrderId,Price,TotalPurchase"+"\n";
            lineOfCustomer+=cname+","+pname+","+type+","+ordId+","+price+","+totalPurchase+"\n";
            i++;
        }
        rs.close();
        if (lineOfCustomer == null) {
            throw new CustomerExceptions("Cannot find any customer");
        }
        return lineOfCustomer;

    }



}
