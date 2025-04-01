package com.example.service_apa.demo.xsx.Repository;

import com.example.service_apa.demo.xsx.Entity.Invoice;
import com.example.service_apa.demo.xsx.Entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByResident(Resident resident);  // Lọc hóa đơn theo cư dân
    List<Invoice> findByPaidFalse();  // Lấy hóa đơn chưa thanh toán
}
