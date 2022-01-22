package com.trader.core.controllers;

import com.trader.core.domain.User;
import com.trader.core.dto.AccessTokenDto;
import com.trader.core.dto.SignupRequestDto;
import com.trader.core.enums.ExchangeProvider;
import com.trader.core.exceptions.EmailNotFoundException;
import com.trader.core.services.TokenProviderService;
import com.trader.core.services.UserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signup")
public class SignupController {
    private final UserService userService;
    private final ConversionService conversionService;
    private final TokenProviderService tokenProviderService;
    private final AuthenticationManager authenticationManager;

    public SignupController(
            UserService userService,
            ConversionService conversionService,
            TokenProviderService tokenProviderService,
            AuthenticationManager authenticationManager
    ) {
        this.userService = userService;
        this.conversionService = conversionService;
        this.tokenProviderService = tokenProviderService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public AccessTokenDto signup(@RequestBody SignupRequestDto signupRequestDto) {
        String email = signupRequestDto.getEmail();
        ExchangeProvider provider = signupRequestDto.getExchangeProvider();
        if (userService.findByEmailAndExchangeProvider(email, provider).isPresent()) {
            throw new EmailNotFoundException();
        }
        User user = toUser(signupRequestDto);
        user = userService.save(user);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        signupRequestDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = tokenProviderService.createToken(authentication);
        return new AccessTokenDto(accessToken);
    }

    private User toUser(SignupRequestDto signupRequestDto) {
        return conversionService.convert(signupRequestDto, User.class);
    }
}
