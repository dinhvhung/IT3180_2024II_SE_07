package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneLoader {
    public static void loadScene(ActionEvent event, String fxmlPath) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SceneLoader.class.getResource(fxmlPath));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    
    public static void makeFadeTransition(int a, Node e)
    {
    	FadeTransition fadeTransition = new FadeTransition();
		fadeTransition.setDuration(Duration.seconds(a));
		fadeTransition.setNode(e);
		fadeTransition.setFromValue(0);
		fadeTransition.setToValue(1);
		fadeTransition.play();
    }
    
    public static void loadMouseEvent(MouseEvent event, String fxmlPath) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SceneLoader.class.getResource(fxmlPath));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/taikhoan";
    private static final String DB_USER = "root"; // Thay bằng user của bạn
    private static final String DB_PASSWORD = "root"; // Thay bằng password của bạn

    public static boolean checkLogin(String username, String password) {
        String query = "SELECT * FROM cosodulieu WHERE taikhoan = ? AND matkhau = ?";
        
        try {
        	Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password); // Chỉ dùng khi lưu mật khẩu dạng plaintext (KHÔNG KHUYẾN KHÍCH)

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Nếu có dòng kết quả, tài khoản hợp lệ
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
