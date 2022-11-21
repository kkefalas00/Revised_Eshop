package com.Advantage.Eshop.Repository;

import com.Advantage.Eshop.Exceptions.CustomerExceptions;
import com.Advantage.Eshop.Exceptions.OrderExceptions;
import com.Advantage.Eshop.Exceptions.ProductExceptions;

import java.sql.SQLException;
import java.util.List;

public interface CrudInterface<T> {
    public void create(T t) throws SQLException;
    public void update(int id, String name) throws SQLException;
    public void delete(int id) throws SQLException;
    public T find(int id) throws SQLException, CustomerExceptions, OrderExceptions, ProductExceptions;
    public List<T> findAll() throws SQLException, CustomerExceptions, OrderExceptions, ProductExceptions;

}
