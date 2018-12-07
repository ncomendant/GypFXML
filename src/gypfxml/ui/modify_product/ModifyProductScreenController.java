package gypfxml.ui.modify_product;


import gypfxml.App;
import gypfxml.ui.ScreenController;
import gypfxml.ui.ScreenResource;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductScreenController implements ScreenController {   
    
    private App app;
    
    @Override
    public void refresh() {
        //TODO
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        app = App.getInstance();
        app.setScreenController(ScreenResource.MODIFY_PRODUCT, this);
        refresh();
    }    
    
}
