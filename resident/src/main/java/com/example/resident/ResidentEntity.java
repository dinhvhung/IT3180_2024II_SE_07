package com.example.resident;

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
@Table(name = "resident")
public class ResidentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "apartment_number")
    private String apartmentNumber;

    // JavaFX properties
    @Transient
    private LongProperty idProperty;
    @Transient
    private StringProperty fullNameProperty;
    @Transient
    private StringProperty emailProperty;
    @Transient
    private StringProperty phoneProperty;
    @Transient
    private StringProperty apartmentNumberProperty;

    // Constructor mặc định
    public ResidentEntity() {
        this.idProperty = new SimpleLongProperty();
        this.fullNameProperty = new SimpleStringProperty();
        this.emailProperty = new SimpleStringProperty();
        this.phoneProperty = new SimpleStringProperty();
        this.apartmentNumberProperty = new SimpleStringProperty();

        // Đồng bộ 2 chiều
        this.fullNameProperty.addListener((_, _, newVal) -> this.fullName = newVal);
        this.emailProperty.addListener((_, _, newVal) -> this.email = newVal);
        this.phoneProperty.addListener((_, _, newVal) -> this.phone = newVal);
        this.apartmentNumberProperty.addListener((_, _, newVal) -> this.apartmentNumber = newVal);
        this.idProperty.addListener((_, _, newVal) -> this.id = newVal.longValue());
    }


    // Constructor đầy đủ tham số
    public ResidentEntity(Long id, String fullName, String email, String phone, String apartmentNumber) {
        this();
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.apartmentNumber = apartmentNumber;
        syncProperties();
    }

    // Getter JavaFX Property
    public LongProperty idProperty() {
        if (idProperty == null) idProperty = new SimpleLongProperty(id != null ? id : 0);
        return idProperty;
    }

    public StringProperty fullNameProperty() {
        if (fullNameProperty == null) fullNameProperty = new SimpleStringProperty(fullName);
        return fullNameProperty;
    }

    public StringProperty emailProperty() {
        if (emailProperty == null) emailProperty = new SimpleStringProperty(email);
        return emailProperty;
    }

    public StringProperty phoneProperty() {
        if (phoneProperty == null) phoneProperty = new SimpleStringProperty(phone);
        return phoneProperty;
    }

    public StringProperty apartmentNumberProperty() {
        if (apartmentNumberProperty == null) apartmentNumberProperty = new SimpleStringProperty(apartmentNumber);
        return apartmentNumberProperty;
    }

    // Đồng bộ dữ liệu JavaFX Property với các field thông thường
    public void syncProperties() {
        idProperty().set(id != null ? id : 0);
        fullNameProperty().set(fullName != null ? fullName : "");
        emailProperty().set(email != null ? email : "");
        phoneProperty().set(phone != null ? phone : "");
        apartmentNumberProperty().set(apartmentNumber != null ? apartmentNumber : "");
    }
}
