/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem_keaganhilliard.Model;

import java.util.ArrayList;

/**
 *
 * @author keagan
 */
public class Product {
    
    private ArrayList<Part> associatedParts;
    private int productID;
    private String name;
    private Double price;
    private int inStock;
    private int min;
    private int max;

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public int getInStock() {
        return inStock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public ArrayList<Part> getAssociatedParts() {
        return associatedParts;
    }
    
    public void addAssociatedPart(Part thePart) {
        associatedParts.add(thePart);
    }
    
    public void removeAssociatedPart(int index) {
        associatedParts.remove(index);
    }
    
    public Part lookupAssociatedPart(int thePartId) {
        return associatedParts.get(thePartId);
    }
    
}
