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
 * used by the customer table view to display results.  
 */
public class CustomerModel {
    private SimpleStringProperty contactName; 
    private SimpleStringProperty city;
    
    public CustomerModel(String contactName, String city) {
        this.contactName = new SimpleStringProperty(contactName);  
        this.city = new SimpleStringProperty(city);  
    }
       
    public String getContactName()
    {
        return contactName.get(); 
    }

    public String getCity()
    {
        return city.get(); 
    }
}
