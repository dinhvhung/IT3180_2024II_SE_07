package com.example.service_apa.demo.xsx.Service;

import com.example.service_apa.demo.xsx.Entity.Invoice;
import com.example.service_apa.demo.xsx.Entity.Resident;
import com.example.service_apa.demo.xsx.Repository.InvoiceRepository;
import com.example.service_apa.demo.xsx.Repository.ResidentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ResidentRepository residentRepository;

    //  Tạo hóa đơn mới
    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    //  Lấy hóa đơn theo ID
    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    // Cập nhật hóa đơn
    public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found"));

        invoice.setResident(updatedInvoice.getResident());
        invoice.setFees(updatedInvoice.getFees());
        invoice.setTotalAmount(updatedInvoice.getTotalAmount());
        invoice.setDueDate(updatedInvoice.getDueDate());

        return invoiceRepository.save(invoice);
    }

    //  Xóa hóa đơn
    public void deleteInvoice(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new EntityNotFoundException("Invoice not found");
        }
        invoiceRepository.deleteById(id);
    }

    //  Lấy danh sách tất cả hóa đơn
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    //  Lọc hóa đơn theo cư dân
    public List<Invoice> getInvoicesByResident(Long residentId) {
        Resident resident = residentRepository.findById(residentId)
                .orElseThrow(() -> new EntityNotFoundException("Resident not found"));
        return invoiceRepository.findByResident(resident);
    }

    //  Lấy danh sách hóa đơn chưa thanh toán
    public List<Invoice> getOutstandingInvoices() {
        return invoiceRepository.findByPaidFalse();
    }

    //  Xác nhận thanh toán hóa đơn
    public Invoice confirmPayment(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found"));

        invoice.setPaid(true);
        return invoiceRepository.save(invoice);
    }
}
