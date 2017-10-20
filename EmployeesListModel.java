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
 * This is the data model for the results table view for employees.  
 * It is the M of MVC.  
 * It is a list of EmployeeModel objects.  
 */
public class EmployeesListModel {
    private final ObservableList<EmployeeModel> namesByBirthYearData;
    
    public EmployeesListModel() 
    {
        namesByBirthYearData =
            FXCollections.observableArrayList();
    }
    
    public ObservableList<EmployeeModel> getNamesByBirthYearData()
    {
        return namesByBirthYearData;
    }
}
