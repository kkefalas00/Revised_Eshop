package com.Advantage.Eshop.Services;

import com.Advantage.Eshop.Repository.DatabaseRepository;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseImpl implements DatabaseInterface{

    private DatabaseRepository databaseRepository;

    public DatabaseImpl(Connection connection) {

        databaseRepository = new DatabaseRepository(connection);
    }

    @Override
    public void createTableForCustomer() throws SQLException {

        databaseRepository.CreateTableCustomer();

    }

    @Override
    public void createTableForOrder() throws SQLException {
        databaseRepository.CreateTableOrder();
    }

    @Override
    public void createTableForProduct() throws SQLException {
        databaseRepository.CreateTableProduct();
    }
}
