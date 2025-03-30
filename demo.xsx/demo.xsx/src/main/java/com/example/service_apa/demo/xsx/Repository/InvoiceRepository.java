package com.example.service_apa.demo.xsx.Repository;

import com.example.service_apa.demo.xsx.Entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {}