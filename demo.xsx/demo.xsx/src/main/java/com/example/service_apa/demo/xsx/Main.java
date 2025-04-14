package com.example.service_apa.demo.xsx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

public class Main extends Application {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        // Khởi động Spring Boot trước khi chạy JavaFX
        applicationContext = new SpringApplicationBuilder(com.example.service_apa.demo.xsx.Application.class)
                .run(args);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Lấy Spring context
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DangNhap.fxml"));
            loader.setControllerFactory(applicationContext::getBean);

            // Load giao diện JavaFX
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Phần mềm quản lý chung cư");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
