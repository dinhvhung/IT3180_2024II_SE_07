package com.fx.login.controller;

import com.fx.login.config.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.MouseEvent;
import java.io.IOException;

@Component
@FxmlView("/ui/dashboard.fxml")
public class DashboardController {
    @Autowired
    private Router entrance;

    @FXML
    private TilePane btnResident;
    @FXML
    private BorderPane contentPane;

    @FXML
    public void loadResident(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/quanly.fxml"));
        BorderPane scene1Pane = loader.load();
        contentPane.setCenter(scene1Pane);
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        entrance.navigate(LoginController.class, event);
    }
}
