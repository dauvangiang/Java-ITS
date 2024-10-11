package com.dvgiang.electricitybillingsystem.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    @NotNull(message = "id is required")
    private Long id;

    @NotEmpty(message = "name is required")
    private String name;

    @NotEmpty(message = "phone number is required")
    private String phone;

    @NotEmpty(message = "address is required")
    private String address;
}
