package com.example.service_apa.demo.xsx.Controller;

import com.example.service_apa.demo.xsx.Service.DangKyService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DangKyController {

    @Autowired
    private DangKyService dangKyService;

    @FXML private Text myText;
    @FXML private PasswordField mk;
    @FXML private PasswordField nlmk;
    @FXML private TextField tdn;
    @FXML private TextField hoField;
    @FXML private TextField tenField;
    @FXML private Text errorText;

    @FXML
    public void handleMouseEnter() {
        myText.setStyle("-fx-underline: true;");
    }

    @FXML
    public void handleMouseExit() {
        myText.setStyle("-fx-underline: false;");
    }

    @FXML
    public void loadDangNhap(MouseEvent e) {
        // Code chuyển Scene JavaFX nếu cần
    }

    @FXML
    public void luuDuLieu() {
        String ho = hoField.getText().trim();
        String ten = tenField.getText().trim();
        String taikhoan = tdn.getText().trim();
        String matkhau = mk.getText();
        String nhaplaiMatKhau = nlmk.getText();

        if (ho.isEmpty() || ten.isEmpty() || taikhoan.isEmpty() || matkhau.isEmpty() || nhaplaiMatKhau.isEmpty()) {
            errorText.setText("Vui lòng nhập đầy đủ thông tin!");
            errorText.setVisible(true);
            return;
        }

        if (!matkhau.equals(nhaplaiMatKhau)) {
            errorText.setText("Mật khẩu nhập lại không khớp!");
            errorText.setVisible(true);
            return;
        }

        boolean success = dangKyService.registerUser(taikhoan, matkhau);

        if (success) {
            errorText.setText("Đăng ký thành công!");
            errorText.setStyle("-fx-fill: green;");
        } else {
            errorText.setText("Tài khoản đã tồn tại hoặc có lỗi xảy ra.");
        }

        errorText.setVisible(true);
    }

    @PostMapping("/dangky")
    public boolean apiDangKy(@RequestParam String taikhoan, @RequestParam String matkhau) {
        return dangKyService.registerUser(taikhoan, matkhau);
    }
}
