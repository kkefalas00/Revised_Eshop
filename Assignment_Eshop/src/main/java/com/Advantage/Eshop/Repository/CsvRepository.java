package com.Advantage.Eshop.Repository;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CsvRepository {

    public static void writeCustomersToCsvFile(String filename, String line) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(filename);
        pw.println(line);
        pw.close();
    }


    public static void writeOrdersToCsvFile(String filename, String line) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(filename);
        pw.println(line);
        pw.close();
    }

    public static void writeProductsToCsvFile(String filename, String line) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(filename);
        pw.println(line);
        pw.close();
    }

    public static void writeCustomerToCsvFile(String filename, String line) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(filename);
        pw.println(line);
        pw.close();
    }

    public static void writeProductToCsvFile(String filename, String line) throws FileNotFoundException{

        PrintWriter pw = new PrintWriter(filename);
        pw.println(line);
        pw.close();

    }



}
