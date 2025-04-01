package com.example.service_apa.demo.xsx.Controller;

import com.example.service_apa.demo.xsx.Entity.Fee;
import com.example.service_apa.demo.xsx.Enums.FeeType;
import com.example.service_apa.demo.xsx.Service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/fees")
public class FeeViewController {

    @Autowired
    private FeeService feeService;

    // Hiển thị danh sách khoản thu
    @GetMapping
    public String listFees(Model model) {
        List<Fee> fees = feeService.findAll();
        model.addAttribute("fees", fees);
        return "fee"; // Tương ứng với fee.html
    }

    // Hiển thị form thêm khoản thu
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("fee", new Fee());
        model.addAttribute("feeTypes", FeeType.values()); // Lấy danh sách FeeType
        return "add_fee"; // Tương ứng với add_fee.html
    }

    // Xử lý thêm khoản thu
    @PostMapping("/add")
    public String addFee(@ModelAttribute Fee fee) {
        feeService.createFee(fee);
        return "redirect:/fees";
    }

    // Hiển thị form chỉnh sửa khoản thu
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Fee> feeOpt = feeService.getFeeById(id);
        if (feeOpt.isPresent()) {
            model.addAttribute("fee", feeOpt.get());
            model.addAttribute("feeTypes", FeeType.values());
            return "edit_fee"; // Tương ứng với edit_fee.html
        } else {
            return "redirect:/fees";
        }
    }

    // Xử lý cập nhật khoản thu
    @PostMapping("/update/{id}")
    public String updateFee(@PathVariable Long id, @ModelAttribute Fee fee) {
        feeService.updateFee(id, fee.getDescription(), fee.getAmount(), fee.getCategory());
        return "redirect:/fees";
    }

    // Xóa khoản thu
    @GetMapping("/delete/{id}")
    public String deleteFee(@PathVariable Long id) {
        feeService.deleteFee(id);
        return "redirect:/fees";
    }
}
