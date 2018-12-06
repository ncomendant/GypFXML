package gypfxml.ui.main;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MainScreenController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleSearchPart(ActionEvent event) {
        
    }
    
    @FXML
    private void handleAddPart(ActionEvent event) {
        
    }
    
    @FXML
    private void handleModifyPart(ActionEvent event) {
        
    }
    
    @FXML
    private void handleDeletePart(ActionEvent event) {
        
    }
    
    @FXML
    private void handleSearchProduct(ActionEvent event) {
        
    }
    
    @FXML
    private void handleAddProduct(ActionEvent event) {
        
    }
    
    @FXML
    private void handleModifyProduct(ActionEvent event) {
        
    }
    
    @FXML
    private void handleDeleteProduct(ActionEvent event) {
        
    }
    
    @FXML
    private void handleExit(ActionEvent event) {
        System.out.println("Exit");
        Platform.exit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
