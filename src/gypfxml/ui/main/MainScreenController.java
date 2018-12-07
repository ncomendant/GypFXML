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
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class MainScreenController implements Initializable {
    
    private GypFXML app;
    
    private FilteredList<Part> filteredParts;
    private FilteredList<Product> filteredProducts;
    
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
        app.filterParts(keyword, filteredParts);
    }
    
    @FXML
    private void handleAddPart(ActionEvent event) {
        app.showScene(ScreenResource.ADD_PART);
    }
    
    @FXML
    private void handleModifyPart(ActionEvent event) {
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            app.modifyPart(selectedPart);
        }
    }
    
    @FXML
    private void handleDeletePart(ActionEvent event) {
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            app.deletePart(selectedPart);
        }
    }
    
    @FXML
    private void handleSearchProduct(ActionEvent event) {
        String keyword = productInp.getText();
        app.filterProducts(keyword, filteredProducts);
    }
    
    @FXML
    private void handleAddProduct(ActionEvent event) {
        app.showScene(ScreenResource.ADD_PRODUCT);
    }
    
    @FXML
    private void handleModifyProduct(ActionEvent event) {
        Product product = productTable.getSelectionModel().getSelectedItem();
        if (product != null) {
            app.modifyProduct(product);
        }
    }
    
    @FXML
    private void handleDeleteProduct(ActionEvent event) {
        Product product = productTable.getSelectionModel().getSelectedItem();
        if (product != null) {
            app.deleteProduct(product);
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
        
        filteredParts = app.initPartTable(partTable);
        filteredProducts = app.initProductTable(productTable);
        
    }
    
}
