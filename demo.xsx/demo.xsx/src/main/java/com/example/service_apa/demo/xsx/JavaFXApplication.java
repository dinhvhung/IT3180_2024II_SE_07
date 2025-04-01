package com.example.service_apa.demo.xsx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFXApplication extends Application {

    private static ConfigurableApplicationContext springContext;
    private static Stage primaryStage;

    public static void main(String[] args) {
        springContext = SpringApplication.run(SpringBootApplication.class);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);

        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("JavaFX with Spring Boot");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }
}
