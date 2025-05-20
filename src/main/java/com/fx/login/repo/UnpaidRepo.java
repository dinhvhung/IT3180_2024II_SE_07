package com.fx.login.repo;

import com.fx.login.model.UnpaidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnpaidRepo extends JpaRepository<UnpaidEntity, Long> {

}
