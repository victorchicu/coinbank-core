package com.coinbank.core.services;

import com.coinbank.core.domain.User;
import com.coinbank.core.enums.NotificationType;

public interface NotificationTemplate {
    <T> void sendNotification(User user, NotificationType type, T payload);
}
