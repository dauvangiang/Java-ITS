package com.dvgiang.electricitybillingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "electricity_bill")
public class ElectricityBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
//    private Customer customer;
    private Long customerId;

    @Column(name = "writing_date")
    private Date writingDate;

    @Column(name = "billing_period")
    private String billingPeriod;

    @Column(name = "previous_reading")
    private Integer previousReading;

    @Column(name = "current_reading")
    private Integer currentReading;

    @Column(name = "used")
    private Integer used;

    @Column(name = "total_cost")
    private Float totalCost;

    @Column(name = "payment_status")
    private Integer paymentStatus; //0: chua thanh toan, 1: da thanh toan

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
