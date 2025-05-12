package com.fx.login.controller;

import com.fx.login.model.ResidentEntity;
import com.fx.login.model.User;
import com.fx.login.service.ResidentService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResidentFormController {

    @Autowired
    private ResidentService residentService;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField apartmentNumberField;

    private ResidentEntity resident;

    public void setResident(ResidentEntity resident) {
        this.resident = resident;
        if (resident != null) {
            fullNameField.setText(resident.getFullName());
            emailField.setText(resident.getEmail());
            phoneField.setText(resident.getPhone());
            apartmentNumberField.setText(resident.getApartmentNumber());
        }
    }


    @FXML
    private void onSave() {
        if (resident != null) {
            resident.setFullName(fullNameField.getText());
            resident.setEmail(emailField.getText());
            resident.setPhone(phoneField.getText());
            resident.setApartmentNumber(apartmentNumberField.getText());
            resident.syncProperties(); // đồng bộ các thuộc tính

        }
        ((Stage) fullNameField.getScene().getWindow()).close(); // Đóng cửa sổ sau khi lưu
    }

    private void saveAlert(ResidentEntity resident) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The user " + resident.getFullName() + " has been created ");
        alert.showAndWait();
    }
}
