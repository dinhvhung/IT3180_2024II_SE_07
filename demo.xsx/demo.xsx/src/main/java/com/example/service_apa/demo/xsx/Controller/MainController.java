package com.example.service_apa.demo.xsx.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @FXML
    private Label label;

    @FXML
    public void initialize() {
        label.setText("Hello from Spring Boot & JavaFX!");
    }
}
