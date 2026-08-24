package com.shiwansh.ecommerce.application.usecase.user;

import com.shiwansh.ecommerce.application.dto.user.UserRegisterRequest;
import com.shiwansh.ecommerce.application.dto.user.UserResponse;
import com.shiwansh.ecommerce.domain.model.User;
import com.shiwansh.ecommerce.domain.model.UserRole;
import com.shiwansh.ecommerce.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse execute(UserRegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        String encodedPassword =
                passwordEncoder.encode(request.getPassword());

        User user = new User(
                null,
                request.getName(),
                request.getEmail(),
                encodedPassword,
                UserRole.CUSTOMER,
                true
        );

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.isActive()
        );
    }
}