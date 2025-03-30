package com.example.service_apa.demo.xsx.Service;

import com.example.service_apa.demo.xsx.Entity.Resident;
import com.example.service_apa.demo.xsx.Repository.ResidentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResidentService {
    @Autowired
    private ResidentRepository residentRepository;

    public Resident createResident(Resident resident) {
        return residentRepository.save(resident);
    }

    public Resident updateResident(Long id, Resident updatedResident) {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resident not found"));
        resident.setName(updatedResident.getName());
        resident.setApartmentNumber(updatedResident.getApartmentNumber());
        return residentRepository.save(resident);
    }

    public List<Resident> getAllResidents() {
        return residentRepository.findAll();
    }
}