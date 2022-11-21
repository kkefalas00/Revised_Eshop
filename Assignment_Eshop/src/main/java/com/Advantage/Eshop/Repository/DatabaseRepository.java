package com.Advantage.Eshop.Repository;

import com.Advantage.Eshop.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseRepository {

    private Connection connection;

    private static final Logger logger = LoggerFactory.getLogger(DatabaseRepository.class);
    public DatabaseRepository(Connection connection){

        this.connection=connection;
    }



    public void CreateTableCustomer() throws SQLException{

        Statement statement= connection.createStatement();
        try {
            String sql = "CREATE TABLE if not exists customer (\n" +
                    "    customer_id int  NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    phone int NOT NULL ,\n" +
                    "    customerName varchar(255) NOT NULL,\n"+
                    "    type varchar(255) NOT NULL,\n"+
                    "    address varchar(255) NOT NULL);";

            logger.info(String.valueOf(statement.executeUpdate(sql)));
        }catch(SQLException e){
            logger.error("Problem with SQL : {}",e.getMessage());
        }


    }



    public void CreateTableOrder() throws SQLException{

        Statement statement= connection.createStatement();
        try {
            String sql = "CREATE TABLE if not exists orders (\n" +
                    "    order_id int PRIMARY KEY AUTO_INCREMENT NOT NULL,\n" +
                    "    transfer_type varchar(255) NOT NULL,\n" +
                    "    customer_id int NOT NULL,\n" +
                    "   FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ");";

            logger.info(String.valueOf(statement.executeUpdate(sql)));
        }catch(SQLException e){
            logger.error("Problem with SQL : {}",e.getMessage());
        }


    }

    public void CreateTableProduct() throws SQLException{

        Statement statement= connection.createStatement();

        try {
            String sql = "CREATE TABLE if not exists product (\n" +
                    "    product_id int  NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    order_id int NOT NULL,\n" +
                    "    product_name varchar(255) NOT NULL,\n" +
                    "    price double NOT NULL,\n" +
                    "    category varchar(255) NOT NULL,\n" +
                    "    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ");";
            logger.info(String.valueOf(statement.executeUpdate(sql)));

        }catch(SQLException e){
            logger.error("Problem with SQL : {}",e.getMessage());
        }
    }

}
