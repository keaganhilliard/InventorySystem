/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem_keaganhilliard.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author keagan
 */
public class Inventory {
    private ObservableList<Product> products;
    private ObservableList<Part> allParts;
    
    public Inventory() {
        products = FXCollections.observableArrayList();
        allParts = FXCollections.observableArrayList();
        for (int i = 0; i < 3; i++) allParts.add(new InHousePart("Name " + i, i));
        for (int i = 0; i < 3; i++) allParts.add(new OutsourcedPart());
    }
    
    public ObservableList<Product> getProducts() {
        return products;
    }
    
    public ObservableList<Part> getAllParts() {
        return allParts;
    }
    
    public void addPart(Part part) {
        Integer maxID = 0;
        for (Part p : allParts) {
            if (p.getPartID() > maxID) maxID = p.getPartID();
        }
        maxID++;
        part.setPartID(maxID);
        allParts.add(part);
    }
    
    public void addProduct(Product product) {
        products.add(product);
    }
    
    public Part lookupPart(Integer partID) {
        for (Part p : allParts) {
            if (p.getPartID() == partID) return p;
        }
        return null;
    }
    
    public Boolean removePart(Integer partID) {
        Part part = lookupPart(partID);
        if (part == null) return false;
        return allParts.remove(part);
    }
    
    public Boolean removeProduct(Integer index) {
        Product product = products.get(index);
        return products.remove(product);
    }
    
    public void updatePart(Integer index, Part part) {
        allParts.set(index, part);
    }
}
