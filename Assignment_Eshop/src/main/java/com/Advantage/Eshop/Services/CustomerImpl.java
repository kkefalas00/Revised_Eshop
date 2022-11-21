package com.Advantage.Eshop.Services;

import com.Advantage.Eshop.Domain.Customer;
import com.Advantage.Eshop.Exceptions.CustomerExceptions;
import com.Advantage.Eshop.Exceptions.OrderExceptions;
import com.Advantage.Eshop.Exceptions.ProductExceptions;
import com.Advantage.Eshop.Repository.CustomerRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerImpl implements CustomerInterface{

    CustomerRepository customerRepository;

    public CustomerImpl(Connection connection){

        customerRepository = new CustomerRepository(connection);

    }

    @Override
    public void createCustomer(Customer customer) throws SQLException {
        customerRepository.create(customer);
    }

    @Override
    public void deleteCustomer(int id) throws SQLException {
        customerRepository.delete(id);
    }

    @Override
    public Customer findStudent(int id) throws SQLException, CustomerExceptions, OrderExceptions, ProductExceptions {

        Customer c=customerRepository.find(id);
        return c;
    }

    @Override
    public List findAllCustomers() throws SQLException, CustomerExceptions, OrderExceptions, ProductExceptions {

        List customers=customerRepository.findAll();

        return customers;
    }

    @Override
    public void updateCustomer(int id, String name) throws SQLException {

        customerRepository.update(id,name);

    }


    // List of orders od each customer

    public List listOfOrderOfEachCustomer(int id) throws OrderExceptions, SQLException {

        List ordersOfCustomer=customerRepository.ListofCustomerOrders(id);

        return ordersOfCustomer;

    }

    //All customers in a string line

    public String allCustomersInStringLine() throws CustomerExceptions, SQLException {

        String lineOfCustomers=customerRepository.findALLCustomers();

        return lineOfCustomers;
    }

    public String customerReport(int id) throws CustomerExceptions, SQLException {

        String lineOfCustomer=customerRepository.findCustomerReport(id);
        return lineOfCustomer;
    }

}
