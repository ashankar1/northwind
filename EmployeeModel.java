/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northwinddb;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ambik
 EmployeeModel: used the employee table view to display results.  
 */
public class EmployeeModel {
    private SimpleStringProperty firstName; 
    private SimpleStringProperty lastName; 

    public EmployeeModel(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);  
        this.lastName = new SimpleStringProperty(lastName);  
    }
    
    public String getFirstName()
    {
        return firstName.get(); 
    }
    
    public String getLastName()
    {
        return lastName.get(); 
    }
}
