package tech.szymanskazdrzalik.fuzzy;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tech.szymanskazdrzalik.fuzzy.dao.ResourcesAccidentDao;
import tech.szymanskazdrzalik.fuzzy.utils.PropertiesLoader;

import java.io.IOException;

public class Main extends Application {
    public static Stage stage;
    private static Scene scene;

    public static void main(String[] args) throws IOException {
        // TODO: 10.06.2021 sprawdzic czy to sie wywoluje
        new ResourcesAccidentDao().getAll("Data/" + PropertiesLoader.getJsonName());
        launch();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public void reload(String fxmlFileName) throws IOException {
        stage.getScene().setRoot(loadFXML(fxmlFileName));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new ResourcesAccidentDao().getAll("Data/" + PropertiesLoader.getJsonName());
        stage = primaryStage;
        scene = new Scene(loadFXML("main"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
