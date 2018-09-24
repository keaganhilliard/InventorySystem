/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem_keaganhilliard.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author keagan
 */
public abstract class Part {
    private final IntegerProperty partID;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty inStock;
    private final IntegerProperty min;
    private final IntegerProperty max;
    
    public Part() {
        this.partID = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.inStock = new SimpleIntegerProperty();
        this.min = new SimpleIntegerProperty();
        this.max = new SimpleIntegerProperty();
    }
    
    public void setPartID(int partID) {
        this.partID.set(partID);
    }
    
    public int getPartID() {
        return this.partID.get();
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    
    public String getName() {
        return this.name.get();
    }
    
    public void setPrice(Double price) {
        this.price.set(price);
    }
    
    public Double getPrice() {
        return this.price.get();
    }
    
    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }
    
    public int getInStock() {
        return this.inStock.get();
    }

    public void setMin(int min) {
        this.min.set(min);
    }
    
    public int getMin() {
        return this.min.get();
    }

    public void setMax(int max) {
        this.max.set(max);
    }
    
    public int getMax() {
        return this.max.get();
    }
    
}
