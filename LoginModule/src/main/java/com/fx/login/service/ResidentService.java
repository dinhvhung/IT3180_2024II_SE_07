package com.fx.login.service;

import javax.persistence.EntityNotFoundException;

import com.fx.login.model.ResidentEntity;
import com.fx.login.repo.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    public ResidentService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    // Lấy danh sách tất cả cư dân
    public List<ResidentEntity> findAll() {
        return residentRepository.findAll();
    }

    // Lấy thông tin cư dân theo ID
    public Optional<ResidentEntity> findById(Long id) {
        return residentRepository.findById(id);
    }

    // Tạo mới cư dân
    public ResidentEntity createResident(ResidentEntity resident) {
        return residentRepository.save(resident);
    }

    // Cập nhật thông tin cư dân
    public ResidentEntity updateResident(Long id, ResidentEntity residentDetails) {
        ResidentEntity resident = residentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resident not found"));
        resident.setFullName(residentDetails.getFullName());
        resident.setEmail(residentDetails.getEmail());
        resident.setPhone(residentDetails.getPhone());
        resident.setApartmentNumber(residentDetails.getApartmentNumber());
        return residentRepository.save(resident);
    }

    // Xóa cư dân
    public void deleteResident(Long id) {
        if (!residentRepository.existsById(id)) {
            throw new EntityNotFoundException("Resident not found");
        }
        residentRepository.deleteById(id);
    }
}
