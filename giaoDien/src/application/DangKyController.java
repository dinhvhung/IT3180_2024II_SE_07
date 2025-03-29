package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class DangKyController {
	@FXML
	private Text myText;
	@FXML
	private PasswordField mk;
	@FXML
	private PasswordField nlmk;
	@FXML
	private TextField tdn;

	@FXML
	public void handleMouseEnter() {
	    myText.setStyle("-fx-underline: true;");
	}

	@FXML
	public void handleMouseExit() {
	    myText.setStyle("-fx-underline: false;");
	}
	
	@FXML
	public void loadDangNhap(MouseEvent e) throws IOException{
		SceneLoader.loadMouseEvent(e, "DangNhap.fxml");
	}
	
	@FXML
	public void luuDuLieu() {
		String dbURL = "jdbc:mysql://localhost:3306/taikhoan";
		String user = "root";
		String pass = "root";
		try {
			Connection connection = DriverManager.getConnection(dbURL, user, pass);
			
			String sql = "insert into cosodulieu(taikhoan, matkhau) values (?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, tdn.getText());
			preparedStatement.setString (2, mk.getText());
			preparedStatement.executeUpdate();
		    preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
