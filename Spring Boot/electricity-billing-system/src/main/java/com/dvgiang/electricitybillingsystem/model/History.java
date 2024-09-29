package com.dvgiang.electricitybillingsystem.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "reading_date")
    private Date readingDate;

    @Column(name = "billing_period")
    private String billingPeriod;

    @Column(name = "previous_reading")
    private Integer previousReading;

    @Column(name = "current_reading")
    private Integer currentReading;

    @Column(name = "used")
    private Integer used;

    @Column(name = "total_cost", scale = 3)
    private Float totalCost;

    //-1: chua den ngay thanh toan, 0: chua thanh toan, 1: da thanh toan
    @Column(name = "payment_status")
    private Integer paymentStatus = 0;

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(Date readingDate) {
        this.readingDate = readingDate;
    }

    public String getBillingPeriod() {
        return billingPeriod;
    }

    public void setBillingPeriod(String billingPeriod) {
        this.billingPeriod = billingPeriod;
    }

    public Integer getPreviousReading() {
        return previousReading;
    }

    public void setPreviousReading(Integer previousReading) {
        this.previousReading = previousReading;
    }

    public Integer getCurrentReading() {
        return currentReading;
    }

    public void setCurrentReading(Integer currentReading) {
        this.currentReading = currentReading;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
