package com.dvgiang.electricitybillingsystem.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    @NotNull(message = "id is required")
    private Long id;

    @NotEmpty(message = "fullname is required")
    private String fullName;

    @NotEmpty(message = "phone number is required")
    private String phone;

    @NotEmpty(message = "address is required")
    private String address;

    private Integer status = 1;

    private Date updatedAt = new Date();
}
