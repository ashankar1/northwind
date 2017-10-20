/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northwinddb;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author ambik
 * This is the view part of the MVC for menus.  
 * Implements sub menus in a pane.  
 * Contains an inner class for each possible sub menu (employees, customers, orders). 
 */
public class SubMenuPanelUI {
    
    private SubMenuPanelUI.Initial initial; 
    private SubMenuPanelUI.EmployeesByBirthYear employeesByBirthYear;
    private SubMenuPanelUI.CustomersByState customersByState; 
    private SubMenuPanelUI.OrderDetails orderDetails; 
    
    SubMenuPanelUI()
    {
        this.initial = new Initial(); 
        this.employeesByBirthYear = new SubMenuPanelUI.EmployeesByBirthYear();
        this.customersByState = new SubMenuPanelUI.CustomersByState();
        this.orderDetails = new SubMenuPanelUI.OrderDetails(); 
    }    

    Initial getInitial()
    {
        return this.initial; 
    }
    
    EmployeesByBirthYear getEmployeesByBirthYear()
    {
        return this.employeesByBirthYear;
    }
    
    CustomersByState getCustomersByState()
    {
        return this.customersByState;
    }           

    OrderDetails getOrderDetails()
    {
        return this.orderDetails;
    }    
    
    //initial sub menu panel when the user has not yet chosen a menu option
    class Initial
    {
        private VBox menuVBox; 
        private Label menuLabel;
        
        Initial()
        {
            menuVBox = new VBox(); 
            menuVBox.setStyle("-fx-background-color:darksalmon;-fx-padding:5px;");
            menuVBox.setPrefWidth(150);
            menuLabel = new Label("Sub Menu Panel");
            menuVBox.getChildren().addAll(menuLabel);
        }
        
        Pane getMenuPanel()
        {
            return menuVBox; 
        }
    };

    /* 
        Employees by Birth Year Menu Box
        If the Employees Menu's Display Details option is chosen, 
        the following appears on the side menu panel.  
        There is a text field to allow the user to submit a birth year
        along with a submit button.  
    */                  
    class EmployeesByBirthYear
    {
        private VBox menuVBox;
        private Label menuLabel;
        private Label menuLabel2;
        private Label enterBirthYearLabel;
        private TextField tfBirthYear;
        private HBox submitButtonHBox;
        private Button submitButton;

        EmployeesByBirthYear() 
        {
            menuVBox = new VBox();
            menuVBox.setSpacing(5); 
            menuLabel = new Label("Employees Menu:");
            menuLabel2 = new Label("List by Birth Year");
            enterBirthYearLabel = new Label("Please enter the birth year:");
            tfBirthYear = new TextField(); 
            tfBirthYear.setAlignment(Pos.BOTTOM_RIGHT);
            submitButtonHBox = new HBox(); 
            submitButton = new Button("Submit");
            submitButtonHBox.getChildren().addAll(submitButton);
            submitButtonHBox.setAlignment(Pos.CENTER_RIGHT);
            menuVBox.getChildren().addAll(menuLabel, menuLabel2, 
                    enterBirthYearLabel, tfBirthYear, submitButtonHBox);
            menuVBox.setStyle("-fx-background-color:lightsteelblue;-fx-padding:5px;");
        }

        Pane getMenuPanel()
        {
            return menuVBox; 
        }        
        
        Button getSubmitButton()
        {
            return submitButton; 
        }
        
        TextField getTfBirthYear()
        {
            return tfBirthYear; 
        }

    };
    
    /* 
        Customers by State Menu Box
        If the Customers Menu's Display Details option is chosen, 
        the following appears on the side menu panel.  
        There is a text field to allow the user to submit a state
        along with a submit button.  
    */  
    class CustomersByState
    {
        private VBox menuVBox;
        private Label menuLabel;
        private Label menuLabel2;
        private Label enterStateLabel;
        private TextField tfState;
        private HBox submitButtonHBox;
        private Button submitButton;  
        
        CustomersByState()
        {
            menuVBox = new VBox();
            menuVBox.setSpacing(5); 
            menuLabel = new Label("Customers Menu:");
            menuLabel2 = new Label("List by State");
            enterStateLabel = new Label("Please enter the state:");
            tfState = new TextField(); 
            tfState.setAlignment(Pos.BOTTOM_RIGHT);
            submitButtonHBox = new HBox(); 
            submitButton = new Button("Submit");
            submitButtonHBox.getChildren().addAll(submitButton);
            submitButtonHBox.setAlignment(Pos.CENTER_RIGHT);
            menuVBox.getChildren().addAll(menuLabel, menuLabel2, 
                    enterStateLabel, tfState, submitButtonHBox);
            menuVBox.setStyle("-fx-background-color:lemonchiffon;-fx-padding:5px;");            
        }     
        
        Pane getMenuPanel()
        {
            return menuVBox; 
        }       
        
        Button getSubmitButton()
        {
            return submitButton; 
        }
        
        TextField getTfState()
        {
            return tfState; 
        }
    };

    /* 
        Order Details Menu Box
        If the Order Menu's Display Details option is chosen, 
        the following appears on the side menu panel.  
        There is a text field to allow the user to submit an order number
        along with a submit button.  
    */       
    class OrderDetails
    {
        private VBox menuVBox;
        private Label menuLabel; 
        private Label menuLabel2;
        private Label enterOrderIDLabel;
        private TextField tfOrderID;
        private HBox submitButtonHBox;
        private Button submitButton;        

        OrderDetails()
        {
            menuVBox = new VBox();
            menuVBox.setSpacing(5); 
            menuLabel = new Label("Order SubMenu:");
            menuLabel2 = new Label("Display Details");
            enterOrderIDLabel = new Label("Please enter the order number:");
            tfOrderID = new TextField(); 
            tfOrderID.setAlignment(Pos.BOTTOM_RIGHT);
            submitButtonHBox = new HBox(); 
            submitButton = new Button("Submit");
            submitButtonHBox.getChildren().addAll(submitButton);
            submitButtonHBox.setAlignment(Pos.CENTER_RIGHT);
            menuVBox.getChildren().addAll(menuLabel, menuLabel2,
                    enterOrderIDLabel, tfOrderID, submitButtonHBox);
            menuVBox.setStyle("-fx-background-color:peachpuff;-fx-padding:5px;");
        }
        
        Pane getMenuPanel()
        {
            return menuVBox; 
        }
        
        Button getSubmitButton()
        {
            return submitButton; 
        }
        
        TextField getTfOrderID()
        {
            return tfOrderID; 
        }
    }
}
