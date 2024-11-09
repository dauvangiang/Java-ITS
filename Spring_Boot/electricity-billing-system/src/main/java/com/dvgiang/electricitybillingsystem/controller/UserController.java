package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.request.LoginDTO;
import com.dvgiang.electricitybillingsystem.dto.request.UserDTO;
import com.dvgiang.electricitybillingsystem.dto.response.BaseResponse;
import com.dvgiang.electricitybillingsystem.service.JwtService;
import com.dvgiang.electricitybillingsystem.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(
                BaseResponse.ok(userService.createUser(userDTO)),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO loginDTO) {
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
