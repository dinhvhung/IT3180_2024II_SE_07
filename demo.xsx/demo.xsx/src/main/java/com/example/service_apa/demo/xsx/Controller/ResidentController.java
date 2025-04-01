package com.example.service_apa.demo.xsx.Controller;

import com.example.service_apa.demo.xsx.Entity.Resident;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ResidentController {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Resident> residentTable;

    @FXML
    private TableColumn<Resident, Long> idColumn;

    @FXML
    private TableColumn<Resident, String> nameColumn;

    @FXML
    private TableColumn<Resident, String> emailColumn;

    @FXML
    private TableColumn<Resident, String> phoneColumn;

    @FXML
    private TableColumn<Resident, String> apartmentColumn;

    private static final String API_URL = "http://localhost:8080/api/residents";  // Đảm bảo URL chính xác với Spring Boot

    private final RestTemplate restTemplate = new RestTemplate();

    // Lấy danh sách cư dân từ backend
    @FXML
    public void initialize() {
        fetchResidents();
    }

    private void fetchResidents() {
        ResponseEntity<List> response = restTemplate.exchange(API_URL, HttpMethod.GET, null, List.class);
        List<Resident> residents = response.getBody();

        // Đưa dữ liệu vào TableView
        residentTable.getItems().clear();
        residentTable.getItems().addAll(residents);

        // Thiết lập các cột trong bảng
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        apartmentColumn.setCellValueFactory(cellData -> cellData.getValue().apartmentNumberProperty());
    }

    // Tìm kiếm cư dân theo ID
    @FXML
    public void onSearch() {
        String searchId = searchField.getText();
        String url = API_URL + "/" + searchId;
        ResponseEntity<Resident> response = restTemplate.exchange(url, HttpMethod.GET, null, Resident.class);
        Resident resident = response.getBody();
        if (resident != null) {
            residentTable.getItems().clear();
            residentTable.getItems().add(resident);
        } else {
            showAlert(Alert.AlertType.WARNING, "Không tìm thấy cư dân");
        }
    }

    // Thêm một cư dân mới
    @FXML
    public void onAdd() {
        // Giả sử bạn có một form để nhập thông tin cư dân mới
        Resident newResident = new Resident("Tên mới", "email@example.com", "0123456789", "A101");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Resident> request = new HttpEntity<>(newResident, headers);

        ResponseEntity<Resident> response = restTemplate.exchange(API_URL, HttpMethod.POST, request, Resident.class);
        Resident createdResident = response.getBody();

        if (createdResident != null) {
            residentTable.getItems().add(createdResident);
            showAlert(Alert.AlertType.INFORMATION, "Cư dân đã được thêm thành công.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Có lỗi xảy ra khi thêm cư dân.");
        }
    }

    // Cập nhật thông tin cư dân
    @FXML
    public void onUpdate() {
        // Giả sử bạn chọn một cư dân từ bảng để cập nhật
        Resident selectedResident = residentTable.getSelectionModel().getSelectedItem();
        if (selectedResident != null) {
            selectedResident.setFullName("Tên cập nhật");
            selectedResident.setEmail("email_updated@example.com");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Resident> request = new HttpEntity<>(selectedResident, headers);

            ResponseEntity<Resident> response = restTemplate.exchange(API_URL + "/" + selectedResident.getId(), HttpMethod.PUT, request, Resident.class);
            Resident updatedResident = response.getBody();

            if (updatedResident != null) {
                residentTable.refresh();
                showAlert(Alert.AlertType.INFORMATION, "Cư dân đã được cập nhật.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Có lỗi xảy ra khi cập nhật cư dân.");
            }
        }
    }

    // Xóa cư dân
    @FXML
    public void onDelete() {
        Resident selectedResident = residentTable.getSelectionModel().getSelectedItem();
        if (selectedResident != null) {
            String url = API_URL + "/" + selectedResident.getId();
            restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);

            residentTable.getItems().remove(selectedResident);
            showAlert(Alert.AlertType.INFORMATION, "Cư dân đã được xóa.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Vui lòng chọn một cư dân để xóa.");
        }
    }

    // Hiển thị thông báo cảnh báo
    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
