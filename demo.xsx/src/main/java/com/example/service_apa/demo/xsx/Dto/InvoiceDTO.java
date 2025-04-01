package com.example.service_apa.demo.xsx.Dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class InvoiceDTO {
    private Long residentId;
    private List<Long> feeIds;
    private double totalAmount;
    private String dueDate;
}
