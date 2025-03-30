package com.example.service_apa.demo.xsx.Controller;

import com.example.service_apa.demo.xsx.Entity.Resident;
import com.example.service_apa.demo.xsx.Service.ResidentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    // Lấy danh sách tất cả cư dân
    @GetMapping
    public ResponseEntity<List<Resident>> getAllResidents() {
        List<Resident> residents = residentService.findAll();
        return ResponseEntity.ok(residents);
    }

    // Lấy thông tin của một cư dân theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getResidentById(@PathVariable Long id) {
        Optional<Resident> residentOpt = residentService.findById(id);
        if (residentOpt.isPresent()) {
            return ResponseEntity.ok(residentOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Tạo mới cư dân
    @PostMapping
    public ResponseEntity<?> createResident(@Valid @RequestBody Resident resident) {
        Resident createdResident = residentService.createResident(resident);
        return ResponseEntity.ok(createdResident);
    }

    // Cập nhật thông tin cư dân
    @PutMapping("/{id}")
    public ResponseEntity<?> updateResident(@PathVariable Long id, @Valid @RequestBody Resident residentDetails) {
        try {
            Resident updatedResident = residentService.updateResident(id, residentDetails);
            return ResponseEntity.ok(updatedResident);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Xóa cư dân
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResident(@PathVariable Long id) {
        try {
            residentService.deleteResident(id);
            return ResponseEntity.ok("Cư dân đã được xóa thành công");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
