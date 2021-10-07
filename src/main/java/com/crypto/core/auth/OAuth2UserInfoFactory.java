package com.crypto.core.auth;

import com.crypto.core.auth.enums.AuthProvider;
import com.crypto.core.auth.exceptions.OAuth2AuthenticationProcessingException;
import com.crypto.core.auth.facebook.FacebookOAuth2UserInfo;
import com.crypto.core.auth.github.GithubOAuth2UserInfo;
import com.crypto.core.auth.google.GoogleOAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(AuthProvider.GOOGLE.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.FACEBOOK.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.GITHUB.toString())) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
