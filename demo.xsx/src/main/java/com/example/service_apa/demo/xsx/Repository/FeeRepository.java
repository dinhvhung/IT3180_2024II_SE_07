package com.example.service_apa.demo.xsx.Repository;

import com.example.service_apa.demo.xsx.Entity.Fee;
import com.example.service_apa.demo.xsx.Enums.FeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;

public interface FeeRepository extends JpaRepository<Fee, Long> {
    @Query("SELECT COALESCE(SUM(f.amount), 0) FROM Fee f " +
            "WHERE (:category IS NULL OR f.category.name = :category) " +
            "AND (:feeType IS NULL OR f.feeType = :feeType) " +
            "AND (:year IS NULL OR FUNCTION('YEAR', f.date) = :year) " +
            "AND (:month IS NULL OR FUNCTION('MONTH', f.date) = :month)")
    BigDecimal calculateTotalFees(
            @Param("category") String category,
            @Param("feeType") FeeType feeType,
            @Param("year") Integer year,
            @Param("month") Integer month
    );
}
