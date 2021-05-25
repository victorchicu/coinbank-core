package com.cryptostrophe.bot.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "binance")
public class BinanceProperties {
    private String url;
    private String apiKey;
    private String secretKey;
    private Map<String, Cryptocurrency> cryptocurrency;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Map<String, Cryptocurrency> getCryptocurrency() {
        return cryptocurrency;
    }

    public void setCryptocurrency(Map<String, Cryptocurrency> cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
    }
}
