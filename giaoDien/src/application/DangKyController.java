package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class DangKyController {
	@FXML
	private Text myText;

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
}
