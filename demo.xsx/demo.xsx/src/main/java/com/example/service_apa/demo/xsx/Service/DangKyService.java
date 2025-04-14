package com.example.service_apa.demo.xsx.Service;

import com.example.service_apa.demo.xsx.Entity.User;
import com.example.service_apa.demo.xsx.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DangKyService {

    @Autowired
    private UserRepository userRepository;

    public boolean registerUser(String taikhoan, String matkhau) {
        if (userRepository.findByTaikhoan(taikhoan) != null) {
            return false; // Tài khoản đã tồn tại
        }

        User user = new User(taikhoan, matkhau);
        userRepository.save(user);
        return true;
    }
}
