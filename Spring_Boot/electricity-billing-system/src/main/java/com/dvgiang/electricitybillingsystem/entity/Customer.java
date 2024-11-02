package com.dvgiang.electricitybillingsystem.entity;

import com.dvgiang.electricitybillingsystem.dto.request.CustomerDTO;
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
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "status")
    private Integer status; //0: hủy dịch vụ, 1: đang sử dụng dịch vụ

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = 1;
        }
    }

    public void updateFromDTO(CustomerDTO dto) {
        this.fullName = dto.getName();
        this.phone = dto.getPhone();
        this.address = dto.getAddress();
    }
}
