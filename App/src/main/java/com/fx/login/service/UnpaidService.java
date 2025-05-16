package com.fx.login.service;

import javax.persistence.EntityNotFoundException;

import com.fx.login.model.UnpaidEntity;
import com.fx.login.repo.UnpaidRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnpaidService {
    @Autowired
    private UnpaidRepo unpaidRepository;

    public UnpaidService(UnpaidRepo unpaidRepository) {
        this.unpaidRepository = unpaidRepository;
    }

    // Lấy danh sách tất cả khoản phí
    public List<UnpaidEntity> findAll() {
        return unpaidRepository.findAll();
    }

    // Lấy thông tin cư dân theo ID
    public Optional<UnpaidEntity> findById(Long id) {
        return unpaidRepository.findById(id);
    }

    // Tạo mới
    public UnpaidEntity createResident(UnpaidEntity unpaid) {
        return unpaidRepository.save(unpaid);
    }

    // Cập nhật thông tin
    public UnpaidEntity updateUnpaid(Long id, UnpaidEntity unpaidDetails) {
        UnpaidEntity unpaid = unpaidRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unpaid not found"));
        unpaid.setResidentName(unpaidDetails.getResidentName());
        unpaid.setApartmentName(unpaidDetails.getApartmentName());
        unpaid.setTotalPayment(unpaidDetails.getTotalPayment());
        unpaid.setDueDate(unpaidDetails.getDueDate());
        unpaid.setFeeID(unpaidDetails.getFeeID());
        return unpaidRepository.save(unpaid);
    }

    // Xóa cư dân
    public void deleteUnpaid(Long id) {
        if (!unpaidRepository.existsById(id)) {
            throw new EntityNotFoundException("Unpaid not found");
        }
        unpaidRepository.deleteById(id);
    }
}
