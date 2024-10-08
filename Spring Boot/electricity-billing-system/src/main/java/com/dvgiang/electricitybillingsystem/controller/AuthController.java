package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.request.LoginDTO;
import com.dvgiang.electricitybillingsystem.dto.request.RegisterDTO;
import com.dvgiang.electricitybillingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody RegisterDTO registerDTO) {
    return ResponseEntity.ok(userService.creatNewUser(registerDTO));
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) {
    return ResponseEntity.ok(userService.authentication(loginDTO));
  }
}
