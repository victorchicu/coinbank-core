package com.coinbank.core.domain.services.impl;

import com.coinbank.core.domain.ApiKey;
import com.coinbank.core.domain.User;
import com.coinbank.core.domain.services.ApiConnectionService;
import com.coinbank.core.domain.services.ExchangeProvider;
import com.coinbank.core.domain.services.UserService;
import com.coinbank.core.domain.exceptions.UserNotFoundException;
import com.coinbank.core.domain.services.ExchangeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.security.Principal;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApiConnectionServiceImpl implements ApiConnectionService {
    private final UserService userService;
    private final ExchangeProvider exchangeProvider;
    private final Map<String, ExchangeService> exchanges;

    private ApiConnectionServiceImpl(UserService userService, ExchangeProvider exchangeProvider, @Lazy Map<String, ExchangeService> exchanges) {
        this.userService = userService;
        this.exchangeProvider = exchangeProvider;
        this.exchanges = exchanges;
    }

    @Override
    public void create(Principal principal, ApiKey apiKey) {
        userService.findById(principal.getName())
                .ifPresentOrElse(
                        user -> {
                            user.addApiKey(apiKey);
                            userService.save(user);
                            ExchangeService exchangeService = exchangeProvider.get(apiKey);
                            exchanges.put(apiKey.getLabel(), exchangeService);
                        },
                        () -> {
                            throw new UserNotFoundException();
                        });
    }

    @Override
    public void delete(Principal principal, String label) {
        userService.findById(principal.getName())
                .ifPresentOrElse(
                        user -> {
                            user.deleteApiKey(label);
                            userService.save(user);
                            exchanges.remove(label);
                        },
                        () -> {
                            throw new UserNotFoundException();
                        });
    }

    @Bean
    @SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Map<String, ExchangeService> exchanges() {
        return userService.findById(SecurityContextHolder.getContext().getAuthentication().getName())
                .map((User user) ->
                        user.getApiKeys().entrySet().stream()
                                .collect(
                                        Collectors.toMap(
                                                Map.Entry::getKey,
                                                entry -> exchangeProvider.get(entry.getValue())
                                        )
                                ))
                .orElseThrow(UserNotFoundException::new);
    }
}
