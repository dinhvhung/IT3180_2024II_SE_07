package com.example.service_apa.demo.xsx.Service;

import com.example.service_apa.demo.xsx.Entity.FeeCategory;
import com.example.service_apa.demo.xsx.Repository.FeeCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FeeCategoryService {
    @Autowired
    private FeeCategoryRepository feeCategoryRepository;

    public FeeCategory createFeeCategory(FeeCategory feeCategory) {
        return feeCategoryRepository.save(feeCategory);
    }

    public FeeCategory updateFeeCategory(Long id, FeeCategory updatedFeeCategory) {
        FeeCategory feeCategory = feeCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FeeCategory not found"));
        feeCategory.setName(updatedFeeCategory.getName());
        feeCategory.setType(updatedFeeCategory.getType());
        feeCategory.setUnit(updatedFeeCategory.getUnit());
        return feeCategoryRepository.save(feeCategory);
    }

    public List<FeeCategory> getAllFeeCategories() {
        return feeCategoryRepository.findAll();
    }
}