package gypfxml.ui.add_product;


import gypfxml.App;
import gypfxml.core.Part;
import gypfxml.misc.Event;
import gypfxml.ui.ScreenResource;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddProductScreenController implements Initializable {
    
    private App app;
    
    @FXML
    private TextField maxInp;
    @FXML
    private TextField minInp;
    @FXML
    private TextField idInp;
    @FXML
    private TextField nameInp;
    @FXML
    private TextField invInp;
    @FXML
    private TextField priceInp;
    @FXML
    private TextField searchInp;
    @FXML
    private TableView<Part> allPartsTable;
    @FXML
    private TableView<Part> addedPartsTable;
    
    @FXML
    private void handleSearch(ActionEvent event) {
        //TODO
    }
    
    @FXML
    private void handleAddPart(ActionEvent event) {
        //TODO
    }
    
    @FXML
    private void handleDeletePart(ActionEvent event) {
        //TODO
    }
    
    @FXML
    private void handleSave(ActionEvent event) {
        //TODO
    }
    
    @FXML
    private void handleCancel(ActionEvent event) {
        app.showScene(ScreenResource.MAIN);
    }
    
    private void refresh() {
        nameInp.clear();
        invInp.clear();
        priceInp.clear();
        maxInp.clear();
        minInp.clear();
        searchInp.clear();
        
        //TODO - populate/bind tables
        
        nameInp.requestFocus();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        app = App.getInstance();
        
        app.getEventManager().on(Event.SCREEN_CHANGED, (Object... data) -> {
            if (data[0].equals(ScreenResource.ADD_PART)) {
                refresh();
            }
        });
    }
}
