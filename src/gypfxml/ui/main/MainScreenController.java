package gypfxml.ui.main;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import gypfxml.GypFXML;
import gypfxml.misc.Event;
import gypfxml.misc.EventManager;
import gypfxml.ui.ScreenResource;


public class MainScreenController implements Initializable {
        
    @FXML
    private void handleSearchPart(ActionEvent event) {
        
    }
    
    @FXML
    private void handleAddPart(ActionEvent event) {
        GypFXML.getInstance().showScene(ScreenResource.ADD_PART);
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
    
    private void refresh() {
        //TODO
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EventManager eventManager = GypFXML.getInstance().getEventManager();
        
        eventManager.on(Event.SCREEN_CHANGED, (Object... data) -> {
            if (data[0].equals(ScreenResource.MAIN)) {
                refresh();
            }
        });
    }
    
}
