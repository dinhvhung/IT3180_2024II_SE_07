package com.example.service_apa.demo.xsx.Controller;

import com.example.service_apa.demo.xsx.Enums.FeeType;
import com.example.service_apa.demo.xsx.Service.FeeService;
import com.example.service_apa.demo.xsx.Entity.Fee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/fees")
public class FeeController {
    private final FeeService feeService;

    @Autowired
    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }

    // Lấy danh sách khoản thu với bộ lọc và phân trang
    @GetMapping
    public ResponseEntity<Page<com.example.service_apa.demo.xsx.Entity.Fee>> getFees(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String subCategory,
            @RequestParam(required = false) FeeType feeType, // Lọc theo loại thu
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(feeService.getAllFeesByCategoryType(category, subCategory, feeType, pageable));
    }

    // Lấy khoản thu theo ID
    @GetMapping("/{id}")
    public ResponseEntity<com.example.service_apa.demo.xsx.Entity.Fee> getFeeById(@PathVariable Long id) {
        return feeService.getFeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cập nhật khoản thu
    @PutMapping("/{id}")
    public ResponseEntity<String> modifiedFee(@PathVariable Long id, @RequestBody Fee fee) {
        try {
            com.example.service_apa.demo.xsx.Entity.Fee modifiedFee; // Đổi tên biến để tránh trùng
            modifiedFee = feeService.updateFee(id, fee.getAmount(), fee.getDescription(), fee.getCategory());


            if (modifiedFee == null) {
                return ResponseEntity.badRequest().body("Cập nhật không thành công.");
            }

            return ResponseEntity.ok(modifiedFee.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateFee(@PathVariable Long id, @RequestBody Fee fee) {
        try {
            Fee updatedFee;
            updatedFee = feeService.updateFee(id, fee.getDescription(), fee.getAmount(),fee.getCategory());
            return ResponseEntity.ok(updatedFee);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    // Đánh dấu khoản thu đã thanh toán
    @PatchMapping("/{id}/pay")
    public ResponseEntity<?> markFeeAsPaid(@PathVariable Long id) {
        try {
            com.example.service_apa.demo.xsx.Entity.Fee updatedFee = feeService.markAsPaid(id);
            return ResponseEntity.ok(updatedFee);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Xác nhận thanh toán khoản thu
    @PatchMapping("/{id}/confirm-payment")
    public ResponseEntity<?> confirmPayment(@PathVariable Long id) {
        try {
            Fee updatedFee = feeService.confirmPayment(id);
            return ResponseEntity.ok(updatedFee);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Xóa khoản thu
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFee(@PathVariable Long id) {
        feeService.deleteFee(id);
        return ResponseEntity.ok("Đã xóa thành công khoản thu với id " + id);
    }

    // API thống kê tổng số tiền thu theo loại khoản thu
    @GetMapping("/statistics")
    public ResponseEntity<?> getFeeStatistics(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) FeeType feeType,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month
    ) {
        BigDecimal total = feeService.calculateTotalFees(category, feeType, year, month);
        return ResponseEntity.ok(total);
    }
}
