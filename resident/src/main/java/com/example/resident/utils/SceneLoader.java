package com.example.resident.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SceneLoader {

    // Dành cho sự kiện MouseEvent (nếu dùng click chuột)
    public static void loadMouseEvent(MouseEvent e, String fxmlFile) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(SceneLoader.class.getResource("/fxml/" + fxmlFile));
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Dành cho sự kiện ActionEvent (như click Button)
    public static void loadActionEvent(ActionEvent event, String fxmlFile) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(SceneLoader.class.getResource("/fxml/" + fxmlFile));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static boolean checkLogin(String username, String password) {
        return username.equals("admin") && password.equals("123456");
    }
    // Load giao diện mới từ file FXML
    public static void loadScene(Stage stage, String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(SceneLoader.class.getResource("/fxml/" + fxmlFile));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
