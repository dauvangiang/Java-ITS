package com.dvgiang.electricitybillingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
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

    @Column(name = "status")
    private Integer status; //1: mức giá đang áp dụng, 0: mức giá đã dừng áp dụng

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
