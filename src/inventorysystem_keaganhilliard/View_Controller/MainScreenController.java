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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import inventorysystem_keaganhilliard.InventorySystem_KeaganHilliard;
import inventorysystem_keaganhilliard.Model.Inventory;
import inventorysystem_keaganhilliard.Model.Part;
import inventorysystem_keaganhilliard.Model.Product;

/**
 * FXML Controller class
 *
 * @author keagan
 */
public class MainScreenController implements Initializable {
    
    private InventorySystem_KeaganHilliard app;
    private Inventory inv = new Inventory();
    
    @FXML
    private TableView<Part> partsTable;
    
    @FXML
    private TableColumn<Part, String> partNameColumn;
    
    @FXML
    private TableColumn<Part, Integer> partIDColumn;
    
    @FXML
    private TableColumn<Part, String> partInvColumn;
    
    @FXML
    private TableColumn<Part, String> partPriceColumn;
    
    @FXML
    private TableView<Product> productsTable;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(url);
        partNameColumn.setCellValueFactory(part -> part.getValue().nameProperty());
        partIDColumn.setCellValueFactory(part -> part.getValue().partIDProperty().asObject());
//        partsTable.setItems(inv.getAllParts());
    }
    
    public void setInventoryApp(InventorySystem_KeaganHilliard app) {
        this.app = app;
        this.partsTable.setItems(this.app.inv.getAllParts());
    }
    
    @FXML
    public void handleAddPart() {
        try {
            app.showAddOrEditPart();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
