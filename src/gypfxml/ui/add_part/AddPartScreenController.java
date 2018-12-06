package gypfxml.ui.add_part;

import gypfxml.GypFXML;
import gypfxml.misc.Event;
import gypfxml.misc.EventManager;
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
    
    
    private void refresh() {
        //TODO
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EventManager eventManager = GypFXML.getInstance().getEventManager();
        
        eventManager.on(Event.SCREEN_CHANGED, (Object... data) -> {
            if (data[0].equals(ScreenResource.ADD_PART)) {
                refresh();
            }
        });
    }
    
}
