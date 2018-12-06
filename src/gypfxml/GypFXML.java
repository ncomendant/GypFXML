package gypfxml;

import gypfxml.misc.Event;
import gypfxml.misc.EventManager;
import gypfxml.ui.ScreenResource;
import gypfxml.core.Inventory;
import gypfxml.core.Part;
import gypfxml.core.Product;
import gypfxml.test.Test;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class GypFXML extends Application {
    
    private static GypFXML instance;
    
    private Inventory inventory;
    
    private Part activePart;
    private Product activeProduct;
    
    private Stage stage;
    private Map<String, Scene> scenes;
    private EventManager eventManager;
    
    private FilteredList<Part> filteredParts;
    private FilteredList<Product> filteredProducts;
    
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
        nextPartId = 1;
        nextProductId = 1;
        inventory = new Inventory();
        filteredParts = new FilteredList<>(inventory.getParts(), p -> true);
        filteredProducts = new FilteredList<>(inventory.getProducts(), p -> true);
        showScene(ScreenResource.MAIN);
        Test.addRandomParts(this, 100);
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
    
    public void bindPartTable(TableView<Part> table) {
        List<TableColumn<Part, ?>> columns = table.getColumns();
        
        TableColumn<Part, Integer> idCol = (TableColumn<Part, Integer>) columns.get(0);
        idCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        
        TableColumn<Part, String> nameCol = (TableColumn<Part, String>) columns.get(1);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Part, Integer> invCol = (TableColumn<Part, Integer>) columns.get(2);
        invCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        
        TableColumn<Part, Double> priceCol = (TableColumn<Part, Double>) columns.get(3);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.setItems(filteredParts);        
    }
    
    public void bindProductTable(TableView<Product> table) {
        List<TableColumn<Product, ?>> columns = table.getColumns();
        
        TableColumn<Product, Integer> idCol = (TableColumn<Product, Integer>) columns.get(0);
        idCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        
        TableColumn<Product, String> nameCol = (TableColumn<Product, String>) columns.get(1);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Product, Integer> invCol = (TableColumn<Product, Integer>) columns.get(2);
        invCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        
        TableColumn<Product, Double> priceCol = (TableColumn<Product, Double>) columns.get(3);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        table.setItems(filteredProducts);
    }
    
    public void addPart(Part part) {
        part.setPartID(nextPartId++);
        inventory.addPart(part);
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
