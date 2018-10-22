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
import inventorysystem_keaganhilliard.Model.Part;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author keagan
 */
public class Product {
    
    private ObservableList<Part> associatedParts;
    private IntegerProperty productID;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty inStock;
    private IntegerProperty min;
    private IntegerProperty max;
    
    public Product() {
        this.associatedParts = FXCollections.observableArrayList();
        this.productID = new SimpleIntegerProperty(-1);
        this.name = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.inStock = new SimpleIntegerProperty();
        this.min = new SimpleIntegerProperty();
        this.max = new SimpleIntegerProperty();
    }

    public void setProductID(int productID) {
        this.productID.set(productID);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPrice(Double price) {
        this.price.set(price);
    }

    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public void setMax(int max) {
        this.max.set(max);
    }

    public int getProductID() {
        return productID.getValue();
    }
    
    public IntegerProperty productIDProperty() {
        return productID;
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    public DoubleProperty priceProperty() {
        return price;
    }
    
    public IntegerProperty inStockProperty() {
        return inStock;
    }
    
    public IntegerProperty minProperty() {
        return min;
    }
    
    public IntegerProperty maxProperty() {
        return max;
    }

    public String getName() {
        return name.getValue();
    }

    public Double getPrice() {
        return price.getValue();
    }

    public int getInStock() {
        return inStock.getValue();
    }

    public int getMin() {
        return min.getValue();
    }

    public int getMax() {
        return max.getValue();
    }

    public void resetAssociatedParts() {
        associatedParts = FXCollections.observableArrayList();
    }

    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
    
    public void addAssociatedPart(Part thePart) {
        if (associatedParts.indexOf(thePart) < 0) associatedParts.add(thePart);
    }
    
    public void removeAssociatedPart(int index) {
        Part thePart = lookupAssociatedPart(index);
        associatedParts.remove(thePart);
    }
    
    public Part lookupAssociatedPart(int index) {
        return associatedParts.get(index);
    }
    
}
