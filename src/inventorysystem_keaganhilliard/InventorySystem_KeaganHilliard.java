/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem_keaganhilliard;

import inventorysystem_keaganhilliard.Model.InHousePart;
import inventorysystem_keaganhilliard.Model.Inventory;
import inventorysystem_keaganhilliard.Model.Part;
import inventorysystem_keaganhilliard.View_Controller.AddOrEditPartScreenController;
import inventorysystem_keaganhilliard.View_Controller.MainScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author keagan
 */
public class InventorySystem_KeaganHilliard extends Application {
    
    public Inventory inv;
    private Scene scene;
    private Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        inv = new Inventory();
        //Parent root = FXMLLoader.load(getClass().getResource("View_Controller/MainScreen.fxml"));
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("View_Controller/MainScreen.fxml")
        );
        
        AnchorPane mainScreen = (AnchorPane) loader.load();
        MainScreenController controller = loader.getController();
        controller.setInventoryApp(this);
        
        scene = new Scene(mainScreen);
        
        this.stage = stage;
        this.stage.setScene(scene);
        this.stage.show();
    }
    
    public void showAddOrEditPart(Part part, Integer index) throws Exception {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("View_Controller/AddOrEditPartScreen.fxml")
        );
        
        AnchorPane addOrEdit = (AnchorPane) loader.load();
        AddOrEditPartScreenController controller = loader.getController();
        controller.setApp(this);
        controller.setPart(part, index);
        
        Scene addOrEditScene = new Scene(addOrEdit);
        stage.setScene(addOrEditScene);
    }
    
    public void cancelAddOrEdit() throws Exception {
        stage.setScene(scene);
    }
    
    public void saveAddOrEdit(Part part, Integer index) throws Exception {
        System.out.println(part.getPrice());
        if (index == null) inv.addPart(part);
        else inv.updatePart(index, part);
        stage.setScene(scene);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
