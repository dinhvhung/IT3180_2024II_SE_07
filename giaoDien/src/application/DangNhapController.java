package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import application.SceneLoader;


public class DangNhapController {
	
	@FXML
	private Text myText;
	
	@FXML
	private Text thongBao;

	@FXML
	private TextField tenDangNhap;
	
	@FXML
	private TextField matKhau;
	@FXML
	public void handleMouseEnter() {
	    myText.setStyle("-fx-underline: true;");
	}

	@FXML
	public void handleMouseExit() {
	    myText.setStyle("-fx-underline: false;");
	}

	@FXML
	public void loadDangKy(MouseEvent e) throws IOException{
		SceneLoader.loadMouseEvent(e, "DangKy.fxml");
	}
	
	@FXML
	public void dangNhap ()
	{
		if (SceneLoader.checkLogin(tenDangNhap.getText(), matKhau.getText()) == false) 
		{
			thongBao.setText("Không hợp lệ. Vui lòng thử lại!");
		}
		else thongBao.setText("");
	}
}
