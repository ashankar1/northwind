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
 * List of products (ProductModel)
 * This is the data model for the results table views
 * This is the M of MVC.   
 */
public class ProductsListModel {
    private final ObservableList<ProductModel> detailsData;
    
    public ProductsListModel() 
    {
        detailsData = FXCollections.observableArrayList();
    }
    
    public ObservableList<ProductModel> getDetailsData()
    {
        return detailsData;
    }
}
