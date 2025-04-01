package com.example.service_apa.demo.xsx.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "resident_id", nullable = false)
    private Resident resident;

    @ManyToMany
    @JoinTable(
            name = "invoice_fees",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "fee_id")
    )
    private List<Fee> fees;  // Danh sách các khoản phí

    private double amount;  // Tổng số tiền (trước đây là totalAmount)

    private boolean status = false;  // Trạng thái đã thanh toán hay chưa (trước đây là paid)

    @Column(name = "due_date")
    private String dueDate;  // Ngày đến hạn thanh toán


}
