package com.fx.loginmodule;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class RegisterController {
    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passRetype;

    @FXML
    private Label thongBao;

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

    @FXML
    public void register() {
        if (userName.getText().trim().isEmpty() || password.getText().trim().isEmpty()) {
            thongBao.setText("Vui lòng nhập đầy đủ thông tin!");
        }
        else if (Objects.equals(password.getText(), passRetype.getText())) {
            if (SceneLoader.checkLogin(userName.getText(), password.getText())) {
                thongBao.setText("Tài khoản đã tồn tại. Hãy thử lại!");
            }
            else {  //thêm user và pass vào database
                String dbURL = "jdbc:mysql://localhost:3306/taikhoan";
                String user = "root";
                String pass = "root";
                try {
                    Connection connection = DriverManager.getConnection(dbURL, user, pass);

                    String sql = "insert into cosodulieu(taikhoan, matkhau) values (?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, userName.getText());
                    preparedStatement.setString(2, password.getText());
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    thongBao.setText("Đăng ký thành công!");
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        else thongBao.setText("Mật khẩu nhập lại không trùng khớp!");
    }
}
