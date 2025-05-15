package com.fx.login.controller;

import com.fx.login.MainApplication;
import com.fx.login.config.Router;
import com.fx.login.config.SessionContext;
import com.fx.login.model.User;
import com.fx.login.service.ResidentService;
import com.fx.login.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/ui/changepassword.fxml")
public class ChangePasswordController {
    @FXML
    private PasswordField passMoiField;

    @FXML
    private PasswordField passMoiRetypeField;

    @Autowired private UserService userService;
    @Autowired private Router router;
    User currentUser;
    @FXML
    public void initialize() {
        currentUser = SessionContext.getInstance().getCurrentUser();    //Lấy thông tin của session
    }

    public void saveUser(ActionEvent e){
        currentUser.setPassword(passMoiField.getText());
        User newUser = userService.save(currentUser);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Đổi mật khẩu thành công!");
        alert.setHeaderText(null);
        router.navigate(LoginController.class, e);
    }
}
