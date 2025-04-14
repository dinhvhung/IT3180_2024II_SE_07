package com.example.service_apa.demo.xsx.Entity;

import com.example.service_apa.demo.xsx.Enums.FeeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "fees")
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private FeeCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FeeType feeType;

    private boolean isPaid = false;
    private boolean isConfirmed = false;

    @Column(nullable = false)
    private LocalDate date;  // Thêm trường date

    public Fee() {
    }

    public Fee(String description, BigDecimal amount, FeeCategory category, FeeType feeType, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.feeType = feeType;
        this.isPaid = false;
        this.isConfirmed = false;
        this.date = date;  // Khởi tạo date
    }

    public void setPaid(boolean paid) {
        this.isPaid = paid;
    }

    public void setConfirmed(boolean confirmed) {
        this.isConfirmed = confirmed;
    }
}
