package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.request.LoginDTO;
import com.dvgiang.electricitybillingsystem.dto.request.RegisterDTO;
import com.dvgiang.electricitybillingsystem.dto.response.BaseResponse;
import com.dvgiang.electricitybillingsystem.service.JwtService;
import com.dvgiang.electricitybillingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDTO registerDTO) {
//    return ResponseEntity.(userService.creatNewUser(registerDTO));
        return new ResponseEntity<>(
                BaseResponse.ok(userService.creatNewUser(registerDTO)),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(
                BaseResponse.ok(userService.authentication(loginDTO))
        );
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
        jwtService.revokeToken(authHeader);
        return ResponseEntity.ok("Logout Successfully");
    }

    @PostMapping("/permissions")
    public ResponseEntity<Object> getPermission() {
        return ResponseEntity.ok(userService.getPermissions());
    }
}
