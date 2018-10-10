/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem_keaganhilliard.View_Controller;

import inventorysystem_keaganhilliard.InventorySystem_KeaganHilliard;
import inventorysystem_keaganhilliard.Model.InHousePart;
import inventorysystem_keaganhilliard.Model.OutsourcedPart;
import inventorysystem_keaganhilliard.Model.Part;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    private String currentPartType;
    
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
    
    @FXML
    private TextField partID;
    
    @FXML
    private Label machineLabel;
    
    @FXML
    private Label mainLabel;
    
    @FXML
    private RadioButton inHouseButton;
    
    @FXML
    private RadioButton outsourcedButton;
    
    private InventorySystem_KeaganHilliard app;
    private Part part;
    private InHousePart inHouse;
    private OutsourcedPart outsourced;
    private Integer index;
    
    public AddOrEditPartScreenController() {

    }
    
    public void setApp(InventorySystem_KeaganHilliard app) {
        this.app = app;
    }
    
    public void setPart(Part part, Integer index) {
        this.part = part;
        this.index = index;
        if (index == null) mainLabel.setText("Add Part");
        else {
            mainLabel.setText("Modify Part");
            partID.setText(String.valueOf(part.getPartID()));
            name.setText(part.getName());
            inv.setText(String.valueOf(part.getInStock()));
            price.setText(String.valueOf(part.getPrice()));
            min.setText(String.valueOf(part.getMin()));
            max.setText(String.valueOf(part.getMax()));
            if (part instanceof InHousePart) {
                inHouse = (InHousePart) part;
                machineID.setText(String.valueOf(inHouse.getMachineID()));
                partType.selectToggle(inHouseButton);
            }
            else {
                outsourced = (OutsourcedPart) part;
                machineID.setText(outsourced.getCompanyName());
                machineLabel.setText("Company Name");
                partType.selectToggle(outsourcedButton);
            }
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentPartType = ((RadioButton)partType.getSelectedToggle()).getText();
        partType.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) -> {
            RadioButton theButton = (RadioButton)t1.getToggleGroup().getSelectedToggle();
            currentPartType = theButton.getText();
            if (currentPartType.equalsIgnoreCase("Outsourced")) {
                System.out.println("Setting to company name");
                machineLabel.setText("Company Name");
                machineID.setPromptText("Company Name");
            }
            else if (currentPartType.equalsIgnoreCase("In-House")) {
                machineLabel.setText("Machine ID");
                machineID.setPromptText("Machine ID");
            }
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
    
    @FXML
    public void handleSave() {
        try {
            if (currentPartType.equalsIgnoreCase("Outsourced")) {
                if (outsourced == null) outsourced = new OutsourcedPart();
                outsourced.setName(name.getText());
                outsourced.setInStock(Integer.valueOf(inv.getText()));
                outsourced.setMax(Integer.valueOf(max.getText()));
                outsourced.setMin(Integer.valueOf(min.getText()));
                outsourced.setPrice(Double.valueOf(price.getText()));
                if (partID.getText() != null && !partID.getText().equalsIgnoreCase("")) outsourced.setPartID(Integer.valueOf(partID.getText()));
                outsourced.setCompanyName(machineID.getText());
                app.saveAddOrEdit(outsourced, index);
            }
            else {
                if (inHouse == null) inHouse = new InHousePart();
                inHouse.setName(name.getText());
                inHouse.setInStock(Integer.valueOf(inv.getText()));
                inHouse.setMax(Integer.valueOf(max.getText()));
                inHouse.setMin(Integer.valueOf(min.getText()));
                inHouse.setPrice(Double.valueOf(price.getText()));
                if (partID.getText() != null && !partID.getText().equalsIgnoreCase("")) inHouse.setPartID(Integer.valueOf(partID.getText()));
                inHouse.setMachineID(Integer.valueOf(machineID.getText()));
                app.saveAddOrEdit(inHouse, index);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
    }
    
}
