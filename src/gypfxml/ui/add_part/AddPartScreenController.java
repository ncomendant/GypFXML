package gypfxml.ui.add_part;

import gypfxml.GypFXML;
import gypfxml.ui.ScreenResource;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class AddPartScreenController implements Initializable {
    
    private boolean inHouse = true;
    
    @FXML
    private void handleInHouseSelect(ActionEvent event) {
        inHouse = true;
    }
    
    @FXML
    private void handleOutsourcedSelect(ActionEvent event) {
        inHouse = false;
    }
    
    @FXML
    private void handleSave(ActionEvent event) {
        
    }
    
    @FXML
    private void handleCancel(ActionEvent event) {
        GypFXML.getInstance().showScene(ScreenResource.MAIN);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(url.toString());
        // TODO
    }    
    
}
