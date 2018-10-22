/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem_keaganhilliard.View_Controller;

import inventorysystem_keaganhilliard.InventorySystem_KeaganHilliard;
import inventorysystem_keaganhilliard.Model.Part;
import inventorysystem_keaganhilliard.Model.Product;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author keagan
 */
public class AddOrEditProductScreenController implements Initializable {
    
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
    private TextField productID;
    
    @FXML
    private Label mainLabel;

    @FXML
    private TableView<Part> allPartsTable;

    @FXML
    private TableColumn<Part, String> allPartsNameColumn;
    
    @FXML
    private TableColumn<Part, Integer> allPartsIDColumn;
    
    @FXML
    private TableColumn<Part, Integer> allPartsInvColumn;
    
    @FXML
    private TableColumn<Part, Double> allPartsPriceColumn;

    @FXML
    private TableView<Part> associatedPartsTable;

    @FXML
    private TableColumn<Part, String> associatedPartsNameColumn;
    
    @FXML
    private TableColumn<Part, Integer> associatedPartsIDColumn;
    
    @FXML
    private TableColumn<Part, Integer> associatedPartsInvColumn;
    
    @FXML
    private TableColumn<Part, Double> associatedPartsPriceColumn;

    @FXML
    private TextField partSearch;

    private FilteredList<Part> filteredParts;
    private ObservableList<Part> associatedParts;
    private ObservableList<Part> allParts;
    
    private InventorySystem_KeaganHilliard app;
    private Product product;
    private Integer index;
    
    public AddOrEditProductScreenController() {
        associatedParts = FXCollections.observableArrayList();
        allParts = FXCollections.observableArrayList();
    }
    
    public void setApp(InventorySystem_KeaganHilliard app) {
        this.app = app;
    }
    
    public void setProduct(Product product, Integer index) {
        this.product = product;
        this.index = index;
        if (index == null) mainLabel.setText("Add Product");
        else {
            mainLabel.setText("Modify Product");
            productID.setText(String.valueOf(product.getProductID()));
            name.setText(product.getName());
            inv.setText(String.valueOf(product.getInStock()));
            price.setText(String.valueOf(product.getPrice()));
            min.setText(String.valueOf(product.getMin()));
            max.setText(String.valueOf(product.getMax()));
            associatedParts.addAll(product.getAssociatedParts());
        }

        associatedPartsTable.setItems(associatedParts);
    }

    public void setAllParts(ObservableList<Part> parts) {
        allParts = FXCollections.observableArrayList();
        allParts.addAll(parts);
        filteredParts = new FilteredList<>(this.allParts, p -> true);
        allPartsTable.setItems(this.filteredParts);
        handlePartSearch();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupAllPartsTable();
        setupAssociatedPartsTable();
    }

    private void setupAllPartsTable() {
        allPartsNameColumn.setCellValueFactory(part -> part.getValue().nameProperty());
        allPartsIDColumn.setCellValueFactory(part -> part.getValue().partIDProperty().asObject());
        allPartsInvColumn.setCellValueFactory(part -> part.getValue().inStockProperty().asObject());
        allPartsPriceColumn.setCellValueFactory(part -> part.getValue().priceProperty().asObject());
        NumberFormat currencyF = NumberFormat.getCurrencyInstance();
        allPartsPriceColumn.setCellFactory(tableCell -> new TableCell<Part, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } 
                else {
                    setText(currencyF.format(price));
                }
            }
        });
    }

    private void setupAssociatedPartsTable() {
        associatedPartsNameColumn.setCellValueFactory(part -> part.getValue().nameProperty());
        associatedPartsIDColumn.setCellValueFactory(part -> part.getValue().partIDProperty().asObject());
        associatedPartsInvColumn.setCellValueFactory(part -> part.getValue().inStockProperty().asObject());
        associatedPartsPriceColumn.setCellValueFactory(part -> part.getValue().priceProperty().asObject());
        NumberFormat currencyF = NumberFormat.getCurrencyInstance();
        associatedPartsPriceColumn.setCellFactory(tableCell -> new TableCell<Part, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } 
                else {
                    setText(currencyF.format(price));
                }
            }
        });
    }

    @FXML
    private void handlePartSearch() {
        String searchVal = partSearch.getText();
        filteredParts.setPredicate((Part part) -> {
            if (searchVal == null || searchVal.isEmpty()) return associatedParts.indexOf(part) < 0;

            String lowerCaseSearch = searchVal.toLowerCase();

            return part.getName().toLowerCase().contains(lowerCaseSearch) && associatedParts.indexOf(part) < 0;
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
            Double totalPrice = 0.0;
            for (Part p : associatedParts) {
                totalPrice += p.getPrice();
            }
            if (associatedParts.isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Missing Parts");
                alert.setContentText("Product cannot be saved without at least one associated part.");
                alert.showAndWait();
            }
            else if (Double.valueOf(price.getText()) < totalPrice) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Price too low");
                alert.setContentText("Product cannot be saved with a price lower than the sum of the cost of its associated parts.");
                alert.showAndWait();
            }
            else {
                product.setName(name.getText());
                product.setInStock(Integer.valueOf(inv.getText()));
                product.setPrice(Double.valueOf(price.getText()));
                product.setMin(Integer.valueOf(min.getText()));
                product.setMax(Integer.valueOf(max.getText()));
                product.resetAssociatedParts();
                for (Part part : associatedParts) {
                    product.addAssociatedPart(part);
                }
                if (product.getProductID() == -1) app.saveAdd(product);
                else app.cancelAddOrEdit();
            }
        }
        catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleAdd() {
        try {
            Part selectedPart = allPartsTable.getSelectionModel().getSelectedItem();
            Integer selectedIndex = allPartsTable.getSelectionModel().getSelectedIndex();
            if (selectedPart != null) {
                associatedParts.add(selectedPart);
                handlePartSearch();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleDelete() {
        try {
            Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
            if (selectedPart != null) {
                associatedParts.remove(selectedPart);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
