package com.fx.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendVerificationCode(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Mã xác thực khôi phục mật khẩu");
        message.setText("Mã xác thực của bạn là: " + code);
        mailSender.send(message);
    }

    public void sendAccountVerificationCode(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Mã xác thực tài khoản");
        message.setText("Mã xác thực của bạn là: " + code);
        mailSender.send(message);
    }

    public void sendPasswordVerificationCode(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Mã xác thực đổi mật khẩu");
        message.setText("Mã xác thực của bạn là: " + code);
        mailSender.send(message);
    }

    public void sendInfoVerificationCode(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Mã xác thực thông tin người dùng");
        message.setText("Mã xác thực của bạn là: " + code);
        mailSender.send(message);
    }
}
