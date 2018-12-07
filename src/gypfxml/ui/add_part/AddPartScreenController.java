package gypfxml.ui.add_part;

import gypfxml.App;
import gypfxml.core.Inhouse;
import gypfxml.core.Outsourced;
import gypfxml.core.Part;
import gypfxml.ui.ScreenResource;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import gypfxml.ui.ScreenController;

public class AddPartScreenController implements ScreenController {
    
    private App app;
    private boolean inHouse = true;
    
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private Label machineCompanyLab;
    @FXML
    private TextField nameInp;
    @FXML
    private TextField invInp;
    @FXML
    private TextField priceInp;
    @FXML
    private TextField maxInp;
    @FXML
    private TextField minInp;
    @FXML
    private TextField machineCompanyInp;
    
    @FXML
    private void handleInHouseSelect(ActionEvent event) {
        inHouse = true;
        updateMachineCompanyLab();
    }
    
    @FXML
    private void handleOutsourcedSelect(ActionEvent event) {
        inHouse = false;
        updateMachineCompanyLab();
    }
    
    @FXML
    private void handleSave(ActionEvent event) {
        String name = nameInp.getText();
        int inStock = Integer.parseInt(invInp.getText());
        double price = Double.parseDouble(priceInp.getText());
        int min = Integer.parseInt(minInp.getText());
        int max = Integer.parseInt(maxInp.getText());
        String machineCompany = machineCompanyInp.getText();
        
        Part part;
        if (inHouse) {
            part = new Inhouse(Integer.parseInt(machineCompany));
        } else {
            part = new Outsourced(machineCompany);
        }
        
        part.setName(name);
        part.setInStock(inStock);
        part.setPrice(price);
        part.setMin(min);
        part.setMax(max);
        
        app.addPart(part);
        app.showScreen(ScreenResource.MAIN);
    }
    
    @FXML
    private void handleCancel(ActionEvent event) {
        app.showScreen(ScreenResource.MAIN);
    }
    
    private void updateMachineCompanyLab() {
        machineCompanyLab.setText((inHouse) ? "Machine ID" : "Company Name");
    }
    
    @Override
    public void refresh() {
        inHouse = true;
        inHouseRadio.setSelected(true);
        
        nameInp.clear();
        invInp.clear();
        priceInp.clear();
        maxInp.clear();
        minInp.clear();
        machineCompanyInp.clear();
        
        updateMachineCompanyLab();
        
        nameInp.requestFocus();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        app = App.getInstance();
        app.setScreenController(ScreenResource.ADD_PART, this);
        refresh();
    }
    
}
