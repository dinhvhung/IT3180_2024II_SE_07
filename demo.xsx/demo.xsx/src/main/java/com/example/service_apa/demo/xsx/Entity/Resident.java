package com.example.service_apa.demo.xsx.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String apartmentNumber;

    @OneToMany(mappedBy = "resident")
    private List<Invoice> invoices;
}