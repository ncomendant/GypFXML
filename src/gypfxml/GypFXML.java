package gypfxml;

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
    
    private Stage stage;
    private Map<String, Scene> scenes;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        scenes = new HashMap<>();
        instance = this;
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
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        this.stage.setScene(scene);
        this.stage.show();
    }
    
    public static GypFXML getInstance() {
        return instance;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
