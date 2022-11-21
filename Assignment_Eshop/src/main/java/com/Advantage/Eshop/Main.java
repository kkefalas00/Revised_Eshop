package com.Advantage.Eshop;
import com.Advantage.Eshop.Exceptions.ProductExceptions;
import com.Advantage.Eshop.Domain.Customer;
import com.Advantage.Eshop.Domain.Product;
import com.Advantage.Eshop.Domain.Order;
import com.Advantage.Eshop.Exceptions.CustomerExceptions;
import com.Advantage.Eshop.Services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import static java.lang.Float.parseFloat;


public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static Connection connection = null;
    private static Properties dbProperties = null;

    public static void main(String[] args) {

        initiateDatabase();
        //Business();
    }

    public static void Business(){

//        try {
//
//////            CreateTableForCustomer();
//////            CreateTableForOrder();
//////            CreateTableForProduct();
//////            createThreeCustomers();
//////            createThreeOrders();
//////            createThreeProducts();
//////            createCsvForCustomer(2);
//////            createCsvForProducts(2);
////            createCsvForCustomer(3);
////            createCsvForProducts(3);
//
//        }catch (SQLException | CustomerExceptions | FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (ProductExceptions e) {
//            throw new RuntimeException(e);
//        }

    }


    private static void initiateDatabase() {
        try {
            readProperties();
            useMySqlDriver();
            connectToDatabase(dbProperties.getProperty("connection.schema"));
            logger.info("Connected");
        } catch (SQLException e) {
            logger.error("Problem with sql:{}",e.getMessage());
        } catch (IOException e) {
            logger.error("Problem with system file: {}",e.getMessage());
        } catch (Exception e) {
            logger.error("Problem with system:{} ",e.getMessage());
        }
    }

    private static void readProperties() throws IOException {
        InputStream inStream = Main.class.getClassLoader().getResourceAsStream("sql.properties");
        dbProperties = new Properties();
        dbProperties.load(inStream);
    }

    private static void useMySqlDriver() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
    }

    private static Connection connectToDatabase(String schema) throws SQLException {
        String dbUrl = dbProperties.getProperty("connection.url");
        String username = dbProperties.getProperty("connection.username");
        String password = dbProperties.getProperty("connection.password");
        connection = DriverManager.getConnection(dbUrl + "/" + schema, username, password);
        return connection;
    }

    private static void CreateTableForCustomer() throws SQLException {

        DatabaseImpl service = new DatabaseImpl(connection);
        service.createTableForCustomer();

    }

    private static void CreateTableForOrder() throws SQLException {
        DatabaseImpl  service = new DatabaseImpl(connection);
        service.createTableForOrder();
    }

    private static void CreateTableForProduct() throws SQLException {
        DatabaseImpl  service = new DatabaseImpl(connection);
        service.createTableForProduct();
    }


    private static void createProduct(Product product) throws SQLException {
        ProductImpl service = new ProductImpl(connection);
        service.createProduct(product);
    }

    private static void createCustomer(Customer customer) throws SQLException {
        CustomerImpl service = new CustomerImpl(connection);
        service.createCustomer(customer);
    }

    private static void createOrder(Order order) throws SQLException {
        OrderImpl service = new OrderImpl(connection);
        service.createOrder(order);
    }


    private static void saveCustomerToFile(String line) throws CustomerExceptions, SQLException, FileNotFoundException {
        FileImpl fs = new FileImpl("data\\report1.csv");
        fs.saveCustomers(line);
    }

    private static void saveProductToFile(String line) throws ProductExceptions,SQLException, FileNotFoundException {
        FileImpl fs = new FileImpl("data\\report2.csv");
        fs.saveOneProduct(line);
    }


    private static String getLineOfCustomer(int id) throws CustomerExceptions, SQLException {

        CustomerImpl service = new CustomerImpl(connection);
        String line=service.customerReport(id);
        return line;
    }

    private static String getLineOfProduct(int id) throws ProductExceptions,CustomerExceptions, SQLException {

        ProductImpl service = new ProductImpl(connection);
        String lineofProd= service.productReport(id);
        return lineofProd;
    }

    //Create 3 customers
    public static void createThreeCustomers() throws SQLException {

        Customer c1= new Customer(dbProperties.getProperty("create.cname1"),Integer.parseInt(dbProperties.getProperty("create.phone")),dbProperties.getProperty("create.addr"),dbProperties.getProperty("create.type"));
        Customer c2= new Customer(dbProperties.getProperty("create.cname2"),Integer.parseInt(dbProperties.getProperty("create.phone2")),dbProperties.getProperty("create.addr2"),dbProperties.getProperty("create.type2"));
        Customer c3= new Customer(dbProperties.getProperty("create.cname3"),Integer.parseInt(dbProperties.getProperty("create.phone3")),dbProperties.getProperty("create.addr3"),dbProperties.getProperty("create.type3"));

        c1.setCustomerId(1);
        c2.setCustomerId(2);
        c3.setCustomerId(3);

        createCustomer(c1);
        createCustomer(c2);
        createCustomer(c3);

    }

    //Create 3 orders

    public static void createThreeOrders() throws SQLException {

        Order o1 = new Order(Integer.parseInt(dbProperties.getProperty("create.order.cid")),dbProperties.getProperty("create.order.type"));
        Order o2 = new Order(Integer.parseInt(dbProperties.getProperty("create.order.cid2")),dbProperties.getProperty("create.order.type2"));
        Order o3 = new Order(Integer.parseInt(dbProperties.getProperty("create.order.cid3")),dbProperties.getProperty("create.order.type3"));

        o1.setOrderId(1);
        o2.setOrderId(2);
        o3.setOrderId(3);

        createOrder(o1);
        createOrder(o2);
        createOrder(o3);

    }
    //Create 3 products
    public static void createThreeProducts() throws SQLException{

        Product p1 = new Product(Integer.parseInt(dbProperties.getProperty("create.pr1.ordId")),dbProperties.getProperty("create.pr1.name"),dbProperties.getProperty("create.cat1"),parseFloat(dbProperties.getProperty("create.pr1")));
        Product p2 = new Product(Integer.parseInt(dbProperties.getProperty("create.pr2.ordId")),dbProperties.getProperty("create.pr2.name"),dbProperties.getProperty("create.cat2"),parseFloat(dbProperties.getProperty("create.pr2")));
        Product p3 = new Product(Integer.parseInt(dbProperties.getProperty("create.pr3.ordId")),dbProperties.getProperty("create.pr3.name"),dbProperties.getProperty("create.cat3"),parseFloat(dbProperties.getProperty("create.pr3")));

        p1.setProductId(1);
        p2.setProductId(2);
        p3.setProductId(3);

        createProduct(p1);
        createProduct(p2);
        createProduct(p3);

    }

    //Create report1 for customer
    public static void createCsvForCustomer(int id) throws CustomerExceptions, SQLException, FileNotFoundException {

        String line=getLineOfCustomer(id);
        saveCustomerToFile(line);

    }

    //Create report1 for product
    public static void createCsvForProducts(int id) throws ProductExceptions, CustomerExceptions, SQLException, FileNotFoundException {

        String lineOfPr=getLineOfProduct(id);
        saveProductToFile(lineOfPr);
    }

}