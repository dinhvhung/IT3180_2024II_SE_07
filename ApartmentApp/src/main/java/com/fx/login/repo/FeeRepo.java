package com.fx.login.repo;
import com.fx.login.model.FeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepo extends JpaRepository<FeeEntity, Long> {
    // Các phương thức truy vấn
}