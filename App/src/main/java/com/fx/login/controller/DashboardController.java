package com.fx.login.controller;

import com.fx.login.config.Router;
import com.fx.login.config.SessionContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.fx.login.model.User;

@Component
@FxmlView("/ui/dashboard.fxml")
public class DashboardController implements Initializable {
    @Autowired
    private Router entrance;

    @FXML
    private TilePane btnResident;
    @FXML
    private BorderPane contentPane;

    @FXML private Text welcomeText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User currentUser = SessionContext.getInstance().getCurrentUser();
        if (currentUser != null) {
            welcomeText.setText("Xin chào, " + currentUser.getFullname());
        }
    }

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
