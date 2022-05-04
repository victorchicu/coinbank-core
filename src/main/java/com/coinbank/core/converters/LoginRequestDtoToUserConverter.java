package com.coinbank.core.converters;

import com.coinbank.core.domain.User;
import com.coinbank.core.dto.LoginRequestDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginRequestDtoToUserConverter implements Converter<LoginRequestDto, User> {
    private final PasswordEncoder passwordEncoder;

    public LoginRequestDtoToUserConverter(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User convert(LoginRequestDto source) {
        return User.newBuilder()
                .email(source.getEmail())
                .password(passwordEncoder.encode(source.getPassword()))
                .build();
    }
}