package com.Advantage.Eshop.Services;

import java.io.FileNotFoundException;
import com.Advantage.Eshop.Domain.Order;
import com.Advantage.Eshop.Domain.Customer;
import com.Advantage.Eshop.Domain.Product;
import com.Advantage.Eshop.Repository.CsvRepository;

public class FileImpl implements FileInterface{

    private String filename;

    public FileImpl(){}

    public FileImpl(String filename) {

        this.filename = filename;
    }


    @Override
    public void saveCustomers(String line) throws FileNotFoundException {
        CsvRepository.writeCustomersToCsvFile(filename,line);
    }

    @Override
    public void saveOrders(String line) throws FileNotFoundException {
        CsvRepository.writeOrdersToCsvFile(filename,line);
    }

    @Override
    public void saveProducts(String line) throws FileNotFoundException {
        CsvRepository.writeProductsToCsvFile(filename,line);
    }

    public void saveOneCustomer(String line) throws FileNotFoundException {
        CsvRepository.writeCustomerToCsvFile(filename,line);
    }


    public void saveOneProduct(String line) throws FileNotFoundException {
        CsvRepository.writeProductToCsvFile(filename,line);
    }
}
