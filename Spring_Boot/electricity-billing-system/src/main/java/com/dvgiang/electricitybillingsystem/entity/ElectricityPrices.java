package com.dvgiang.electricitybillingsystem.entity;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityPricesRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "electricity_prices")
public class ElectricityPrices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "min_use")
    private Integer minUse;

    @Column(name = "max_use")
    private Integer maxUse;

    @Column(name = "price")
    private Float price;

    @ColumnDefault("1")
    @Column(name = "status")
    private Integer status; //1: mức giá đang áp dụng, 0: mức giá đã dừng áp dụng

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "update_at")
    private Date updateAt;

    @PrePersist
    private void prePersist() {
        if (status == null) {
            status = 1;
        }
        if (createdAt == null) {
            createdAt = new Date();
        }
    }

    public void updateFromDTO(ElectricityPricesRequestDTO dto) {
        this.setName(dto.getName());
        this.setMinUse(dto.getMinUse());
        this.setMaxUse(dto.getMaxUse());
        this.setPrice(dto.getPrice());
    }
}
