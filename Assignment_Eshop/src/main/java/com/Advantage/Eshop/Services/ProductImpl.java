package com.Advantage.Eshop.Services;

import com.Advantage.Eshop.Domain.Product;
import com.Advantage.Eshop.Exceptions.CustomerExceptions;
import com.Advantage.Eshop.Exceptions.ProductExceptions;
import com.Advantage.Eshop.Repository.ProductRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductImpl implements ProductInterface{

    private ProductRepository productRepository;

    public ProductImpl(Connection connection){

        productRepository = new ProductRepository(connection);

    }


    @Override
    public void createProduct(Product product) throws SQLException {

        productRepository.create(product);

    }

    @Override
    public void deleteProduct(int id) throws SQLException {

        productRepository.delete(id);

    }

    @Override
    public Product findProduct(int id) throws SQLException, ProductExceptions {

        Product p = productRepository.find(id);
        return p;
    }

    @Override
    public List findAllProducts() throws SQLException, ProductExceptions {
        List products=productRepository.findAll();
        return products;
    }

    public String findAllProductsToString() throws ProductExceptions, SQLException {
        String line=productRepository.findAllProductsToStringLine();
        return line;
    }

    @Override
    public void updateProduct(int id, String name) throws SQLException {
        productRepository.update(id,name);
    }

    public String productReport(int id) throws ProductExceptions, SQLException, CustomerExceptions {

        String lineOfProduct=productRepository.findProductToStringLine(id);
        return lineOfProduct;
    }
}
