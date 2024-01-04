package com.messias.schedulingapi.controllers;

import com.messias.schedulingapi.config.SecurityConfig;
import com.messias.schedulingapi.services.AuthServices;
import com.messias.schedulingapi.vo.security.AccountCredentialsVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final AuthServices authServices;

    private final SecurityConfig securityConfig;

    public AuthController(AuthServices authServices, SecurityConfig securityConfig) {
        this.authServices = authServices;
        this.securityConfig = securityConfig;
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AccountCredentialsVO data) {
        if (checkIfParamsIsNotNull(data)) {
            return ResponseEntity.ok().body("caiu aqui");
        }
        var token = authServices.signin(data);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Request");
        }

        return token;


    }

    private boolean checkIfParamsIsNotNull(AccountCredentialsVO data) {
        return data == null || data.getUsername() == null || data.getUsername().isBlank() ||
                data.getPassword() == null || data.getPassword().isBlank();
    }

    @GetMapping("/{password}")
    public ResponseEntity<String> generatePassword(@PathVariable String password) {
        String passw = securityConfig.passwordEncoder().encode(password);
        return ResponseEntity.ok().body(passw);
    }
}
