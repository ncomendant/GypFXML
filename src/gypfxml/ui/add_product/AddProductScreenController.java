package gypfxml.ui.add_product;


import gypfxml.App;
import gypfxml.core.Part;
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
import gypfxml.ui.ScreenController;

public class AddProductScreenController implements ScreenController {
    
    private App app;
    private FilteredList<Part> allPartsList;
    private ObservableList<Part> addedPartsList;
    
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
        //TODO
    }
    
    @FXML
    private void handleCancel(ActionEvent event) {
        app.showScreen(ScreenResource.MAIN);
    }
    
    @Override
    public void refresh() {
        nameInp.clear();
        invInp.clear();
        priceInp.clear();
        maxInp.clear();
        minInp.clear();
        searchInp.clear();
        
        allPartsList.setPredicate(p -> true); //show all parts
        
        nameInp.requestFocus();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        app = App.getInstance();
        app.setScreenController(ScreenResource.ADD_PRODUCT, this);

        allPartsList = app.initPartTable(allPartsTable);
        
        addedPartsList = FXCollections.observableArrayList();
        app.initPartTable(addedPartsTable, addedPartsList);
        
        refresh();
    }
}
