package com.example.service_apa.demo.xsx.Controller;

import com.example.service_apa.demo.xsx.Entity.Resident;
import com.example.service_apa.demo.xsx.Service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/residents")
public class ResidentViewController {

    @Autowired
    private ResidentService residentService;

    // Hiển thị danh sách cư dân
    @GetMapping
    public String listResidents(Model model) {
        List<Resident> residents = residentService.findAll();
        model.addAttribute("residents", residents);
        return "residents"; // Gọi residents.html trong thư mục templates
    }

    // Hiển thị form thêm cư dân
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("resident", new Resident("Tên mới", "email@example.com", "0123456789","A101"));
        return "add_resident";
    }

    // Xử lý thêm cư dân
    @PostMapping("/add")
    public String addResident(@ModelAttribute Resident resident) {
        residentService.createResident(resident);
        return "redirect:/residents";
    }

    // Hiển thị form chỉnh sửa cư dân
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Resident> residentOpt = residentService.findById(id);
        if (residentOpt.isPresent()) {
            model.addAttribute("resident", residentOpt.get());
            return "edit_resident";
        } else {
            return "redirect:/residents";
        }
    }

    // Xử lý cập nhật cư dân
    @PostMapping("/update/{id}")
    public String updateResident(@PathVariable Long id, @ModelAttribute Resident resident) {
        residentService.updateResident(id, resident);
        return "redirect:/residents";
    }

    // Xóa cư dân
    @GetMapping("/delete/{id}")
    public String deleteResident(@PathVariable Long id) {
        residentService.deleteResident(id);
        return "redirect:/residents";
    }
}
