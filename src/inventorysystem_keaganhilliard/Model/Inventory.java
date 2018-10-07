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
}
