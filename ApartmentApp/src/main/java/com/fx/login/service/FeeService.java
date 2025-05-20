package com.fx.login.service;

import javax.persistence.EntityNotFoundException;

import com.fx.login.model.FeeEntity;
import com.fx.login.repo.FeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeeService {

    @Autowired
    private FeeRepo feeRepository;

    public FeeService(FeeRepo feeRepository) {
        this.feeRepository = feeRepository;
    }

    // Lấy danh sách tất cả khoản thu
    public List<FeeEntity> findAll() {
        return feeRepository.findAll();
    }

    // Lấy thông tin khoản thu theo ID
    public Optional<FeeEntity> findById(Long id) {
        return feeRepository.findById(id);
    }

    // Tạo mới khoản thu
    public FeeEntity createFee(FeeEntity fee) {
        return feeRepository.save(fee);
    }

    // Cập nhật thông tin khoản thu
    public FeeEntity updateFee(Long id, FeeEntity feeDetails) {
        FeeEntity fee = feeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fee not found"));
        fee.setFeeName(feeDetails.getFeeName());
        fee.setAmountDue(feeDetails.getAmountDue());
        fee.setMonthlyFee(feeDetails.getMonthlyFee());
        fee.setUnpaidHouseholds(feeDetails.getUnpaidHouseholds());
        return feeRepository.save(fee);
    }

    // Xóa khoản thu
    public void deleteFee(Long id) {
        if (!feeRepository.existsById(id)) {
            throw new EntityNotFoundException("Fee not found");
        }
        feeRepository.deleteById(id);
    }
}
