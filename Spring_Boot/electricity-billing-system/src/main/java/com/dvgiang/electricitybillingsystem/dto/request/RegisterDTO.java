package com.dvgiang.electricitybillingsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
  private String fullName;
  private String email;
  private String phone;
  private String address;
  private String username;
  private String password;
}
