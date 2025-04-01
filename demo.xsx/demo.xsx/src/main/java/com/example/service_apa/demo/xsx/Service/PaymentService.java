package com.example.service_apa.demo.xsx.Service;

import com.example.service_apa.demo.xsx.Entity.Payment;
import com.example.service_apa.demo.xsx.Repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Long id, Payment updatedPayment) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));
        payment.setInvoice(updatedPayment.getInvoice());
        payment.setAmountPaid(updatedPayment.getAmountPaid());
        payment.setPaymentMethod(updatedPayment.getPaymentMethod());
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}