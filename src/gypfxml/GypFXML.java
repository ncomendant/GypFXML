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
    
    private int activePartIndex;
    private int activeProductIndex;
    
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
        activePartIndex = -1;
        activeProductIndex = -1;
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
    
    public void updatePart(Part part) {
        inventory.updatePart(part);
    }
    
    public void updateProduct(Product product) {
        inventory.updateProduct(product);
    }
    
    public Product getActiveProduct() {
        if (activeProductIndex < 0) return null;
        return filteredProducts.get(activeProductIndex);
    }
    
    public Part getActivePart() {
        if (activePartIndex < 0) return null;
        return filteredParts.get(activePartIndex);
    }
    
    public void searchPart(String keyword) {
        String normalizedKeyword = keyword.trim().toLowerCase();
        if (normalizedKeyword.length() == 0) {
            filteredParts.setPredicate(part -> true);
        } else {
            filteredParts.setPredicate(part -> part.getName().toLowerCase().contains(normalizedKeyword));
        }
    }
    
    public void searchProduct(String keyword) {
        String normalizedKeyword = keyword.trim().toLowerCase();
        if (normalizedKeyword.length() == 0) {
            filteredProducts.setPredicate(product -> true);
        } else {
            filteredProducts.setPredicate(product -> product.getName().toLowerCase().contains(normalizedKeyword));
        }
    }
    
    public void modifyPart(int partIndex) {
        activePartIndex = partIndex;
        showScene(ScreenResource.MODIFY_PART);
    }
    
    public void modifyProduct(int productIndex) {
        activeProductIndex = productIndex;
        showScene(ScreenResource.MODIFY_PRODUCT);
    }
    
    public EventManager getEventManager() {
        return eventManager;
    }
    
    public boolean deletePart(int partIndex) {
        Part part = filteredParts.get(partIndex);
        return inventory.deletePart(part);
    }
    
    public boolean deleteProduct(int productIndex) {
        Product product = filteredProducts.get(productIndex);
        return inventory.removeProduct(product.getProductID());
    }
}
