package com.fx.login.controller;

import com.fx.login.config.Router;
import com.fx.login.config.SessionContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

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
    private ScrollPane scrollPane;

    @FXML
    private TilePane btnResident;
    @FXML
    private AnchorPane contentPane;

    @Autowired
    FxWeaver fxWeaver;
    @FXML private Text welcomeText;

    User currentUser;


    @Autowired
    private ApplicationContext applicationContext;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentUser = SessionContext.getInstance().getCurrentUser();    //Lấy thông tin của session
        if (currentUser != null) {
            welcomeText.setText(currentUser.getFullname());
        }
        AnchorPane scene2 = null;
        try {
            scene2 = FXMLLoader.load(getClass().getResource("/ui/homepage.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        scrollPane.setContent(scene2);
    }

    @FXML
    public void loadResident(MouseEvent mouseEvent) throws IOException {
        // Load Scene2.fxml
        AnchorPane scene2 = FXMLLoader.load(getClass().getResource("/ui/resident.fxml"));

        scrollPane.setContent(scene2);
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        entrance.navigate(LoginController.class, event);
    }

    @FXML
    public void loadTrangChu(MouseEvent mouseEvent) throws IOException {
        // Load Scene2.fxml
        AnchorPane scene2 = FXMLLoader.load(getClass().getResource("/ui/homepage.fxml"));

        scrollPane.setContent(scene2);
    }

    public void loadFee(MouseEvent mouseEvent) throws IOException {
        AnchorPane scene2 = FXMLLoader.load(getClass().getResource("/ui/fee.fxml"));

        scrollPane.setContent(scene2);
    }

    public void loadProfile(MouseEvent mouseEvent) throws IOException {
        AnchorPane scene2 = fxWeaver.loadView(ProfileController.class);

        scrollPane.setContent(scene2);
    }

    public void loadFeedBack(MouseEvent mouseEvent) throws IOException {
        AnchorPane scene2 = FXMLLoader.load(getClass().getResource("/ui/feedback.fxml"));

        scrollPane.setContent(scene2);
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }
    @FXML
    public void loadNotificationView(MouseEvent mouseEvent) throws IOException {
        String fxmlPath = currentUser.getRole() == User.Role.Admin
                ? "/ui/AdminSendNotificationView.fxml"
                : "/ui/ResidentNotificationsView.fxml";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        loader.setControllerFactory(applicationContext::getBean);
        Parent notificationView = loader.load();

        scrollPane.setContent(notificationView);
    }
}
