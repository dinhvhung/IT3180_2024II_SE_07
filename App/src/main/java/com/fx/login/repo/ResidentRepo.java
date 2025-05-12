package com.fx.login.repo;
import com.fx.login.model.ResidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepo extends JpaRepository<ResidentEntity, Long> {}