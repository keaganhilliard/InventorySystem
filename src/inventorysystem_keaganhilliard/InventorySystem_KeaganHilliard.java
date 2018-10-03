/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem_keaganhilliard;

import inventorysystem_keaganhilliard.Model.Inventory;
import inventorysystem_keaganhilliard.View_Controller.MainScreenController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author keagan
 */
public class InventorySystem_KeaganHilliard extends Application {
    
    public Inventory inv;
    
    @Override
    public void start(Stage stage) throws Exception {
        inv = new Inventory();
        //Parent root = FXMLLoader.load(getClass().getResource("View_Controller/MainScreen.fxml"));
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("View_Controller/MainScreen.fxml")
        );
        AnchorPane personOverview = (AnchorPane) loader.load();
        MainScreenController controller = loader.getController();
        controller.setInventoryApp(this);
        
        Scene scene = new Scene(personOverview);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
