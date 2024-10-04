package com.dvgiang.electricitybillingsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "configuration")
public class Configuration {
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

    public Configuration() {}
    public Configuration(String name, Integer minUse, Integer maxUse, Float price) {
        this.name = name;
        this.minUse = minUse;
        this.maxUse = maxUse;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinUse() {
        return minUse;
    }

    public void setMinUse(Integer minUse) {
        this.minUse = minUse;
    }

    public Integer getMaxUse() {
        return maxUse;
    }

    public void setMaxUse(Integer maxUse) {
        this.maxUse = maxUse;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
