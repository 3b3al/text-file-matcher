package com.textfilematcher.shared;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.textfilematcher.dtos.LoginUserDto;
import com.textfilematcher.dtos.RegisterUserDto;
import com.textfilematcher.model.User;
import com.textfilematcher.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public User signup(RegisterUserDto input) {
        
        User user = new User().builder()
                .firstName(input.getFirstName())
                .userName(input.getUserName())
                .lastName(input.getLastName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .build();

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUserName(),
                        input.getPassword()
                )
        );

        return userRepository.findByUserName(input.getUserName())
                .orElseThrow();
    }
}
