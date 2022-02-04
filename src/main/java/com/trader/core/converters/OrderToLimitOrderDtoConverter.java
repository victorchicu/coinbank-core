package com.trader.core.converters;

import com.binance.api.client.domain.account.Order;
import com.trader.core.dto.LimitOrderDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderToLimitOrderDtoConverter implements Converter<Order, LimitOrderDto> {
    @Override
    public LimitOrderDto convert(Order source) {
        return null;
    }
}
