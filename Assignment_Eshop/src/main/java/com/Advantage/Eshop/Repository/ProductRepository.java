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


public class ProductRepository implements CrudInterface<Product> {

    private Connection connection;

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    public ProductRepository(Connection connection){

        this.connection=connection;
    }




    @Override
    public void create(Product product) throws SQLException {

        Statement statement = connection.createStatement();
        String sql = "INSERT INTO product(order_id,product_name,price,category) VALUES ('" + product.getOrderId() + "','" + product.getProductName() + "','" + product.getPrice() + "','" + product.getCategory() + "')";
        statement.executeUpdate(sql);
        logger.info("product is inserted");

    }

    @Override
    public void update(int id, String name) throws SQLException {

        Statement statement = connection.createStatement();
        String sql="UPDATE product SET product_name ='"+name +"'  WHERE product_id="+id;
        statement.executeUpdate(sql);
        logger.info("customer is updated");


    }

    @Override
    public void delete(int id) throws SQLException {

        Statement statement = connection.createStatement();
        String sql="delete from product WHERE product_id="+id;
        statement.executeUpdate(sql);
        logger.info("product with id"+id+" is deleted");

    }

    @Override
    public Product find(int id) throws SQLException, ProductExceptions {

        Statement statement = connection.createStatement();
        String sql = "select * from product where product_id=" + id;
        ResultSet rs = statement.executeQuery(sql);
        Product product = null;
        if (rs.next()) {


            int ordId= Integer.parseInt(rs.getString("order_id"));
            String productName = rs.getString("product_name");
            float price = Float.parseFloat(rs.getString("price"));
            String category = rs.getString("category");

            product = new Product(ordId,productName,category,price);
        }
        rs.close();
        if (product == null) {
            throw new ProductExceptions("Cannot find product with id=" + id);
        }
        return product;

    }

    @Override
    public List<Product> findAll() throws SQLException,ProductExceptions {

        Statement statement = connection.createStatement();
        String sql = "select * from product";
        ResultSet rs = statement.executeQuery(sql);
        List<Product> products = new ArrayList<>();
        while (rs.next()) {


            int ordId= Integer.parseInt(rs.getString("order_id"));
            String productName = rs.getString("product_name");
            float price = Float.parseFloat(rs.getString("price"));
            String category = rs.getString("category");

            Product p = new Product(ordId,productName,category,price);
            products.add(p);
        }
        rs.close();
        if (products == null) {
            throw new ProductExceptions("Cannot find any product");
        }
        return products;


    }


    public String findAllProductsToStringLine() throws SQLException,ProductExceptions {

        Statement statement = connection.createStatement();
        String sql = "select * from product";
        ResultSet rs = statement.executeQuery(sql);
        String lineOfProducts="";
        while (rs.next()) {


            int ordId= Integer.parseInt(rs.getString("order_id"));
            String productName = rs.getString("product_name");
            float price = Float.parseFloat(rs.getString("price"));
            String category = rs.getString("category");

            lineOfProducts+=productName+","+price+","+category+","+ordId+"\n";

        }
        rs.close();
        if (lineOfProducts == null) {
            throw new ProductExceptions("Cannot find any product");
        }
        return lineOfProducts;


    }


    public String findProductToStringLine(int id) throws SQLException, CustomerExceptions {


        Statement statement = connection.createStatement();
        String sql = "select product.product_name,orders.order_id,product.price from orders,product where orders.order_id=product.order_id and product.product_id="+id;
        ResultSet rs = statement.executeQuery(sql);
        String lineOfProduct="";
        while (rs.next()) {


            String pname = rs.getString("product_name");
            int ordId = Integer.parseInt(rs.getString("order_id"));
            float price = Float.parseFloat(rs.getString("price"));

            lineOfProduct+="ProductName,OrderId,TotalCost"+"\n";
            lineOfProduct+=pname+","+ordId+","+price+","+"\n";

        }
        rs.close();
        if (lineOfProduct == null) {
            throw new CustomerExceptions("Cannot find any product");
        }
        return lineOfProduct;

    }




}
