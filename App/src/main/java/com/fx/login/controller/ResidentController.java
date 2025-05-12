package com.fx.login.controller;

import com.fx.login.model.ResidentEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ResidentController {

    @FXML
    private TableView<ResidentEntity> residentTable;

    @FXML
    private TableColumn<ResidentEntity, Number> idColumn;

    @FXML
    private TableColumn<ResidentEntity, String> fullNameColumn;

    @FXML
    private TableColumn<ResidentEntity, String> emailColumn;

    @FXML
    private TableColumn<ResidentEntity, String> phoneColumn;

    @FXML
    private TableColumn<ResidentEntity, String> apartmentNumberColumn;

    @FXML
    private TextField searchField;

    private final ObservableList<ResidentEntity> residents = FXCollections.observableArrayList();
    private long nextId = 3; // để sinh ID tự động cho cư dân mới

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        fullNameColumn.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        apartmentNumberColumn.setCellValueFactory(cellData -> cellData.getValue().apartmentNumberProperty());

        loadResidents();
    }

    private void loadResidents() {
        residents.clear();
        residents.add(new ResidentEntity(1L, "Nguyễn Văn A", "a@gmail.com", "0123456789", "A101"));
        residents.add(new ResidentEntity(2L, "Trần Thị B", "b@gmail.com", "0987654321", "B202"));
        residents.add(new ResidentEntity(3L, "Đỗ Văn Tài", "c@gmail.com", "0987562321", "C302"));
        residentTable.setItems(residents);

        // Cập nhật nextId tự động
        nextId = residents.stream()
                .mapToLong(ResidentEntity::getId)
                .max()
                .orElse(0) + 1;
    }

    @FXML
    private void onSearch() {
        String keyword = searchField.getText();
        if (keyword == null || keyword.isEmpty()) {
            residentTable.setItems(residents);
            return;
        }

        ObservableList<ResidentEntity> filtered = FXCollections.observableArrayList();
        try {
            Long id = Long.parseLong(keyword);
            for (ResidentEntity resident : residents) {
                if (resident.getId().equals(id)) {
                    filtered.add(resident);
                }
            }
        } catch (NumberFormatException e) {
            for (ResidentEntity resident : residents) {
                if (resident.getFullName().toLowerCase().contains(keyword.toLowerCase())) {
                    filtered.add(resident);
                }
            }
        }
        residentTable.setItems(filtered);
    }

    @FXML
    private void onAdd() {
        ResidentEntity newResident = showResidentForm(null); // Thay Dialog bằng form mới
        if (newResident != null) {
            newResident.setId(nextId++);
            newResident.syncProperties(); // đồng bộ property
            residents.add(newResident);
            residentTable.setItems(residents);
        }
    }

    @FXML
    private void onUpdate() {
        ResidentEntity selected = residentTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            ResidentEntity updatedResident = showResidentForm(selected); // Thay Dialog bằng form mới
            if (updatedResident != null) {
                selected.setFullName(updatedResident.getFullName());
                selected.setEmail(updatedResident.getEmail());
                selected.setPhone(updatedResident.getPhone());
                selected.setApartmentNumber(updatedResident.getApartmentNumber());
                selected.syncProperties(); // đồng bộ property
                residentTable.refresh(); // refresh bảng
            }
        } else {
            showAlert();
        }
    }

    @FXML
    private void onDelete() {
        ResidentEntity selected = residentTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            residents.remove(selected);
            residentTable.setItems(residents);
        }
    }

    private ResidentEntity showResidentForm(ResidentEntity resident) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/residentForm.fxml"));

            Parent root = loader.load();

            ResidentFormController controller = loader.getController();
            if (resident == null) {
                resident = new ResidentEntity(); // tạo mới nếu không có đối tượng cư dân
            }
            controller.setResident(resident);

            Stage stage = new Stage();
            stage.setTitle("Thông tin cư dân");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            return resident;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Chưa chọn cư dân");
        alert.setHeaderText(null);
        alert.setContentText("Hãy chọn một cư dân để cập nhật.");
        alert.showAndWait();
    }
}
