package com.example.service_apa.demo.xsx.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class SceneLoader {

    // Load một giao diện FXML mới và thay đổi cửa sổ hiện tại
    public static void loadMouseEvent(MouseEvent e, String fxmlFile) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(SceneLoader.class.getResource("/fxml/" + fxmlFile));
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Load giao diện mới từ file FXML
    public static void loadScene(Stage stage, String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(SceneLoader.class.getResource("/fxml/" + fxmlFile));
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Kiểm tra thông tin đăng nhập (có thể thay bằng truy vấn database)
    public static boolean checkLogin(String username, String password) {
        return username.equals("admin") && password.equals("123456");
    }
}
