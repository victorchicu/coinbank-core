package com.coinbank.core.configs;

import com.coinbank.core.domain.User;
import com.coinbank.core.services.ApiWebSocketClient;
import com.coinbank.core.services.ApiRestClient;
import com.coinbank.core.exceptions.ApiClientException;
import com.coinbank.core.services.ExchangeService;
import com.coinbank.core.services.UserService;
import com.coinbank.core.services.ExchangeStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class ApiRestClientConfig {
    private final UserService userService;
    private final ExchangeStrategy exchangeStrategy;

    public ApiRestClientConfig(
            UserService userService,
            ExchangeStrategy exchangeStrategy
    ) {
        this.userService = userService;
        this.exchangeStrategy = exchangeStrategy;
    }

    @Bean
    @SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ApiRestClient apiRestClient() {
        return userService.findById(SecurityContextHolder.getContext().getAuthentication().getName())
                .map((User user) -> {
                    ExchangeService exchangeService = exchangeStrategy.getExchangeService(
                            user.getCryptoExchanges().get("BINANCE").getProvider()
                    );
                    return exchangeService.newApiRestClient(user);
                })
                .orElseThrow(() -> new ApiClientException("Could not create rest client"));
    }

    @Bean
    @SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ApiWebSocketClient apiWebSocketClient() {
        return userService.findById(SecurityContextHolder.getContext().getAuthentication().getName())
                .map(user -> {
                    ExchangeService exchangeService = exchangeStrategy.getExchangeService(
                            user.getCryptoExchanges().get("BINANCE").getProvider()
                    );
                    return exchangeService.newApiWebSocketClient(user);
                })
                .orElseThrow(() -> new ApiClientException("Could not create web socket client"));
    }
}
