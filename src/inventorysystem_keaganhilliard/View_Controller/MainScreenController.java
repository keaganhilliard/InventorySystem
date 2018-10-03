/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem_keaganhilliard.View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import inventorysystem_keaganhilliard.InventorySystem_KeaganHilliard;
import inventorysystem_keaganhilliard.Model.Part;
import inventorysystem_keaganhilliard.Model.Product;

/**
 * FXML Controller class
 *
 * @author keagan
 */
public class MainScreenController implements Initializable {
    
    private InventorySystem_KeaganHilliard app;
    
    @FXML
    private final TableView<Part> partsTable;
    
    @FXML
    private final TableColumn<Part, String> partNameColumn;
    
    @FXML
    private final TableView<Product> productsTable;
    /**
     * Initializes the controller class.
     */
    
    public MainScreenController() {
        this.partsTable = new TableView<>();
        this.partNameColumn = new TableColumn<>();
        this.productsTable = new TableView<>();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void initialize() {
        partNameColumn.setCellValueFactory(part -> part.getValue().nameProperty());
    }
    
    public void setInventoryApp(InventorySystem_KeaganHilliard app) {
        this.app = app;
        partsTable.setItems(app.inv.getAllParts());
    }
}
