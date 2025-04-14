package com.example.service_apa.demo.xsx.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cosodulieu")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "taikhoan", unique = true, nullable = false)
    private String taikhoan;

    @Column(name = "matkhau", nullable = false)
    private String matkhau;

    // Constructors
    public User() {}

    public User(String taikhoan, String matkhau) {
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getTaikhoan() { return taikhoan; }
    public void setTaikhoan(String taikhoan) { this.taikhoan = taikhoan; }

    public String getMatkhau() { return matkhau; }
    public void setMatkhau(String matkhau) { this.matkhau = matkhau; }
}
