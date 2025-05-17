package com.fx.login.controller;

import com.fx.login.config.PendingSessionContext;
import com.fx.login.config.Router;
import com.fx.login.config.SessionContext;
import com.fx.login.model.PendingUser;
import com.fx.login.model.User;
import com.fx.login.service.EmailService;
import com.fx.login.service.PasswordResetService;
import com.fx.login.service.PendingUserService;
import com.fx.login.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@FxmlView("/ui/changeinfo.fxml")
public class ChangeInfoController {
    @FXML
    private Text txtNotification;

    @FXML
    private TextField newName;

    @FXML
    private ComboBox newSex;

    @FXML
    private TextField newEmail;

    @FXML
    private ComboBox newNationality;

    @Autowired private PendingUserService pendingUserService;
    @Autowired private PasswordResetService resetService;
    @Autowired private EmailService emailService;
    @Autowired private Router router;
    @Autowired
    UserService userService;
    PendingUser currentPendingUser;
    User currentUser;
    public void initialize() {
        currentPendingUser = PendingSessionContext.getInstance().getCurrentPendingUser();
        currentUser = SessionContext.getInstance().getCurrentUser();
    }

    public void saveInfo(ActionEvent event) {
        if (!(newName.getText().trim().isEmpty())) currentPendingUser.setFullname(newName.getText());
        if (!(newSex.getValue() == null)) currentPendingUser.setSex(newSex.getSelectionModel().getSelectedItem().toString());
        if (!(newNationality.getValue() == null)) currentPendingUser.setCountry(newNationality.getSelectionModel().getSelectedItem().toString());
        if (!(newEmail.getText().trim().isEmpty())) {
            currentPendingUser.setEmail(newEmail.getText());
            String email = newEmail.getText();
            if (pendingUserService.existsByEmail(email)) {
                String code = resetService.generateCode();
                resetService.storeCode(email, code);
                emailService.sendAccountVerificationCode(email, code);
            }

            router.navigate(ConfirmChangeInfoController.class, event);
        }

        else {
            currentUser.setSex(currentPendingUser.getSex());
            currentUser.setCountry(currentPendingUser.getCountry());
            currentUser.setEmail(currentPendingUser.getEmail());
            currentUser.setFullname(currentPendingUser.getFullname());
            currentUser.setPassword(currentPendingUser.getPassword());
            currentUser.setRole(User.Role.Resident);
            userService.save(currentUser);
            String pendingEmail = currentPendingUser.getEmail();    //xóa PendingUser
            boolean deleted = pendingUserService.deleteUserByEmail(pendingEmail);
            if (deleted) System.out.println("Đã xóa tk");
            txtNotification.setText("Lưu thông tin thành công!");
            txtNotification.setFill(Color.web("#229abb"));

        }
    }
}
