package com.crypto.core.binance.subscriptions.repository.converters;

import com.crypto.core.binance.subscriptions.domain.Subscription;
import com.crypto.core.binance.subscriptions.entity.SubscriptionEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntityToSubscriptionConverter implements Converter<SubscriptionEntity, Subscription> {
    @Override
    public Subscription convert(SubscriptionEntity source) {
        return null;
    }
}