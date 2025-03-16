package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import application.SceneLoader;

public class DangNhapController {
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
	public void loadDangKy(MouseEvent e) throws IOException{
		SceneLoader.loadMouseEvent(e, "DangKy.fxml");
	}
}
