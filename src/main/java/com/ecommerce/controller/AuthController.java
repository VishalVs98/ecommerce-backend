package com.ecommerce.controller;

import com.ecommerce.dto.*;
import com.ecommerce.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager
            authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping(value = "/login",consumes = "application/json")
    public ResponseEntity<AuthResponseDTO>
    login(
            @RequestBody
            LoginRequestDTO dto){

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        dto.getEmail(),
                        dto.getPassword()
                )
        );
        String token =
                jwtUtil.generateToken(
                        dto.getEmail());

        return ResponseEntity.ok(
                new AuthResponseDTO(token));
    }
}