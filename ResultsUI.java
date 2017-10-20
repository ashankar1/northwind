/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northwinddb;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author ambik
 * This is the view part of MVC for output or results.  
 * ResultsUI contains two inner class, ResultsPane and ResultsTableView.  
 * ResultsPane contains three inner classes for each of the possible output or 
 * results pane (employees, customers, orders).
 * Same with ResultsTableView--contains three inner class for the three possible tables.  
 */
public class ResultsUI 
{    
    private ResultsPanel resultsPanel; 
    private ResultsTableView resultsTableView;
    
    ResultsUI()
    {
        resultsPanel = new ResultsPanel(); 
        resultsTableView = new ResultsTableView();
    }
          
    ResultsPanel getResultsPanel()
    {
        return resultsPanel; 
    }
         
    ResultsTableView getResultsTableView()
    {
        return resultsTableView; 
    }
    
    class ResultsPanel 
    {
        private ResultsPanel.Initial initial;
        private ResultsPanel.EmployeesByBirthYear employeesByBirthYear; 
        private ResultsPanel.CustomersByState customersByState; 
        private ResultsPanel.OrderDetails orderDetails; 
        
        ResultsPanel()
        {
            this.initial = new ResultsPanel.Initial(); 
            this.employeesByBirthYear = new ResultsPanel.EmployeesByBirthYear(); 
            this.customersByState = new ResultsPanel.CustomersByState(); 
            this.orderDetails = new ResultsPanel.OrderDetails(); 
        }
        
        ResultsPanel.Initial getInitial()
        {
            return this.initial; 
        }
        
        ResultsPanel.EmployeesByBirthYear getEmployeesByBirthYear()
        {
            return this.employeesByBirthYear; 
        }

        ResultsPanel.CustomersByState getCustomersByState()
        {
            return this.customersByState; 
        }

        ResultsPanel.OrderDetails getOrderDetails()
        {
            return this.orderDetails;
        }
        
        class Initial 
        {
            //blank results box for the start when the user has not chosen a menu yet
            private VBox panelVBox = new VBox();
            private Label panelLabel = new Label("Results Panel");
            
            Initial()
            {
                panelVBox.setPrefHeight(50);
                panelVBox.getChildren().addAll(panelLabel);
                panelVBox.setPadding(new Insets(5,5,5,5));
            }
            
            Pane getMenuPanel()
            {
                return panelVBox; 
            }
        }
        
        /*
            Employees by Birth Year - Results Box for Top Pane
            Contains labels to display the birth year the customer has entered  
            The birth year field is bolded. 
        */
        class EmployeesByBirthYear
        {
            private VBox panelVBox;
            private Label birthYearLabel;
            private Label birthYearResultsLabel;
            private HBox birthYearHBox;

            EmployeesByBirthYear()
            {
                panelVBox = new VBox(); 
                panelVBox.setSpacing(5); 
                panelVBox.setPadding(new Insets(5,5,5,5));
                birthYearLabel = new Label("Employees' Birth Year:");
                birthYearLabel.setStyle("-fx-font-weight:bold");
                birthYearResultsLabel = new Label("");
                birthYearHBox = new HBox();
                birthYearHBox.setSpacing(5);
                birthYearHBox.getChildren().addAll(birthYearLabel, birthYearResultsLabel);
                panelVBox.getChildren().addAll(birthYearHBox);
            }
            
            Pane getMenuPanel()
            {
                return panelVBox; 
            }
            
            void setBirthYearText(String text)
            {
                 birthYearResultsLabel.setText(text);
            }
            
        };
        
                
        /*
            Customers by State - Results Box for Top Pane
            Contains labels to display the state the user has entered.  
            The state field is bolded. 
        */        
        class CustomersByState
        {
            private VBox panelVBox;
            private Label stateLabel;
            private Label stateResultsLabel;
            private HBox stateHBox;

            CustomersByState()
            {                
                panelVBox = new VBox(); 
                panelVBox.setSpacing(5); 
                panelVBox.setPadding(new Insets(5,5,5,5));
                stateLabel = new Label("Customers' State:");
                stateLabel.setStyle("-fx-font-weight:bold");
                stateResultsLabel = new Label("");
                stateHBox = new HBox();
                stateHBox.setSpacing(5);
                stateHBox.getChildren().addAll(stateLabel, stateResultsLabel);
                panelVBox.getChildren().addAll(stateHBox);
            }
            
            Pane getMenuPanel()
            {
                return panelVBox; 
            }
                        
            void setState(String text)
            {
                stateResultsLabel.setText(text);
            }
        };
        
                
        /*
            Order Details - Results Box for Top Pane
            Contains labels to display the order number the user has enetered and 
            the date, cost, and freight charge for the given order number
            The above fields are bolded. 
        */
        class OrderDetails
        {
            private VBox panelVBox;
            private Label orderIDLabel;
            private Label orderIDResultsLabel;
            private Label statusLabel;
            private HBox orderIDAndStatusHBox;
            private Label dateLabel;
            private Label dateResultsLabel;
            private HBox dateHBox;
            private Label costLabel;
            private Label costResultsLabel;
            private HBox costHBox;
            private Label freightChargeLabel;
            private Label freightChargeResultsLabel;
            private HBox freightChargeHBox;

            OrderDetails()
            {
                panelVBox = new VBox(); 
                panelVBox.setSpacing(5); 
                panelVBox.setPadding(new Insets(5,5,5,5));
                orderIDLabel = new Label("Order Number:");
                orderIDLabel.setStyle("-fx-font-weight:bold");
                orderIDResultsLabel = new Label("");
                statusLabel = new Label("");
                statusLabel.setStyle("-fx-font-weight:bold");
                orderIDAndStatusHBox = new HBox();
                orderIDAndStatusHBox.setSpacing(5);
                orderIDAndStatusHBox.getChildren().addAll(orderIDLabel, 
                        orderIDResultsLabel, statusLabel);
                dateLabel = new Label("Order Date:");
                dateLabel.setStyle("-fx-font-weight:bold");
                dateResultsLabel = new Label("");
                dateHBox = new HBox();
                dateHBox.setSpacing(5);
                dateHBox.getChildren().addAll(dateLabel, dateResultsLabel);
                costLabel = new Label("Cost:");
                costLabel.setStyle("-fx-font-weight:bold");
                costResultsLabel = new Label("");
                costHBox = new HBox();
                costHBox.setSpacing(5);
                costHBox.getChildren().addAll(costLabel, costResultsLabel);
                freightChargeLabel = new Label("Freight Charge:");
                freightChargeLabel.setStyle("-fx-font-weight:bold");
                freightChargeResultsLabel = new Label("");
                freightChargeHBox = new HBox();
                freightChargeHBox.setSpacing(5);
                freightChargeHBox.getChildren().addAll(freightChargeLabel, 
                        freightChargeResultsLabel);
                panelVBox.getChildren().addAll(orderIDAndStatusHBox, 
                        dateHBox, costHBox, freightChargeHBox);
            }
            
            Pane getMenuPanel()
            {
                return panelVBox; 
            }
            
            void setOrderID(String text)
            {
                orderIDResultsLabel.setText(text);
            }
            
            void setDate(String text)
            {
                dateResultsLabel.setText(text);
            }
            
            void setCost(String text)
            {
                costResultsLabel.setText(text);
            }
            
            void setFreightCharge(String text)
            {
                freightChargeResultsLabel.setText(text);
            }
            
            
        };
    }
            
    class ResultsTableView
    {        
        private ResultsTableView.EmployeesByBirthYear employeesByBirthYear;
        private ResultsTableView.CustomersByState customersByState; 
        private ResultsTableView.OrderDetails orderDetails; 
        
        ResultsTableView()
        {
            this.employeesByBirthYear = new ResultsTableView.EmployeesByBirthYear();
            this.customersByState = new ResultsTableView.CustomersByState();
            this.orderDetails = new ResultsTableView.OrderDetails();
        }
  
        ResultsTableView.EmployeesByBirthYear getEmployeesByBirthYear()
        {
            return this.employeesByBirthYear;
        }
        
        ResultsTableView.CustomersByState getCustomersByState()
        {
            return this.customersByState;
        }
                
        ResultsTableView.OrderDetails getOrderDetails()
        {
            return this.orderDetails;
        }
        
        class EmployeesByBirthYear
        {
            private TableView<EmployeeModel> tableView;
            
            EmployeesByBirthYear()
            {
                tableView = new TableView(); 
            }
                       
            TableView getTableView() {
                return tableView; 
            }
        };
        
        class CustomersByState
        { 
            private TableView<CustomerModel> tableView;
            
            CustomersByState()
            {
                tableView = new TableView(); 
            }
                        
            TableView getTableView() {
                return tableView; 
            }
        };
        
        class OrderDetails
        {
            private TableView<ProductModel> tableView;
            
            OrderDetails()
            {
                tableView = new TableView(); 
            }          
            
            TableView getTableView() {
                return tableView; 
            }
        };        
    }
}
