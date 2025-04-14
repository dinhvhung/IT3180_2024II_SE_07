package com.example.service_apa.demo.xsx.Repository;

import com.example.service_apa.demo.xsx.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByTaikhoan(String taikhoan);
}
