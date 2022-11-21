package com.Advantage.Eshop.Services;

import com.Advantage.Eshop.Domain.Product;
import com.Advantage.Eshop.Exceptions.ProductExceptions;
import java.sql.SQLException;
import java.util.List;



public interface ProductInterface{

    public void createProduct(Product product) throws SQLException;
    public void deleteProduct(int id) throws SQLException;
    public Product findProduct(int id) throws SQLException, ProductExceptions;
    public List findAllProducts() throws SQLException,ProductExceptions;
    public void updateProduct(int id,String name) throws SQLException;

}
