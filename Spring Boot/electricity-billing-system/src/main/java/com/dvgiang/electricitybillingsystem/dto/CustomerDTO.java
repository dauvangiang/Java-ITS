package com.dvgiang.electricitybillingsystem.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class CustomerDTO {
    @NotEmpty(message = "name is required")
    private String name;
    @NotEmpty(message = "phone number is required")
    private String phone;
    @NotEmpty(message = "address is required")
    private String address;

    public @NotEmpty(message = "name is required") String getName() {
        return name;
    }

    public @NotEmpty(message = "phone number is required") String getPhone() {
        return phone;
    }

    public @NotEmpty(message = "address is required") String getAddress() {
        return address;
    }
}
