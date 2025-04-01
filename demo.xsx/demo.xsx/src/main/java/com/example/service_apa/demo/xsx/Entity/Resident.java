package com.example.service_apa.demo.xsx.Entity;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "residents")
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phone;
    private String apartmentNumber;

    // Các thuộc tính StringProperty cho JavaFX (chỉ sử dụng cho UI)
    @Transient
    private StringProperty fullNameProperty;
    @Transient
    private StringProperty emailProperty;
    @Transient
    private StringProperty phoneProperty;
    @Transient
    private StringProperty apartmentNumberProperty;

    // Property cho ID
    @Transient
    private LongProperty idProperty = new SimpleLongProperty();
    // Constructor mặc định
    public Resident() {}

    // Constructor với các tham số
    public Resident(String fullName, String email, String phone, String apartmentNumber) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.apartmentNumber = apartmentNumber;
        // Khởi tạo các StringProperty cho UI
        this.fullNameProperty = new SimpleStringProperty(fullName);
        this.emailProperty = new SimpleStringProperty(email);
        this.phoneProperty = new SimpleStringProperty(phone);
        this.apartmentNumberProperty = new SimpleStringProperty(apartmentNumber);
    }

    // Các phương thức Property cho JavaFX
    public StringProperty fullNameProperty() {
        return fullNameProperty != null ? fullNameProperty : new SimpleStringProperty(fullName);
    }

    public StringProperty emailProperty() {
        return emailProperty != null ? emailProperty : new SimpleStringProperty(email);
    }

    public StringProperty phoneProperty() {
        return phoneProperty != null ? phoneProperty : new SimpleStringProperty(phone);
    }

    public StringProperty apartmentNumberProperty() {
        return apartmentNumberProperty != null ? apartmentNumberProperty : new SimpleStringProperty(apartmentNumber);
    }
    public LongProperty idProperty() {
        return idProperty != null ? idProperty : new SimpleLongProperty(id);
    }
    // Getter và Setter cho Spring Boot
    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    // Setter cho các Property nếu cần
    public void setFullNameProperty(String fullName) {
        this.fullNameProperty.set(fullName);
    }

    public void setEmailProperty(String email) {
        this.emailProperty.set(email);
    }

    public void setPhoneProperty(String phone) {
        this.phoneProperty.set(phone);
    }

    public void setApartmentNumberProperty(String apartmentNumber) {
        this.apartmentNumberProperty.set(apartmentNumber);
    }


}
