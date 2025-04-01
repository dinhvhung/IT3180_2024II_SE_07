package com.example.service_apa.demo.xsx.Repository;

import com.example.service_apa.demo.xsx.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}