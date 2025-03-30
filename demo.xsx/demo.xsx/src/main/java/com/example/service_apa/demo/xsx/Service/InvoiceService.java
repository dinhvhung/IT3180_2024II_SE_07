package com.example.service_apa.demo.xsx.Service;

import com.example.service_apa.demo.xsx.Entity.Invoice;
import com.example.service_apa.demo.xsx.Repository.InvoiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found"));
        invoice.setResident(updatedInvoice.getResident());
        invoice.setFees(updatedInvoice.getFees());
        invoice.setTotalAmount(updatedInvoice.getTotalAmount());
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
}
