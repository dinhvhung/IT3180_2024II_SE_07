package com.example.service_apa.demo.xsx.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @FXML
    private Label label;  // Kết nối với Label trong FXML

    @FXML
    private Button btnDangXuat;  // Kết nối với Button trong FXML

    // Phương thức khởi tạo để cập nhật nội dung Label
    @FXML
    public void initialize() {
        label.setText("Chào mừng đến với phần mềm quản lý chung cư!");
    }

    // Phương thức xử lý sự kiện khi nhấn nút "Đăng xuất"
    @FXML
    public void dangXuat() {
        // Logic khi đăng xuất (Chuyển hướng về màn hình đăng nhập)
        System.out.println("Đăng xuất thành công!");
        // Ví dụ: Chuyển về màn hình đăng nhập:
        // SceneLoader.loadScene(primaryStage, "DangNhap.fxml");
    }
}
