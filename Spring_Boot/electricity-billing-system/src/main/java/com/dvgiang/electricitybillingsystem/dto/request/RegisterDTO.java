package com.dvgiang.electricitybillingsystem.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Địa chỉ email không hợp lệ")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^0(?!0)\\d{9}$", message = "Số điện thoại là dãy 10 chữ số và bắt đầu bằng 0")
    private String phone;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min = 6, max = 36, message = "Tên đăng nhập tối thiểu 6 ký tự, tối đa 36 ký tự")
    private String username;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu tối thiểu 6 ký tự")
    @Pattern(
            regexp = "^(?=(.*\\d))(?=(.*[a-zA-Z]))(?=(.*[\\W_]))(?!.*(.)\1{3})[\\S]{6,}$",
            message = "Mật khẩu phải chứa chữ cái, số và cả ký tự đặc biệt"
    )
    private String password;

    private Date updatedAt = new Date();
}
