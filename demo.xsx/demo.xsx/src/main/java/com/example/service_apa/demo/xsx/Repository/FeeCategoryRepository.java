package com.example.service_apa.demo.xsx.Repository;

import com.example.service_apa.demo.xsx.Entity.FeeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FeeCategoryRepository extends JpaRepository<FeeCategory, Long> {
    Optional<FeeCategory> findByName(String name);
}
