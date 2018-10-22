/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem_keaganhilliard.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 *
 * @author keagan
 */
public class Inventory {
    private ObservableList<Product> products;
    private ObservableList<Part> allParts;
    private FilteredList<Part> filteredParts;
    private FilteredList<Product> filteredProducts;
    
    public Inventory() {
        products = FXCollections.observableArrayList();
        allParts = FXCollections.observableArrayList();
        filteredParts = new FilteredList<>(allParts, p -> true);
        filteredProducts = new FilteredList<>(products, p -> true);
        for (int i = 0; i < 3; i++) allParts.add(new InHousePart("Name " + i, i));
        for (int i = 0; i < 3; i++) allParts.add(new OutsourcedPart());
        for (int i = 0; i < 3; i++) {
            Product p = new Product();
            p.setName("Product " + i);
            p.setProductID(i);
            p.setPrice(i * 34.00);
            products.add(p);
        }
    }
    
    public FilteredList<Part> getAllParts() {
        return filteredParts;
    }
    
    public FilteredList<Product> getProducts() {
        return filteredProducts;
    }
    
    public void filterParts(String searchVal) {
        filteredParts.setPredicate((Part part) -> {
            if (searchVal == null || searchVal.isEmpty()) return true;

            String lowerCaseSearch = searchVal.toLowerCase();

            return part.getName().toLowerCase().contains(lowerCaseSearch);
        });
    }
    
    public void filterProducts(String searchVal) {
        filteredProducts.setPredicate((Product prod) -> {
            if (searchVal == null || searchVal.isEmpty()) return true;

            String lowerCaseSearch = searchVal.toLowerCase();

            return prod.getName().toLowerCase().contains(lowerCaseSearch);
        });
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
        Integer maxID = 0;
        for (Product p : products) {
            if (p.getProductID() > maxID) maxID = p.getProductID();
        }
        maxID++;
        product.setProductID(maxID);
        products.add(product);
    }
    
    public Part lookupPart(Integer index) {
        return filteredParts.get(index);
    }
    
    public Boolean removePart(Integer index) {
        Part part = lookupPart(index);
        if (part == null) return false;
        return allParts.remove(part);
    }

    public Product lookupProduct(Integer index) {
        return filteredProducts.get(index);
    }
    
    public Boolean removeProduct(Integer index) {
        Product product = lookupProduct(index);
        if (product == null) return false;
        return products.remove(product);
    }
    
    public void updatePart(Integer index, Part part) {
        allParts.set(index, part);
    }
}
