package gypfxml.ui.main;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import gypfxml.App;
import gypfxml.core.Part;
import gypfxml.core.Product;
import gypfxml.ui.ScreenResource;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import gypfxml.ui.ScreenController;


public class MainScreenController implements ScreenController {
    
    private App app;
    
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
        app.showScreen(ScreenResource.ADD_PART);
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
        app.showScreen(ScreenResource.ADD_PRODUCT);
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
    
    @Override
    public void refresh() {
        //TODO
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        app = App.getInstance();
        app.setScreenController(ScreenResource.MAIN, this);
        
        filteredParts = app.initPartTable(partTable);
        filteredProducts = app.initProductTable(productTable);
        
        refresh();
    }
    
}
