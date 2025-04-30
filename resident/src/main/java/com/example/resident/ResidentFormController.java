package com.example.resident;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ResidentFormController {

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
}
