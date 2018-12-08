package gypfxml.ui.modify_part;


import gypfxml.App;
import gypfxml.core.Inhouse;
import gypfxml.core.Outsourced;
import gypfxml.core.Part;
import gypfxml.ui.ScreenController;
import gypfxml.ui.ScreenResource;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ModifyPartScreenController implements ScreenController {    
    private App app;
    private boolean inHouse;
    private Part part;
    
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outsourcedRadio;
    @FXML
    private Label machineCompanyLab;
    @FXML
    private TextField idInp;
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
        String machineCompany = machineCompanyInp.getText();
        int partID = part.getPartID();
        
        String name = nameInp.getText();
        double price;
        int inStock, min, max;
        
        try {
            price = Double.parseDouble(priceInp.getText());
        } catch (NumberFormatException e) {
            price = 0;
        }
        
        try {
            inStock = Integer.parseInt(invInp.getText());
        } catch (NumberFormatException e) {
            inStock = 0;
        }
        
        try {
            min = Integer.parseInt(minInp.getText());
        } catch (NumberFormatException e) {
            min = 0;
        }
        
        try {
            max = Integer.parseInt(maxInp.getText());
        } catch (NumberFormatException e) {
            max = 0;
        }
        
        try {
            if (inStock < min || inStock > max) {
                throw new IllegalArgumentException("Inventory value must be between the minimum and maximum");
            }
        } catch (IllegalArgumentException e) {
            app.displayError(e.getMessage());
            return;
        }
        
        if (inHouse) {
            int machineID;
            try {
                machineID = Integer.parseInt(machineCompany);
            } catch (NumberFormatException e) {
                machineID = 0;
            }
            if (part instanceof Inhouse) {
                ((Inhouse)part).setMachineID(machineID);
            } else {
                part = new Inhouse(machineID);
            }
        } else {
            if (part instanceof Outsourced) {
                ((Outsourced)part).setCompanyName(machineCompany);
            } else {
                part = new Outsourced(machineCompany);
            }
        }
        
        part.setPartID(partID);
        part.setName(name);
        part.setInStock(inStock);
        part.setPrice(price);
        part.setMin(min);
        part.setMax(max);
        
        app.updatePart(part);
        app.showScreen(ScreenResource.MAIN);
    }
    
    @FXML
    private void handleCancel(ActionEvent event) {
        if (app.displayConfirmation("Are you sure you want to cancel?")) {
            app.showScreen(ScreenResource.MAIN);
        }
    }
    
    private void updateMachineCompanyLab() {
        machineCompanyLab.setText((inHouse) ? "Machine ID" : "Company Name");
    }
    
    @Override
    public void refresh() {
        part = app.getSelectedPart();
        
        if (part == null) {
            app.showScreen(ScreenResource.MAIN);
            return;
        }
        
        if (part instanceof Inhouse) {
            inHouse = true;
            inHouseRadio.setSelected(true);
            int machineId = ((Inhouse)part).getMachineId();
            machineCompanyInp.setText(Integer.toString(machineId));
        } else {
            inHouse = false;
            outsourcedRadio.setSelected(true);
            String companyName = ((Outsourced)part).getCompanyName();
            machineCompanyInp.setText(companyName);
        }
        
        idInp.setText(Integer.toString(part.getPartID()));
        nameInp.setText(part.getName());
        invInp.setText(Integer.toString(part.getInStock()));
        priceInp.setText(Double.toString(part.getPrice()));
        maxInp.setText(Integer.toString(part.getMax()));
        minInp.setText(Integer.toString(part.getMin()));
        
        updateMachineCompanyLab();
        
        nameInp.requestFocus();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        app = App.getInstance();
        app.setScreenController(ScreenResource.MODIFY_PART, this);
        refresh();
    }
}
