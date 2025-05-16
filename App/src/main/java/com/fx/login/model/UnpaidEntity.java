package com.fx.login.model;

import javax.persistence.*;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "unpaid")
public class UnpaidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String residentName;

    private String apartmentName;

    private String totalPayment;

    private String dueDate;

    private Long feeID;

    // JavaFX properties
    @Transient
    private LongProperty idProperty;
    @Transient
    private StringProperty residentNameProperty;
    @Transient
    private StringProperty apartmentNameProperty;
    @Transient
    private StringProperty totalPaymentProperty;
    @Transient
    private StringProperty dueDateProperty;
    @Transient
    private LongProperty feeIDProperty;

    // Constructor mặc định
    public UnpaidEntity() {
        this.idProperty = new SimpleLongProperty();
        this.residentNameProperty = new SimpleStringProperty();
        this.apartmentNameProperty = new SimpleStringProperty();
        this.totalPaymentProperty = new SimpleStringProperty();
        this.dueDateProperty = new SimpleStringProperty();
        this.feeIDProperty = new SimpleLongProperty();

        // Đồng bộ 2 chiều
        this.residentNameProperty.addListener((obs, oldVal, newVal) -> this.residentName = newVal);
        this.apartmentNameProperty.addListener((obs, oldVal, newVal) -> this.apartmentName = newVal);
        this.totalPaymentProperty.addListener((obs, oldVal, newVal) -> this.totalPayment = newVal);
        this.dueDateProperty.addListener((obs, oldVal, newVal) -> this.dueDate = newVal);
        this.idProperty.addListener((obs, oldVal, newVal) -> this.id = newVal.longValue());
        this.feeIDProperty.addListener((obs, oldVal, newVal) -> this.feeID = newVal.longValue());
    }


    public UnpaidEntity(Long id, String residentName, String apartmentName, String totalPayment, String dueDate) {
        this();
        this.id = id;
        this.residentName = residentName;
        this.apartmentName = apartmentName;
        this.totalPayment = totalPayment;
        this.dueDate = dueDate;
        syncProperties();  // Đồng bộ ngay lập tức khi khởi tạo
    }

    // Getter JavaFX Property
    public LongProperty idProperty() {
        if (idProperty == null) idProperty = new SimpleLongProperty(id != null ? id : 0);
        return idProperty;
    }

    public LongProperty feeIDProperty() {
        if (feeIDProperty == null) feeIDProperty = new SimpleLongProperty(id != null ? id : 0);
        return feeIDProperty;
    }

    public StringProperty residentNameProperty() {
        if (residentNameProperty == null) residentNameProperty = new SimpleStringProperty(residentName);
        return residentNameProperty;
    }

    public StringProperty apartmentNameProperty() {
        if (apartmentNameProperty == null) apartmentNameProperty = new SimpleStringProperty(apartmentName);
        return apartmentNameProperty;
    }

    public StringProperty totalPaymentProperty() {
        if (totalPaymentProperty == null) totalPaymentProperty = new SimpleStringProperty(totalPayment);
        return totalPaymentProperty;
    }

    public StringProperty dueDateProperty() {
        if (dueDateProperty == null) dueDateProperty = new SimpleStringProperty(dueDate);
        return dueDateProperty;
    }

    // Đồng bộ dữ liệu JavaFX Property với các field thông thường
    public void syncProperties() {
        idProperty().set(id != null ? id : 0);
        residentNameProperty().set(residentName != null ? residentName : "");
        apartmentNameProperty().set(apartmentName != null ? apartmentName : "");
        totalPaymentProperty().set(totalPayment != null ? totalPayment : "");
        dueDateProperty().set(dueDate != null ? dueDate : "");
        feeIDProperty().set(feeID != null ? feeID : 0);
    }
}
