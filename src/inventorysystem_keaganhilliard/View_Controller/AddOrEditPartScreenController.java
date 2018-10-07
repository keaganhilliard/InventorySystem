/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem_keaganhilliard.View_Controller;

import inventorysystem_keaganhilliard.InventorySystem_KeaganHilliard;
import inventorysystem_keaganhilliard.Model.Part;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author keagan
 */
public class AddOrEditPartScreenController implements Initializable {

    @FXML
    private ToggleGroup partType;
    
    @FXML
    private TextField name;
    
    @FXML
    private TextField inv;
    
    @FXML
    private TextField price;
    
    @FXML
    private TextField max;
    
    @FXML
    private TextField min;
    
    @FXML
    private TextField machineID;
    
    private InventorySystem_KeaganHilliard app;
    private Part part;
    
    public AddOrEditPartScreenController() {

    }
    
    public void setApp(InventorySystem_KeaganHilliard app) {
        this.app = app;
    }
    
    public void setPart(Part part) {
        this.part = part;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        partType.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) -> {
            RadioButton chk = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
            System.out.println("Selected Radio Button - "+chk.getText());
        });
    }

    @FXML
    public void handleCancel() {
        try {
            app.cancelAddOrEdit();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
