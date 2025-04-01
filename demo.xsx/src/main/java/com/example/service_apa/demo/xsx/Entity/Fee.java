package com.example.service_apa.demo.xsx.Entity;

import com.example.service_apa.demo.xsx.Enums.FeeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "fees") // Đặt tên bảng rõ ràng trong DB
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(nullable = false)
    private BigDecimal amount; // Dùng BigDecimal để tránh lỗi làm tròn số khi tính toán

    @ManyToOne(optional = false) // Không thể thiếu category
    @JoinColumn(name = "category_id", nullable = false)
    private FeeCategory category;

    @Enumerated(EnumType.STRING) // Lưu dưới dạng chuỗi thay vì số
    @Column(nullable = false)
    private FeeType feeType;

    private boolean isPaid = false;      // Trạng thái thanh toán
    private boolean isConfirmed = false; // Trạng thái xác nhận

    // Constructor mặc định
    public Fee() {
    }

    // Constructor có tham số đầy đủ
    public Fee(String description, BigDecimal amount, FeeCategory category, FeeType feeType) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.feeType = feeType;
        this.isPaid = false;
        this.isConfirmed = false;
    }

    // Đánh dấu đã thanh toán
    public void setPaid(boolean paid) {
        this.isPaid = paid;
    }

    // Xác nhận thanh toán
    public void setConfirmed(boolean confirmed) {
        this.isConfirmed = confirmed;
    }


}
