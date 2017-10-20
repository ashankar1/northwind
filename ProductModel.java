/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northwinddb;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.sql.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ambik
 * ProductModel - used to display each product in a given order. 
 */
public  class ProductModel {
    
    private SimpleStringProperty product; 
    private SimpleStringProperty unitPrice; 
    private SimpleIntegerProperty quantity; 
    private SimpleStringProperty discountPercent; 

    static NumberFormat nf = NumberFormat.getCurrencyInstance();
    static DecimalFormat df = new DecimalFormat("#%");          
    
    ProductModel(String product, double unitPrice, 
                        int quantity, double discountPercent)
    {
        this.product = new SimpleStringProperty(product);  
        this.unitPrice = new SimpleStringProperty(nf.format(unitPrice)); 
        this.quantity = new SimpleIntegerProperty(quantity);
        this.discountPercent = new SimpleStringProperty(df.format(discountPercent));  
    }

    public String getProduct()
    {
        return product.get(); 
    }

    public String getUnitPrice()
    {
        return unitPrice.get(); 
    }
   
    public int getQuantity()
    {
        return quantity.get(); 
    }   

    public String getDiscountPercent()
    {
        return discountPercent.get(); 
    }  
}
