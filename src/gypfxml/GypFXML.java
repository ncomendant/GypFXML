package gypfxml;

import gypfxml.misc.Event;
import gypfxml.misc.EventManager;
import gypfxml.ui.ScreenResource;
import gypfxml.core.Inventory;
import gypfxml.core.Part;
import gypfxml.core.Product;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GypFXML extends Application {
    
    private static GypFXML instance;
    
    private Inventory inventory;
    
    private Part activePart;
    private Product activeProduct;
    
    private Stage stage;
    private Map<String, Scene> scenes;
    private EventManager eventManager;
    
    private int nextPartId;
    private int nextProductId;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Inventory Management Application");
        scenes = new HashMap<>();
        eventManager = new EventManager();
        instance = this;
        activePart = null;
        activeProduct = null;
        nextPartId = 0;
        nextProductId = 0;
        inventory = new Inventory();
        showScene(ScreenResource.MAIN);
    }
    
    public void showScene(String resourceName) {
        Scene scene = scenes.get(resourceName);
        if (scene == null) {
            URL rsc = getClass().getResource(resourceName);
            try {
                Parent parent = FXMLLoader.load(rsc);
                scene = new Scene(parent);
                scenes.put(resourceName, scene);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        this.stage.setScene(scene);
        this.eventManager.emit(Event.SCREEN_CHANGED, resourceName);
        this.stage.show();
    }
    
    public void addPart(Part part) {
        part.setPartID(nextPartId++);
        inventory.addPart(part);
        System.out.println("Done");
    }
    
    public void addProduct(Product product) {
        product.setProductID(nextProductId++);
        inventory.addProduct(product);
    }
    
    public static GypFXML getInstance() {
        return instance;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void setActiveProduct(Product activeProduct) {
        this.activeProduct = activeProduct;
    }
    
    public Product getActiveProduct() {
        return activeProduct;
    }
    
    public void setActivePart(Part activePart) {
        this.activePart = activePart;
    }
    
    public Part getActivePart() {
        return activePart;
    }
    
    public EventManager getEventManager() {
        return eventManager;
    }
}
