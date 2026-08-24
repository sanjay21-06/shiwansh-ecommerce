package com.shiwansh.ecommerce.presentation.controller;

import com.shiwansh.ecommerce.application.dto.user.UserLoginRequest;
import com.shiwansh.ecommerce.application.dto.user.UserRegisterRequest;
import com.shiwansh.ecommerce.application.dto.user.UserResponse;
import com.shiwansh.ecommerce.application.usecase.user.LoginUserUseCase;
import com.shiwansh.ecommerce.application.usecase.user.RegisterUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;

    public UserController(
            RegisterUserUseCase registerUserUseCase,
            LoginUserUseCase loginUserUseCase) {

        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @RequestBody UserRegisterRequest request) {

        UserResponse response =
                registerUserUseCase.execute(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(
            @RequestBody UserLoginRequest request) {

        UserResponse response =
                loginUserUseCase.execute(request);

        return ResponseEntity.ok(response);
    }
}