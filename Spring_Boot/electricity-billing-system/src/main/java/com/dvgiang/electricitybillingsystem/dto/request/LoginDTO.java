package com.dvgiang.electricitybillingsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String username;
    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
}
