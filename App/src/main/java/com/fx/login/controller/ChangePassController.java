package com.fx.login.controller;

import com.fx.login.config.PendingSessionContext;
import com.fx.login.config.Router;
import com.fx.login.config.SessionContext;
import com.fx.login.model.PendingUser;
import com.fx.login.model.User;
import com.fx.login.service.EmailService;
import com.fx.login.service.PasswordResetService;
import com.fx.login.service.PendingUserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@FxmlView("/ui/changepass.fxml")
public class ChangePassController {
    @FXML
    private PasswordField newPassField;

    @FXML
    private PasswordField newPassRetypeField;

    @FXML
    private Text txtNotification;

    @Autowired
    PendingUserService pendingUserService;

    @Autowired
    Router router;

    @Autowired
    PasswordResetService resetService;

    @Autowired
    EmailService emailService;
    PendingUser currentPendingUser;
    public void initialize() {
        currentPendingUser = PendingSessionContext.getInstance().getCurrentPendingUser();
    }

    public void savePass(ActionEvent event) {
        if (!(newPassField.getText().equals(newPassRetypeField.getText()))) txtNotification.setText("Mật khẩu nhập lại phải trùng khớp!");
        else if (newPassField.getText().equals(currentPendingUser.getPassword())) txtNotification.setText("Mật khẩu mới phải khác mật khẩu cũ!");
        else {
            currentPendingUser.setPassword(newPassField.getText());
            pendingUserService.save(currentPendingUser);
            String email = currentPendingUser.getEmail();
            if (pendingUserService.existsByEmail(email)) {
                String code = resetService.generateCode();
                resetService.storeCode(email, code);
                emailService.sendPasswordVerificationCode(email, code);
            }
            router.navigate(ConfirmChangePassController.class, event);
        }
    }
}
