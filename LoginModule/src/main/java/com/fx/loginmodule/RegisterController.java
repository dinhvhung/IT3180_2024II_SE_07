package com.fx.loginmodule;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class RegisterController {
    @FXML
    private Label dangNhap;

    @FXML
    public void handleMouseEnter() {
        dangNhap.setStyle("-fx-underline: true;");
    }

    @FXML
    public void handleMouseExit() {
        dangNhap.setStyle("-fx-underline: false;");
    }

    @FXML
    public void loadLogin(MouseEvent e) throws IOException {
        SceneLoader.loadMouseEvent(e, "Login.fxml");
    }
}
