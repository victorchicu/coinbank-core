package com.crypto.core.logout.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LogoutService {
    void logout(HttpServletRequest request, HttpServletResponse response);
}