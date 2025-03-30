package com.example.service_apa.demo.xsx.Entity;

import com.example.service_apa.demo.xsx.Enums.FeeType;
import com.example.service_apa.demo.xsx.Enums.FeeUnit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class FeeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private FeeType type;

    @Enumerated(EnumType.STRING)
    private FeeUnit unit;

    @OneToMany(mappedBy = "category")
    private List<Fee> fees;
}