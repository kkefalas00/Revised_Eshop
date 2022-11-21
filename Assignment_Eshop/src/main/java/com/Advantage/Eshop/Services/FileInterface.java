package com.Advantage.Eshop.Services;

import java.io.FileNotFoundException;

public interface FileInterface {

    public void saveCustomers(String line) throws FileNotFoundException;
    public void saveOrders(String line) throws FileNotFoundException;
    public void saveProducts(String line) throws FileNotFoundException;

}
