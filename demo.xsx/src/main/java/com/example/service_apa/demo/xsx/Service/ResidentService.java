package com.example.service_apa.demo.xsx.Service;

import com.example.service_apa.demo.xsx.Entity.Resident;
import com.example.service_apa.demo.xsx.Repository.ResidentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    // Lấy danh sách tất cả cư dân
    public List<Resident> findAll() {
        return residentRepository.findAll();
    }

    // Lấy thông tin cư dân theo ID
    public Optional<Resident> findById(Long id) {
        return residentRepository.findById(id);
    }

    // Tạo mới cư dân
    public Resident createResident(Resident resident) {
        return residentRepository.save(resident);
    }

    // Cập nhật thông tin cư dân
    public Resident updateResident(Long id, Resident residentDetails) {
        Resident resident = residentRepository.findById(id)
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
