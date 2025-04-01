package com.example.service_apa.demo.xsx.Controller;

import com.example.service_apa.demo.xsx.Entity.Invoice;
import com.example.service_apa.demo.xsx.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;

@Controller
@RequestMapping("/invoices")
public class InvoiceViewController {

    @Autowired
    private InvoiceService invoiceService;

    // Hiển thị danh sách hóa đơn
    @GetMapping("/list")
    public String listInvoices(Model model) {
        model.addAttribute("invoices", invoiceService.getAllInvoices());
        return "invoice/list";  // Trả về template Thymeleaf "invoice/list.html"
    }

    // Hiển thị biểu mẫu tạo hóa đơn
    @GetMapping("/create")
    public String showCreateInvoiceForm(Model model) {
        model.addAttribute("invoice", new Invoice());  // Tạo đối tượng hóa đơn rỗng
        return "invoice/create";  // Trả về template Thymeleaf "invoice/create.html"
    }

    // Xử lý tạo hóa đơn
    @PostMapping("/create")
    public String createInvoice(@Valid @ModelAttribute("invoice") Invoice invoice, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "invoice/create";  // Nếu có lỗi thì trả lại form tạo hóa đơn
        }
        invoiceService.createInvoice(invoice);  // Tạo hóa đơn mới
        return "redirect:/invoices/list";  // Quay lại danh sách hóa đơn
    }

    // Hiển thị biểu mẫu cập nhật hóa đơn
    @GetMapping("/edit/{id}")
    public String showUpdateInvoiceForm(@PathVariable("id") Long id, Model model) {
        Invoice invoice = invoiceService.getInvoiceById(id).orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));
        model.addAttribute("invoice", invoice);
        return "invoice/edit";  // Trả về template Thymeleaf "invoice/edit.html"
    }

    // Xử lý cập nhật hóa đơn
    @PostMapping("/update/{id}")
    public String updateInvoice(@PathVariable("id") Long id, @Valid @ModelAttribute("invoice") Invoice invoice, BindingResult result) {
        if (result.hasErrors()) {
            return "invoice/edit";  // Nếu có lỗi thì trả lại form cập nhật
        }
        invoiceService.updateInvoice(id, invoice);  // Cập nhật hóa đơn
        return "redirect:/invoices/list";  // Quay lại danh sách hóa đơn
    }

    // Xóa hóa đơn
    @GetMapping("/delete/{id}")
    public String deleteInvoice(@PathVariable("id") Long id) {
        invoiceService.deleteInvoice(id);  // Xóa hóa đơn
        return "redirect:/invoices/list";  // Quay lại danh sách hóa đơn
    }

    // Xác nhận thanh toán hóa đơn
    @GetMapping("/confirm/{id}")
    public String confirmPayment(@PathVariable("id") Long id) {
        invoiceService.confirmPayment(id);  // Xác nhận thanh toán
        return "redirect:/invoices/list";  // Quay lại danh sách hóa đơn
    }
}
