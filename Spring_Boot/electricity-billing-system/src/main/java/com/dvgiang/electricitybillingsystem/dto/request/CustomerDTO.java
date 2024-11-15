package com.dvgiang.electricitybillingsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    @NotNull(message = "Mã số không được để trống, đặt -1 trong trường hợp tạo mới")
    private Long id;

    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^0(?!0)\\d{9}$", message = "Số điện thoại là dãy 10 chữ số và bắt đầu bằng 0")
    private String phone;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;
}
