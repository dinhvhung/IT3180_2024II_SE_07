package com.example.service_apa.demo.xsx.Entity;

import com.example.service_apa.demo.xsx.Enums.FeeType;
import com.example.service_apa.demo.xsx.Enums.FeeUnit;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "fees") // Tránh lỗi stack overflow khi in danh sách
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FeeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private FeeType type;

    @Enumerated(EnumType.STRING)
    private FeeUnit unit;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fee> fees;

}
