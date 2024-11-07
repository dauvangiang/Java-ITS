package com.dvgiang.electricitybillingsystem.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    @NotEmpty(message = "Full name is required!")
    private String fullName;

    @NotEmpty(message = "Email is required!")
    private String email;

    @NotEmpty(message = "Phone number is required!")
    private String phone;

    @NotEmpty(message = "Address is required!")
    private String address;

    @NotEmpty(message = "Username is required!")
    private String username;

    @NotEmpty(message = "Password is required!")
    private String password;

    private Date updatedAt = new Date();
}
