package inventorysystem_keaganhilliard.View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import inventorysystem_keaganhilliard.InventorySystem_KeaganHilliard;
import inventorysystem_keaganhilliard.Model.InHousePart;
import inventorysystem_keaganhilliard.Model.Part;
import inventorysystem_keaganhilliard.Model.Product;
import java.text.NumberFormat;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author keagan
 */
public class MainScreenController implements Initializable {
    
    private InventorySystem_KeaganHilliard app;
    private FilteredList<Part> filteredParts;
    
    @FXML
    private TableView<Part> partsTable;
    
    @FXML
    private TableColumn<Part, String> partNameColumn;
    
    @FXML
    private TableColumn<Part, Integer> partIDColumn;
    
    @FXML
    private TableColumn<Part, Integer> partInvColumn;
    
    @FXML
    private TableColumn<Part, Double> partPriceColumn;
    
    @FXML
    private TableView<Product> productsTable;
    
    @FXML
    private TableColumn<Product, String> productNameColumn;
    
    @FXML
    private TableColumn<Product, Integer> productIDColumn;
    
    @FXML
    private TableColumn<Product, Integer> productInvColumn;
    
    @FXML
    private TableColumn<Product, Double> productPriceColumn;
    
    @FXML
    private TextField partSearch;
    
    @FXML
    private TextField productSearch;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupPartsTable();
        setupProductsTable();
    }
    
    private void setupPartsTable() {
        partNameColumn.setCellValueFactory(part -> part.getValue().nameProperty());
        partIDColumn.setCellValueFactory(part -> part.getValue().partIDProperty().asObject());
        partInvColumn.setCellValueFactory(part -> part.getValue().inStockProperty().asObject());
        partPriceColumn.setCellValueFactory(part -> part.getValue().priceProperty().asObject());
        NumberFormat currencyF = NumberFormat.getCurrencyInstance();
        partPriceColumn.setCellFactory(tableCell -> new TableCell<Part, Double>() {
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
    
    private void setupProductsTable() {
        productNameColumn.setCellValueFactory(prod -> prod.getValue().nameProperty());
        productIDColumn.setCellValueFactory(product -> product.getValue().productIDProperty().asObject());
        productInvColumn.setCellValueFactory(product -> product.getValue().inStockProperty().asObject());
        productPriceColumn.setCellValueFactory(product -> product.getValue().priceProperty().asObject());
        NumberFormat currencyF = NumberFormat.getCurrencyInstance();
        productPriceColumn.setCellFactory(tableCell -> new TableCell<Product, Double>() {
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
    
    public void setInventoryApp(InventorySystem_KeaganHilliard app) {
        this.app = app;
        this.partsTable.setItems(app.inv.getAllParts());
        this.productsTable.setItems(app.inv.getProducts());
    }
    
    @FXML
    private void handlePartSearch() {
        String searchVal = partSearch.getText();
        app.inv.filterParts(searchVal);
    }
    
    @FXML
    private void handleProductSearch() {
        String searchVal = productSearch.getText();
        app.inv.filterProducts(searchVal);
    }
    
    @FXML
    private void handleExitClick() {
        System.exit(0);
    }
    
    @FXML
    private void handleAddPart() {
        try {
            InHousePart part = new InHousePart();
            app.showAddOrEditPart(part, null);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private void handleModifyPart() {
        try {
            Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
            Integer selectedIndex = partsTable.getSelectionModel().getSelectedIndex();
            if (selectedPart != null) {
                app.showAddOrEditPart(selectedPart, selectedIndex);
            }    
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private void handleDeletePart() {
        try {
            Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
            Integer selectedIndex = partsTable.getSelectionModel().getSelectedIndex();
            app.inv.removePart(selectedIndex);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleModifyProduct() {
        try {
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
            Integer selectedIndex = productsTable.getSelectionModel().getSelectedIndex();
            if (selectedProduct != null) {
                app.showAddOrEditProduct(selectedProduct, selectedIndex);
            }    
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleAddProduct() {
        try {
            Product product = new Product();
            app.showAddOrEditProduct(product, null);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleDeleteProduct() {
        try {
            Integer selectedIndex = productsTable.getSelectionModel().getSelectedIndex();
            app.inv.removeProduct(selectedIndex);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
