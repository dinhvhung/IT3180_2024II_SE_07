package com.example.service_apa.demo.xsx.Controller;

import com.example.service_apa.demo.xsx.Entity.Invoice;
import com.example.service_apa.demo.xsx.Service.InvoiceService;
import com.example.service_apa.demo.xsx.Entity.Resident;
import com.example.service_apa.demo.xsx.Service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ResidentService residentService;

    // Tạo mới hóa đơn cho cư dân dựa theo residentId
    @PostMapping("/create/{residentId}")
    public ResponseEntity<?> createInvoice(@PathVariable Long residentId,
                                           @Valid @RequestBody Invoice invoice,
                                           BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Dữ liệu không hợp lệ cho hóa đơn");
        }
        Optional<Resident> optResident = residentService.findById(residentId);
        if (!optResident.isPresent()) {
            return ResponseEntity.badRequest().body("Không tìm thấy cư dân với ID: " + residentId);
        }
        invoice.setResident(optResident.get());
        Invoice createdInvoice = invoiceService.createInvoice(invoice);
        return ResponseEntity.ok(createdInvoice);
    }

    // Lấy hóa đơn theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable Long id) {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(id);
        return invoice.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cập nhật thông tin hóa đơn
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable Long id,
                                           @Valid @RequestBody Invoice invoiceDetails,
                                           BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Dữ liệu cập nhật không hợp lệ");
        }
        try {
            Invoice updatedInvoice = invoiceService.updateInvoice(id, invoiceDetails);
            return ResponseEntity.ok(updatedInvoice);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Xóa hóa đơn
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable Long id) {
        try {
            invoiceService.deleteInvoice(id);
            return ResponseEntity.ok("Hóa đơn đã được xóa thành công");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Xác nhận thanh toán hóa đơn
    @PutMapping("/confirm/{id}")
    public ResponseEntity<?> confirmInvoicePayment(@PathVariable Long id) {
        try {
            Invoice confirmedInvoice = invoiceService.confirmPayment(id);
            return ResponseEntity.ok(confirmedInvoice);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Lấy danh sách tất cả hóa đơn, nếu có residentId thì lọc theo cư dân
    @GetMapping("/list")
    public ResponseEntity<List<Invoice>> listInvoices(@RequestParam(required = false) Long residentId) {
        List<Invoice> invoices;
        if (residentId != null) {
            invoices = invoiceService.getInvoicesByResident(residentId);
        } else {
            invoices = invoiceService.getAllInvoices();
        }
        return ResponseEntity.ok(invoices);
    }

    // Lấy danh sách các hóa đơn chưa thanh toán
    @GetMapping("/outstanding")
    public ResponseEntity<List<Invoice>> getOutstandingInvoices() {
        List<Invoice> outstandingInvoices = invoiceService.getOutstandingInvoices();
        return ResponseEntity.ok(outstandingInvoices);
    }

}
