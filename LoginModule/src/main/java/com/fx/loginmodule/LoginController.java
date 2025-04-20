package com.fx.loginmodule;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


public class LoginController {

    @FXML
    public Button dangNhap;

    @FXML
    private Label thongBao;

    @FXML
    private Label dangKy;

    @FXML
    private TextField tenDangNhap;

    @FXML
    private TextField matKhau;
    @FXML
    public void handleMouseEnter() {
        dangKy.setStyle("-fx-underline: true;");
    }

    @FXML
    public void handleMouseExit() {
        dangKy.setStyle("-fx-underline: false;");
    }

    @FXML
    public void loadRegister(MouseEvent e) throws IOException{
        SceneLoader.loadMouseEvent(e, "Register.fxml");
    }

    @FXML
    public void dangNhapAction()
    {
        if (tenDangNhap.getText().trim().isEmpty() || matKhau.getText().trim().isEmpty()) thongBao.setText("Vui lòng nhập đủ thông tin!");
        else {
            if (!SceneLoader.checkLogin(tenDangNhap.getText(), matKhau.getText()))
            {
                thongBao.setText("Không hợp lệ. Vui lòng thử lại!");
            }
            else thongBao.setText("");
        }
    }
}