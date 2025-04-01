package com.example.service_apa.demo.xsx.Service;

import com.example.service_apa.demo.xsx.Entity.Fee;
import com.example.service_apa.demo.xsx.Entity.FeeCategory;
import com.example.service_apa.demo.xsx.Enums.FeeType;
import com.example.service_apa.demo.xsx.Repository.FeeCategoryRepository;
import com.example.service_apa.demo.xsx.Repository.FeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class FeeService {

    @Autowired
    private FeeRepository feeRepository;

    @Autowired
    private FeeCategoryRepository feeCategoryRepository;

    //  Tạo mới khoản thu
    public Fee createFee(Fee fee) {
        return feeRepository.save(fee);
    }

    //  Cập nhật khoản thu (Sửa kiểu dữ liệu Object ➝ BigDecimal, String, FeeType)
    public Fee updateFee(Long id, Object amount, Object description, Object feeType) {
        Fee fee = feeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fee not found"));

        fee.setAmount((BigDecimal) amount);
        fee.setDescription((String) description);
        fee.setFeeType((FeeType) feeType); // Giả sử có trường feeType

        return feeRepository.save(fee);
    }

    //  Lấy danh sách khoản thu có phân trang
    public Page<Fee> getAllFeesByCategoryType(String category, String subCategory, FeeType feeType, Pageable pageable) {
        return feeRepository.findAll(pageable); // Thay bằng query nếu có bộ lọc
    }

    //  Lấy khoản thu theo ID
    public Optional<Fee> getFeeById(Long id) {
        return feeRepository.findById(id);
    }

    //  Đánh dấu khoản thu là đã thanh toán
    public Fee markAsPaid(Long id) {
        Fee fee = feeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fee not found"));

        fee.setPaid(true); // Giả sử có trường `isPaid`
        return feeRepository.save(fee);
    }

    //  Xác nhận thanh toán khoản thu (Sửa kiểu trả về thành Entity.Fee)
    public Fee confirmPayment(Long id) {
        Fee fee = feeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fee not found"));

        fee.setConfirmed(true); // Giả sử có trường `isConfirmed`
        return feeRepository.save(fee);
    }

    //  Xóa khoản thu
    public void deleteFee(Long id) {
        if (!feeRepository.existsById(id)) {
            throw new EntityNotFoundException("Fee not found");
        }
        feeRepository.deleteById(id);
    }
    // Lấy danh sách tất cả khoản thu
    public List<Fee> findAll() {
        return feeRepository.findAll();
    }

    //  Tính tổng tiền thu theo loại khoản thu
    public BigDecimal calculateTotalFees(String category, FeeType feeType, Integer year, Integer month) {
        return feeRepository.calculateTotalFees(category, feeType, year, month);
    }

    //  Cập nhật khoản thu theo amount (fix lỗi)
    public Fee updateFee(Long id, Fee updatedFee) {
        Fee fee = feeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fee not found"));

        fee.setAmount(updatedFee.getAmount());
        return feeRepository.save(fee);
    }

    //  Cập nhật khoản thu theo amount, description, category (fix lỗi)
    public Fee updateFee(Long id, BigDecimal amount, String description, String category) {
        Fee fee = feeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fee not found"));

        fee.setAmount(amount);
        fee.setDescription(description);

        //
        FeeCategory feeCategory = feeCategoryRepository.findByName(category)
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + category));

        fee.setCategory(feeCategory); // Gán FeeCategory thay vì String

        return feeRepository.save(fee);
    }
}
