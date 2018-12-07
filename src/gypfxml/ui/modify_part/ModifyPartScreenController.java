package gypfxml.ui.modify_part;


import gypfxml.GypFXML;
import gypfxml.core.Inhouse;
import gypfxml.core.Outsourced;
import gypfxml.core.Part;
import gypfxml.misc.Event;
import gypfxml.ui.ScreenResource;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ModifyPartScreenController implements Initializable {    
    private GypFXML app;
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
        String name = nameInp.getText();
        int inStock = Integer.parseInt(invInp.getText());
        double price = Double.parseDouble(priceInp.getText());
        int min = Integer.parseInt(minInp.getText());
        int max = Integer.parseInt(maxInp.getText());
        String machineCompany = machineCompanyInp.getText();
        int partID = part.getPartID();
        
        if (inHouse) {
            int machineID = Integer.parseInt(machineCompany);
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
        app.showScene(ScreenResource.MAIN);
    }
    
    @FXML
    private void handleCancel(ActionEvent event) {
        app.showScene(ScreenResource.MAIN);
    }
    
    private void updateMachineCompanyLab() {
        machineCompanyLab.setText((inHouse) ? "Machine ID" : "Company Name");
    }
    
    private void refresh() {
        part = app.getActivePart();
        
        if (part == null) {
            app.showScene(ScreenResource.MAIN);
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
        app = GypFXML.getInstance();
        
        app.getEventManager().on(Event.SCREEN_CHANGED, (Object... data) -> {
            if (data[0].equals(ScreenResource.MODIFY_PART)) {
                refresh();
            }
        });
    }
}
