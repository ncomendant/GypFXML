package gypfxml.ui.main;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import gypfxml.GypFXML;
import gypfxml.core.Part;
import gypfxml.core.Product;
import gypfxml.misc.Event;
import gypfxml.ui.ScreenResource;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class MainScreenController implements Initializable {
    
    private GypFXML app;
    
    @FXML
    TableView<Part> partTable;
    @FXML
    TableView<Product> productTable;
    @FXML
    TextField partInp;
    @FXML
    TextField productInp;
        
    @FXML
    private void handleSearchPart(ActionEvent event) {
        String keyword = partInp.getText();
        app.searchPart(keyword);
    }
    
    @FXML
    private void handleAddPart(ActionEvent event) {
        app.showScene(ScreenResource.ADD_PART);
    }
    
    @FXML
    private void handleModifyPart(ActionEvent event) {
        int index = partTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            app.modifyPart(index);
        }
    }
    
    @FXML
    private void handleDeletePart(ActionEvent event) {
        int index = partTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            app.deletePart(index);
        }
    }
    
    @FXML
    private void handleSearchProduct(ActionEvent event) {
        String keyword = productInp.getText();
        app.searchProduct(keyword);
    }
    
    @FXML
    private void handleAddProduct(ActionEvent event) {
        
    }
    
    @FXML
    private void handleModifyProduct(ActionEvent event) {
        int index = partTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            app.modifyProduct(index);
        }
    }
    
    @FXML
    private void handleDeleteProduct(ActionEvent event) {
        int index = productTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            app.deleteProduct(index);
        }
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
        app = GypFXML.getInstance();
        
        app.getEventManager().on(Event.SCREEN_CHANGED, (Object... data) -> {
            if (data[0].equals(ScreenResource.MAIN)) {
                refresh();
            }
        });
        
        app.bindPartTable(partTable);
    }
    
}
