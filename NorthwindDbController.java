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
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author ambik
 * Program to query the Northwind database.  
 * This is the main controller of the MVC for this program.  
 * This is the C of MVC.  
 */
public class NorthwindDbController extends Application {

    //database
    DbSql dbSql = new DbSql(); 
    
    //UI
    MenuBarUI menuBarUI = new MenuBarUI(); 
    SubMenuPanelUI subMenuPanelUI = new SubMenuPanelUI(); 
    ResultsUI resultsUI = new ResultsUI();     
  
    //Data Model
    //ProductsListModel - for orders
    ProductsListModel productsListModel = new ProductsListModel(); 
    EmployeesListModel employeesListModel = new EmployeesListModel(); 
    CustomersListModel customersListModel = new CustomersListModel(); 
            
    @Override
    public void start(Stage primaryStage) {

        //Top level UI.  
        //The display: rootBorderPane with menu bar in the top panel.  
        //Menu panel in the left pane.  
        //Another border pane, resultsBorderPane is set in the center of rootBorderPane.  
        //The top panel of resultsBorderPane contains one line results.  
        //The center panel of resultsBorderPane contains table views.  
        
        BorderPane resultsBorderPane = new BorderPane();
        
        resultsBorderPane.setTop(resultsUI.getResultsPanel().getInitial().getMenuPanel());
        BorderPane rootBorderPane = new BorderPane(); 
        rootBorderPane.setTop(menuBarUI.getMenuBar());
        rootBorderPane.setLeft(subMenuPanelUI.getInitial().getMenuPanel());
        rootBorderPane.setStyle("-fx-background-color:lightblue;-fx-padding:5px;");
        rootBorderPane.setCenter(resultsBorderPane);
                
        Scene scene = new Scene(rootBorderPane, 500, 450);
        //Scene scene = new Scene(root, 500, 450);
        primaryStage.setTitle("Northwind Database");
        primaryStage.setScene(scene);
        primaryStage.show();        
        
        //result tables controller
        resultTableController(); 
    
        /* Action handlers for menu items.  
        The appropriate menu is displayed in the left pane, 
        results box in a pane, and results table view in another pane.  
        */
        menuBarUI.getEmployees().getMIListByBirthYear().setOnAction(e->{
            rootBorderPane.setLeft(subMenuPanelUI.getEmployeesByBirthYear().getMenuPanel());
            resultsBorderPane.setCenter(resultsUI.getResultsTableView().getEmployeesByBirthYear().getTableView());
            resultsBorderPane.setTop(resultsUI.getResultsPanel().getEmployeesByBirthYear().getMenuPanel());                
        });         

        menuBarUI.getCustomers().getMIListByState().setOnAction(e->{
            rootBorderPane.setLeft(subMenuPanelUI.getCustomersByState().getMenuPanel());
            resultsBorderPane.setCenter(resultsUI.getResultsTableView().getCustomersByState().getTableView());
            resultsBorderPane.setTop(resultsUI.getResultsPanel().getCustomersByState().getMenuPanel());
        });        
 
        menuBarUI.getOrder().getMIDisplayDetails().setOnAction(e->{
            rootBorderPane.setLeft(subMenuPanelUI.getOrderDetails().getMenuPanel());
            resultsBorderPane.setCenter(resultsUI.getResultsTableView().getOrderDetails().getTableView());
            resultsBorderPane.setTop(resultsUI.getResultsPanel().getOrderDetails().getMenuPanel());
        });        
         
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
                
        //Order Menu's submit button
        subMenuPanelUI.getOrderDetails().getSubmitButton().setOnAction(e->{ 
            //get order id, clear text input box, and clear table view results.  
            String orderIDText = subMenuPanelUI.getOrderDetails().getTfOrderID().getText();   
            resultsUI.getResultsPanel().getOrderDetails().setOrderID(orderIDText);
            resultsUI.getResultsPanel().getOrderDetails().setDate("");
            resultsUI.getResultsPanel().getOrderDetails().setCost("");
            resultsUI.getResultsPanel().getOrderDetails().setFreightCharge("");

            subMenuPanelUI.getOrderDetails().getTfOrderID().clear();
            productsListModel.getDetailsData().clear();
            
            //orderDetailsStatusLabel.setText("");
            int orderNumber;
            try {
                //try to parse the string as an int
                orderNumber = Integer.valueOf(orderIDText);
            } catch (NumberFormatException ex) {                
                //orderDetailsStatusLabel.setText("Invalid order number");
                return; 
            }

            try {
                //get order details by executing sql statement
                dbSql.getPreparedStatements().getOrderDetails().setInt(1, orderNumber);
                ResultSet orderDetailsResultSet = dbSql.getPreparedStatements().getOrderDetails().executeQuery();
                
                Date orderDate;
                double freightCharge;
                int productID; 
                String productName;
                double unitPrice; 
                int quantity; 
                double discountPercent; 
                        
                //go through results set
                while (orderDetailsResultSet.next()) {
                    //get freight charge and date
                    dbSql.getPreparedStatements().getOrderDateAndFreightCharge().setInt(1, orderNumber);
                    ResultSet orderDateFreightChargeResultSet 
                            = dbSql.getPreparedStatements().getOrderDateAndFreightCharge().executeQuery();
                    orderDateFreightChargeResultSet.next(); 
                    orderDate = orderDateFreightChargeResultSet.getDate(1);
                    freightCharge = orderDateFreightChargeResultSet.getDouble(2); 
                    resultsUI.getResultsPanel().getOrderDetails().setDate(sdf.format(orderDate));
                    resultsUI.getResultsPanel().getOrderDetails().setFreightCharge(nf.format(freightCharge));

                    //calculate cost of order in sql
                    dbSql.getPreparedStatements().getOrderCost().setInt(1, orderNumber);
                    ResultSet costResultSet 
                            = dbSql.getPreparedStatements().getOrderCost().executeQuery(); 
                    if (costResultSet.next()) { 
                        double cost = costResultSet.getDouble(1);      
                        resultsUI.getResultsPanel().getOrderDetails().setCost(nf.format(cost));
                    } 
                    
                    //we get the product id; translate this into a product name
                    productID = orderDetailsResultSet.getInt(2); 
                    dbSql.getPreparedStatements().getProductName().setInt(1, productID);
                    ResultSet productNameResultSet 
                            = dbSql.getPreparedStatements().getProductName().executeQuery();
                    productNameResultSet.next(); 
                    productName = productNameResultSet.getString(1);
                    
                    unitPrice = orderDetailsResultSet.getDouble(3);
                    quantity = orderDetailsResultSet.getInt(4);
                    discountPercent = orderDetailsResultSet.getDouble(5);
                    
                    //add to table view
                    productsListModel.getDetailsData().addAll(
                         new ProductModel(productName, unitPrice,
                                   quantity, discountPercent)
                    );
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); 
            }

        });        
        
        /* 
        Employees - submit button action handler.  
        */
        subMenuPanelUI.getEmployeesByBirthYear().getSubmitButton().setOnAction(e->{ 
            //get birth year from user and clear the text box
            //clear the rest of the results panel
            String birthYearText = subMenuPanelUI.getEmployeesByBirthYear().getTfBirthYear().getText();   
            subMenuPanelUI.getEmployeesByBirthYear().getTfBirthYear().clear();
            employeesListModel.getNamesByBirthYearData().clear(); 
            //orderDetailsStatusLabel.setText("");
            int birthYear; 
            //check if the year is a valid number
            try {
                birthYear = Integer.valueOf(birthYearText);
                resultsUI.getResultsPanel().getEmployeesByBirthYear().setBirthYearText(birthYearText);
            } catch (NumberFormatException ex) {
                resultsUI.getResultsPanel().getEmployeesByBirthYear().setBirthYearText(birthYearText); 
                //orderDetailsStatusLabel.setText("Invalid order number");
                return; 
            }
            //call a prepared statement, entering the birth year to get the emploiyees' names.  
            try {
                dbSql.getPreparedStatements().getEmployeeNamesByBirthYear().setInt(1, birthYear);
                ResultSet employeeNamesResultSet = 
                        dbSql.getPreparedStatements().getEmployeeNamesByBirthYear().executeQuery();
                String firstName, lastName; 
                //go through the results set
                while (employeeNamesResultSet.next()) {
                    firstName = employeeNamesResultSet.getString(1);
                    lastName = employeeNamesResultSet.getString(2);
                    employeesListModel.getNamesByBirthYearData().addAll(
                         new EmployeeModel(firstName, lastName)
                    );
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); 
            }        
        
        });

        /* 
        Customers - menu panel - submit button - action handler.  
        */
        subMenuPanelUI.getCustomersByState().getSubmitButton().setOnAction(e->{ 
            //get the state provided by the user and clear the text box and the table view.  
            String state = subMenuPanelUI.getCustomersByState().getTfState().getText();
            resultsUI.getResultsPanel().getCustomersByState().setState(state.toUpperCase());
            subMenuPanelUI.getCustomersByState().getTfState().clear();
            customersListModel.getNamesAndCitiesByStateData().clear(); 
            //orderDetailsStatusLabel.setText("");
            
            /* 
            Get the customer names and cities by state using the sql prepared statement.  
            */
            try {
                dbSql.getPreparedStatements().getCustomerNamesAndCitiesByState().setString(1, state);
                ResultSet customerNamesCitiesResultSet = 
                        dbSql.getPreparedStatements().getCustomerNamesAndCitiesByState().executeQuery();
                String contactName, city; 
                //go through the results set
                while (customerNamesCitiesResultSet.next()) {
                    contactName = customerNamesCitiesResultSet.getString(1);
                    city = customerNamesCitiesResultSet.getString(2);
                    customersListModel.getNamesAndCitiesByStateData().addAll(
                         new CustomerModel(contactName, city)
                    );
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); 
            }        
        
        });
    }
            
    /* resultTableController: controlloer for the results tables.  
        Creates the table columns, cell factories for each value int he column.  
        Sets the data model for the table views.      
    */
    void resultTableController()
    {
        //Order Details
        TableColumn productColumn = new TableColumn("Product");
        TableColumn unitPriceColumn = new TableColumn("Unit Price");
        TableColumn quantityColumn = new TableColumn("Quantity");
        TableColumn discountColumn = new TableColumn("Discount");
        
        productColumn.setPrefWidth(250);
        unitPriceColumn.setStyle("-fx-alignment: CENTER-RIGHT");
        quantityColumn.setStyle("-fx-alignment: CENTER-RIGHT");
        discountColumn.setStyle("-fx-alignment: CENTER-RIGHT");
         
        productColumn.setCellValueFactory(
            new PropertyValueFactory<ProductModel, String>("product")
        );
        
        unitPriceColumn.setCellValueFactory(
            new PropertyValueFactory<ProductModel, String>("unitPrice")
        );
        
        quantityColumn.setCellValueFactory(
            new PropertyValueFactory<ProductModel, Integer>("quantity")
        );
        
        discountColumn.setCellValueFactory(
            new PropertyValueFactory<ProductModel, String>("discountPercent")
        );

        resultsUI.getResultsTableView().getOrderDetails().getTableView().getColumns().addAll(
                                productColumn, unitPriceColumn, 
                                quantityColumn, discountColumn);
        
        //set table data
        resultsUI.getResultsTableView().getOrderDetails().getTableView().setItems(
                        productsListModel.getDetailsData());

        //Employees 
        TableColumn employeeFirstNameColumn = new TableColumn("First Name");
        TableColumn employeeLastNameColumn = new TableColumn("Last Name");

        employeeFirstNameColumn.setPrefWidth(100);
        employeeLastNameColumn.setPrefWidth(100);

        employeeFirstNameColumn.setCellValueFactory(
            new PropertyValueFactory<EmployeeModel, String>("firstName")
        );
        
        employeeLastNameColumn.setCellValueFactory(
            new PropertyValueFactory<EmployeeModel, String>("lastName")
        );
        
        resultsUI.getResultsTableView().getEmployeesByBirthYear().getTableView().getColumns().addAll(
                employeeFirstNameColumn, employeeLastNameColumn); 
         
        //set table data
        resultsUI.getResultsTableView().getEmployeesByBirthYear().getTableView().setItems(
                employeesListModel.getNamesByBirthYearData());

        //Customers
        TableColumn customerContactNameColumn = new TableColumn("Contact Name");
        TableColumn customerCityColumn = new TableColumn("City");

        customerContactNameColumn.setPrefWidth(100);
        customerCityColumn.setPrefWidth(100);
        
        customerContactNameColumn.setCellValueFactory(
            new PropertyValueFactory<EmployeeModel, String>("contactName")
        );
        
        customerCityColumn.setCellValueFactory(
            new PropertyValueFactory<EmployeeModel, String>("city")
        );
        
        resultsUI.getResultsTableView().getCustomersByState().getTableView().getColumns().addAll(
                customerContactNameColumn, customerCityColumn); 

        //set table data
        resultsUI.getResultsTableView().getCustomersByState().getTableView().setItems(
                customersListModel.getNamesAndCitiesByStateData());

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
