package com.fx.login.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Resident {

    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String apartmentNumber;

    // JavaFX Properties (để hiển thị trong TableView)
    private transient LongProperty idProperty;
    private transient StringProperty fullNameProperty;
    private transient StringProperty emailProperty;
    private transient StringProperty phoneProperty;
    private transient StringProperty apartmentNumberProperty;

    // Constructors
    public Resident() {
        // Không tham số để hỗ trợ Jackson và RestTemplate
    }

    public Resident(String fullName, String email, String phone, String apartmentNumber) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.apartmentNumber = apartmentNumber;
        syncProperties();
    }

    public Resident(Long id, String fullName, String email, String phone, String apartmentNumber) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.apartmentNumber = apartmentNumber;
        syncProperties();
    }

    // Getters & Setters (dùng cho REST API)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        if (idProperty != null) {
            this.idProperty.set(id);
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
        if (fullNameProperty != null) {
            this.fullNameProperty.set(fullName);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        if (emailProperty != null) {
            this.emailProperty.set(email);
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        if (phoneProperty != null) {
            this.phoneProperty.set(phone);
        }
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
        if (apartmentNumberProperty != null) {
            this.apartmentNumberProperty.set(apartmentNumber);
        }
    }

    // Property methods (dùng cho TableView)
    public LongProperty idProperty() {
        if (idProperty == null) {
            idProperty = new SimpleLongProperty(this, "id", id != null ? id : 0L);
        }
        return idProperty;
    }

    public StringProperty fullNameProperty() {
        if (fullNameProperty == null) {
            fullNameProperty = new SimpleStringProperty(this, "fullName", fullName);
        }
        return fullNameProperty;
    }

    public StringProperty emailProperty() {
        if (emailProperty == null) {
            emailProperty = new SimpleStringProperty(this, "email", email);
        }
        return emailProperty;
    }

    public StringProperty phoneProperty() {
        if (phoneProperty == null) {
            phoneProperty = new SimpleStringProperty(this, "phone", phone);
        }
        return phoneProperty;
    }

    public StringProperty apartmentNumberProperty() {
        if (apartmentNumberProperty == null) {
            apartmentNumberProperty = new SimpleStringProperty(this, "apartmentNumber", apartmentNumber);
        }
        return apartmentNumberProperty;
    }

    // Đồng bộ dữ liệu từ field → property (sau khi load từ backend)
    public void syncProperties() {
        idProperty();
        fullNameProperty();
        emailProperty();
        phoneProperty();
        apartmentNumberProperty();

        if (id != null) idProperty.set(id);
        if (fullName != null) fullNameProperty.set(fullName);
        if (email != null) emailProperty.set(email);
        if (phone != null) phoneProperty.set(phone);
        if (apartmentNumber != null) apartmentNumberProperty.set(apartmentNumber);
    }
}