package gypfxml;

import gypfxml.misc.Event;
import gypfxml.misc.EventManager;
import gypfxml.ui.ScreenResource;
import gypfxml.model.Inventory;
import gypfxml.model.Part;
import gypfxml.model.Product;
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
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        scenes = new HashMap<>();
        eventManager = new EventManager();
        instance = this;
        activePart = null;
        activeProduct = null;
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
