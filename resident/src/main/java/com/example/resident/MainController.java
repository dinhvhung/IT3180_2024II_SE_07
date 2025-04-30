package com.example.resident;

import com.example.resident.utils.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private void handleResidentButton(ActionEvent event) throws IOException {
        // Chuyển sang giao diện quản lý dân cư
        SceneLoader.loadActionEvent(event, "quanly.fxml");
    }

    @FXML
    private void handleExitButton(ActionEvent event) {
        // Thoát chương trình
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
