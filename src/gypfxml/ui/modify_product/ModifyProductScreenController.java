package gypfxml.ui.modify_product;


import gypfxml.App;
import gypfxml.core.Part;
import gypfxml.core.Product;
import gypfxml.ui.ScreenController;
import gypfxml.ui.ScreenResource;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ModifyProductScreenController implements ScreenController {   
    
    private App app;
    private FilteredList<Part> allPartsList;
    private ObservableList<Part> addedPartsList;
    
    private Product product;
    
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
        String keyword = searchInp.getText();
        app.filterParts(keyword, allPartsList);
    }
    
    @FXML
    private void handleAddPart(ActionEvent event) {
        Part part = allPartsTable.getSelectionModel().getSelectedItem();
        if (part != null) {
            addedPartsList.add(part);
        }
    }
    
    @FXML
    private void handleDeletePart(ActionEvent event) {
        Part part = addedPartsTable.getSelectionModel().getSelectedItem();
        if (part != null) {
            addedPartsList.remove(part);
        }
    }
    
    @FXML
    private void handleSave(ActionEvent event) {
        String name = nameInp.getText();
        int inStock = Integer.parseInt(invInp.getText());
        double price = Double.parseDouble(priceInp.getText());
        int min = Integer.parseInt(minInp.getText());
        int max = Integer.parseInt(maxInp.getText());
        
        product.setName(name);
        product.setInStock(inStock);
        product.setPrice(price);
        product.setMin(min);
        product.setMax(max);
        product.getAssociatedParts().clear();
        
        for (Part part : addedPartsList) {
            product.addAssociatedPart(part);
        }
        
        app.showScreen(ScreenResource.MAIN);
    }
    
    @FXML
    private void handleCancel(ActionEvent event) {
        app.showScreen(ScreenResource.MAIN);
    }
    
    @Override
    public void refresh() {
        product = app.getSelectedProduct();
        
        idInp.setText(Integer.toString(product.getProductID()));
        nameInp.setText(product.getName());
        invInp.setText(Integer.toString(product.getInStock()));
        priceInp.setText(Double.toString(product.getPrice()));
        maxInp.setText(Integer.toString(product.getMax()));
        minInp.setText(Integer.toString(product.getMax()));
        searchInp.clear();
        
        allPartsList.setPredicate(p -> true); //show all parts
        
        addedPartsList.clear();
        for (Part part : product.getAssociatedParts()) {
            addedPartsList.add(part);
        }
        
        nameInp.requestFocus();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        app = App.getInstance();
        app.setScreenController(ScreenResource.MODIFY_PRODUCT, this);

        allPartsList = app.initPartTable(allPartsTable);
        
        addedPartsList = FXCollections.observableArrayList();
        app.initPartTable(addedPartsTable, addedPartsList);
        
        refresh();
    }
    
}
