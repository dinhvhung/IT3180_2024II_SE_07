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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeeName() {
        return feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    public String getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(String amountDue) {
        this.amountDue = amountDue;
    }

    public String getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(String monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public String getUnpaidHouseholds() {
        return unpaidHouseholds;
    }

    public void setUnpaidHouseholds(String unpaidHouseholds) {
        this.unpaidHouseholds = unpaidHouseholds;
    }

    public long getIdProperty() {
        return idProperty.get();
    }

    public LongProperty idPropertyProperty() {
        return idProperty;
    }

    public void setIdProperty(long idProperty) {
        this.idProperty.set(idProperty);
    }

    public String getFeeNameProperty() {
        return feeNameProperty.get();
    }

    public StringProperty feeNamePropertyProperty() {
        return feeNameProperty;
    }

    public void setFeeNameProperty(String feeNameProperty) {
        this.feeNameProperty.set(feeNameProperty);
    }

    public String getAmountDueProperty() {
        return amountDueProperty.get();
    }

    public StringProperty amountDuePropertyProperty() {
        return amountDueProperty;
    }

    public void setAmountDueProperty(String amountDueProperty) {
        this.amountDueProperty.set(amountDueProperty);
    }

    public String getMonthlyFeeProperty() {
        return monthlyFeeProperty.get();
    }

    public StringProperty monthlyFeePropertyProperty() {
        return monthlyFeeProperty;
    }

    public void setMonthlyFeeProperty(String monthlyFeeProperty) {
        this.monthlyFeeProperty.set(monthlyFeeProperty);
    }

    public String getUnpaidHouseholdsProperty() {
        return unpaidHouseholdsProperty.get();
    }

    public StringProperty unpaidHouseholdsPropertyProperty() {
        return unpaidHouseholdsProperty;
    }

    public void setUnpaidHouseholdsProperty(String unpaidHouseholdsProperty) {
        this.unpaidHouseholdsProperty.set(unpaidHouseholdsProperty);
    }

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
