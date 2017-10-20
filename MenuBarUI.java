/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northwinddb;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author ambik
 * Implements the menu bar view.  
 * The MenuBarUI contains three inner classes for each of the three menus 
 * (employees, customers, order).  
 */
public class MenuBarUI {
    private MenuBar menuBar;
    private MenuBarUI.Employees employees;
    private MenuBarUI.Customers customers;
    private MenuBarUI.Order order;
            
    public MenuBarUI()
    {
        menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color:darkseagreen;-fx-padding:5px;");

        this.employees = new MenuBarUI.Employees(); 
        this.customers = new MenuBarUI.Customers(); 
        this.order = new MenuBarUI.Order(); 
                
        menuBar.getMenus().addAll(employees.menu, customers.menu, order.menu);
    }  
    
    MenuBar getMenuBar()
    {
        return menuBar; 
    }
    
    MenuBarUI.Employees getEmployees()
    {
        return this.employees; 
    }
        
    MenuBarUI.Customers getCustomers()
    {
        return this.customers; 
    }
    
    MenuBarUI.Order getOrder()
    {
        return this.order; 
    }
    
    class Employees
    {
        private Menu menu; 
        private MenuItem miListByBirthYear;

        Employees()
        {
            menu = new Menu("Employees");
            miListByBirthYear = new MenuItem("List by Birth Year");
            menu.getItems().addAll(miListByBirthYear);
        }
        
        MenuItem getMIListByBirthYear()
        {
            return miListByBirthYear;
        }
        
    };
    
    class Customers 
    {
        private Menu menu;         
        private MenuItem miListByState;

        Customers()
        {
            menu = new Menu("Customers");
            miListByState = new MenuItem("List by State");
            menu.getItems().addAll(miListByState);
        }
        
        MenuItem getMIListByState()
        {
            return miListByState;
        }
    };
    
    class Order
    {
        private Menu menu; 
        private MenuItem miDisplayDetails;
        
        Order()
        {
            menu = new Menu("Order");
            miDisplayDetails = new MenuItem("Display Details");
            menu.getItems().addAll(miDisplayDetails);
        }
        
        MenuItem getMIDisplayDetails()
        {
            return miDisplayDetails;
        }
    };
}
