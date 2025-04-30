package com.example.resident;

import com.example.resident.utils.SceneLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class MainApplication extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = new SpringApplicationBuilder(ResidentApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneLoader.loadScene(primaryStage, "Main.fxml");  // Gọi giao diện chính
    }

    @Override
    public void stop() {
        springContext.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
