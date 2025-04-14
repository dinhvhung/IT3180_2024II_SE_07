package com.example.service_apa.demo.xsx.Controller;

import java.io.IOException;

import com.example.service_apa.demo.xsx.utils.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DangNhapController {

    @FXML
    private Text myText;

    @FXML
    private Text thongBao;

    @FXML
    private TextField tenDangNhap;

    @FXML
    private PasswordField matKhau; // Dùng PasswordField để bảo mật mật khẩu

    private int soLanSai = 0; // Biến đếm số lần nhập sai
    private static final int MAX_SAI = 3; // Giới hạn số lần nhập sai

    // Xử lý hiệu ứng chữ gạch chân khi di chuột vào
    @FXML
    public void handleMouseEnter() {
        myText.setStyle("-fx-underline: true;");
    }

    // Xử lý hiệu ứng chữ gạch chân khi di chuột ra
    @FXML
    public void handleMouseExit() {
        myText.setStyle("-fx-underline: false;");
    }

    // Chuyển sang giao diện đăng ký khi click vào liên kết
    @FXML
    public void loadDangKy(MouseEvent e) throws IOException {
        SceneLoader.loadMouseEvent(e, "DangKy.fxml");
    }

    // Xử lý đăng nhập
    @FXML
    public void dangNhap() {
        if (soLanSai >= MAX_SAI) {
            thongBao.setText("Tài khoản bị khóa tạm thời! Vui lòng thử lại sau.");
            return;
        }

        String username = tenDangNhap.getText().trim();
        String password = matKhau.getText().trim();

        // Kiểm tra nếu tên đăng nhập hoặc mật khẩu bị bỏ trống
        if (username.isEmpty() || password.isEmpty()) {
            thongBao.setText("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try {
            if (!SceneLoader.checkLogin(username, password)) {
                soLanSai++;
                thongBao.setText("Sai thông tin! Còn " + (MAX_SAI - soLanSai) + " lần thử.");
            } else {
                thongBao.setText("Đăng nhập thành công!");

                // Lấy đối tượng Stage từ hiện tại
                Stage stage = (Stage) myText.getScene().getWindow();

                // Chuyển hướng sang giao diện chính (Main.fxml)
                SceneLoader.loadScene(stage, "Main.fxml");

                soLanSai = 0; // Reset số lần sai khi đăng nhập thành công
            }
        } catch (Exception e) {
            thongBao.setText("Lỗi hệ thống. Vui lòng thử lại!");
            e.printStackTrace(); // In lỗi ra console để debug
        }
    }
}
