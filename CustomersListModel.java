/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northwinddb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ambik
 * List of CustomerModel objects.  
 * This is the data model for the customers results table view.  
 */
public class CustomersListModel {
    private final ObservableList<CustomerModel> namesAndCitiesByStateData;
    
    public CustomersListModel() 
    {
        namesAndCitiesByStateData =
            FXCollections.observableArrayList();
    }
    
    public ObservableList<CustomerModel> getNamesAndCitiesByStateData()
    {
        return namesAndCitiesByStateData;
    }
}
