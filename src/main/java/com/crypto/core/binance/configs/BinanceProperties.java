package com.crypto.core.binance.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "binance")
public class BinanceProperties {
    private String url;
    private String apiKey;
    private String secretKey;
    private Map<String, CryptoMappings> cryptoMappings;

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

    public Map<String, CryptoMappings> getCryptoMappings() {
        return cryptoMappings;
    }

    public void setCryptoMappings(Map<String, CryptoMappings> cryptoMappings) {
        this.cryptoMappings = cryptoMappings;
    }
}
