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
@Table(name = "fee")
public class FeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String feeName;

    private String amountDue;

    private String monthlyFee;

    private String unpaidHouseholds;

    // JavaFX properties
    @Transient
    private LongProperty idProperty;
    @Transient
    private StringProperty feeNameProperty;
    @Transient
    private StringProperty amountDueProperty;
    @Transient
    private StringProperty monthlyFeeProperty;
    @Transient
    private StringProperty unpaidHouseholdsProperty;

    // Constructor mặc định
    public FeeEntity() {
        this.idProperty = new SimpleLongProperty();
        this.feeNameProperty = new SimpleStringProperty();
        this.amountDueProperty = new SimpleStringProperty();
        this.monthlyFeeProperty = new SimpleStringProperty();
        this.unpaidHouseholdsProperty = new SimpleStringProperty();

        // Đồng bộ 2 chiều
        this.feeNameProperty.addListener((obs, oldVal, newVal) -> this.feeName = newVal);
        this.amountDueProperty.addListener((obs, oldVal, newVal) -> this.amountDue = newVal);
        this.monthlyFeeProperty.addListener((obs, oldVal, newVal) -> this.monthlyFee = newVal);
        this.unpaidHouseholdsProperty.addListener((obs, oldVal, newVal) -> this.unpaidHouseholds = newVal);
        this.idProperty.addListener((obs, oldVal, newVal) -> this.id = newVal.longValue());
    }


    public FeeEntity(Long id, String feeName, String amountDue, String monthlyFee, String unpaidHouseholds) {
        this();
        this.id = id;
        this.feeName = feeName;
        this.amountDue = amountDue;
        this.monthlyFee = monthlyFee;
        this.unpaidHouseholds = unpaidHouseholds;
        syncProperties();  // Đồng bộ ngay lập tức khi khởi tạo
    }

    // Getter JavaFX Property
    public LongProperty idProperty() {
        if (idProperty == null) idProperty = new SimpleLongProperty(id != null ? id : 0);
        return idProperty;
    }

    public StringProperty feeNameProperty() {
        if (feeNameProperty == null) feeNameProperty = new SimpleStringProperty(feeName);
        return feeNameProperty;
    }

    public StringProperty amountDueProperty() {
        if (amountDueProperty == null) amountDueProperty = new SimpleStringProperty(amountDue);
        return amountDueProperty;
    }

    public StringProperty monthlyFeeProperty() {
        if (monthlyFeeProperty == null) monthlyFeeProperty = new SimpleStringProperty(monthlyFee);
        return monthlyFeeProperty;
    }

    public StringProperty unpaidHouseholdsProperty() {
        if (unpaidHouseholdsProperty == null) unpaidHouseholdsProperty = new SimpleStringProperty(unpaidHouseholds);
        return unpaidHouseholdsProperty;
    }

    // Đồng bộ dữ liệu JavaFX Property với các field thông thường
    public void syncProperties() {
        idProperty().set(id != null ? id : 0);
        feeNameProperty().set(feeName != null ? feeName : "");
        amountDueProperty().set(amountDue != null ? amountDue : "");
        monthlyFeeProperty().set(monthlyFee != null ? monthlyFee : "");
        unpaidHouseholdsProperty().set(unpaidHouseholds != null ? unpaidHouseholds : "");
    }
}
