package com.coinbank.core.entity;

import com.coinbank.core.enums.ExchangeProvider;
import com.coinbank.core.enums.OAuth2Provider;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@CompoundIndexes({@CompoundIndex(
        name = "email_exchange_idx",
        def = "{'email' : 1, 'exchange' : 1}",
        unique = true
)})
public class UserEntity extends BaseEntity {
    private String email;
    private String password;
    private String imageUrl;
    private String providerId;
    private String apiKey;
    private String secretKey;
    private OAuth2Provider auth2Provider;
    private ExchangeProvider exchangeProvider;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
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

    public OAuth2Provider getAuth2Provider() {
        return auth2Provider;
    }

    public void setAuth2Provider(OAuth2Provider auth2Provider) {
        this.auth2Provider = auth2Provider;
    }

    public ExchangeProvider getExchangeProvider() {
        return exchangeProvider;
    }

    public void setExchangeProvider(ExchangeProvider exchangeProvider) {
        this.exchangeProvider = exchangeProvider;
    }

    public static final class Field {
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String IMAGE_URL = "imageUrl";
        public static final String PROVIDER = "provider";
        public static final String PROVIDER_ID = "providerId";
        public static final String API_KEY = "apiKey";
        public static final String SECRET_KEY = "secretKey";
        public static final String EXCHANGE_PROVIDER = "exchangeProvider";
    }
}