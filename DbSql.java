/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northwinddb;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ambik
 * Functions to initialize the database.  
 * Contains the needed Sql statements.  
 */
public class DbSql {
    private Connection connection; 
    private Statement statement; 
    private PreparedStatements preparedStatements;
        
    //Add any needed prepared statements here.  
    class PreparedStatements {
        private PreparedStatement orderDetails;
        private PreparedStatement productName;
        private PreparedStatement orderDateAndFreightCharge;                
        private PreparedStatement orderCost;  
        private PreparedStatement employeeNamesByBirthYear;
        private PreparedStatement customerNamesAndCitiesByState;            
        
        /* PreparedStatements() - creates the prepared statements used by the program.  
        */
        PreparedStatements()
        {
            try {
                String getOrderDetailsQuery = 
                        "select * from `Order Details` where OrderID = ?";
                orderDetails = 
                        connection.prepareStatement(getOrderDetailsQuery);

                String getProductNameQuery 
                        = "select ProductName from Products "                        
                                            + "where ProductID = ?";
                productName = connection.prepareStatement(getProductNameQuery);

                String getOrderDateAndFreightChargeQuery 
                        = "select OrderDate, Freight from Orders "
                                            + "where OrderID = ?";
                orderDateAndFreightCharge 
                        = connection.prepareStatement(getOrderDateAndFreightChargeQuery);
                
                String getOrderCostQuery = 
                        "select SUM((UnitPrice*Quantity)*(1-Discount)) "
                        + "from `Order Details` where OrderID = ?";
                orderCost = connection.prepareStatement(getOrderCostQuery);

                String getEmployeeNamesByBirthYearQuery 
                        = "select FirstName, LastName from Employees "
                        + "where year(BirthDate) = ? order by LastName";
                employeeNamesByBirthYear 
                        = connection.prepareStatement(getEmployeeNamesByBirthYearQuery);
                
                String getCustomerNamesAndCitiesByStateQuery 
                        = "select ContactName, City from Customers "
                        + "where Region = ? order by City";
                customerNamesAndCitiesByState 
                        = connection.prepareStatement(getCustomerNamesAndCitiesByStateQuery);
            } catch (Exception ex) {
                ex.printStackTrace(); 
            }
        }

        //getters
        PreparedStatement getOrderDetails() {return orderDetails;}
        PreparedStatement getProductName() {return productName;}
        PreparedStatement getOrderDateAndFreightCharge() {return orderDateAndFreightCharge;}                
        PreparedStatement getOrderCost() {return orderCost;}  
        PreparedStatement getEmployeeNamesByBirthYear() {return employeeNamesByBirthYear;}
        PreparedStatement getCustomerNamesAndCitiesByState() {return customerNamesAndCitiesByState;}            
        
    };
    
    public DbSql()
    {
        initDb();
        preparedStatements = new PreparedStatements();
    }
    
    /*  initDb: initializes global variables for connection and statement.
    */
    private void initDb() {
        try {
            // Load the JDBC driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println("UCanAccess Driver Loaded");

            // Establish a connection
            connection = DriverManager.getConnection
              ("jdbc:ucanaccess://C:/Data/Northwind.mdb");
            System.out.println("Northwind Database Connected");
            
            // Create a statement
            statement = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        
    public PreparedStatements getPreparedStatements()
    {
        return preparedStatements; 
    }
    
    /* 
    discoverTableNames - helper function used to discover table names and column names in database.  
    */
    private void discoverTableNames()
    {
       try {
            DatabaseMetaData dbMetaData = connection.getMetaData();
            ResultSet rsTables = dbMetaData.getTables(null, null, null, new String[] {"TABLE"});
            while (rsTables.next()) {
                System.out.println(rsTables.getString("TABLE_NAME")); 
            }
            rsTables = statement.executeQuery("select * from `Order Details`");
            ResultSetMetaData rsMetaData = rsTables.getMetaData(); 
            for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                System.out.print(rsMetaData.getColumnName(i) + " ");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); 
        }        
    }
}
