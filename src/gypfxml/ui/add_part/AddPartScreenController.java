package gypfxml.ui.add_part;

import gypfxml.GypFXML;
import gypfxml.misc.Event;
import gypfxml.model.Inhouse;
import gypfxml.model.Outsourced;
import gypfxml.model.Part;
import gypfxml.ui.ScreenResource;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AddPartScreenController implements Initializable {
    
    private GypFXML app;
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
            Inhouse inhousePart = new Inhouse();
            inhousePart.setMachineID(Integer.parseInt(machineCompany));
            part = inhousePart;
        } else {
            Outsourced outsourcedPart = new Outsourced();
            outsourcedPart.setCompanyName(machineCompany);
            part = outsourcedPart;
        }
        
        part.setName(name);
        part.setInStock(inStock);
        part.setPrice(price);
        part.setMin(min);
        part.setMax(max);
        
        app.addPart(part);
        GypFXML.getInstance().showScene(ScreenResource.MAIN);
    }
    
    @FXML
    private void handleCancel(ActionEvent event) {
        GypFXML.getInstance().showScene(ScreenResource.MAIN);
    }
    
    private void updateMachineCompanyLab() {
        machineCompanyLab.setText((inHouse) ? "Machine ID" : "Company Name");
    }
    
    private void refresh() {
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
        app = GypFXML.getInstance();
        
        app.getEventManager().on(Event.SCREEN_CHANGED, (Object... data) -> {
            if (data[0].equals(ScreenResource.ADD_PART)) {
                refresh();
            }
        });
    }
    
}
