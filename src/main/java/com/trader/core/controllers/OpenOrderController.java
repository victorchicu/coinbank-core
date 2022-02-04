package com.trader.core.controllers;

import com.binance.api.client.domain.account.Order;
import com.trader.core.dto.OrderDto;
import com.trader.core.services.OrderService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/orders/open")
public class OpenOrderController {
    private final OrderService orderService;
    private final ConversionService conversionService;

    public OpenOrderController(OrderService orderService, ConversionService conversionService) {
        this.orderService = orderService;
        this.conversionService = conversionService;
    }

    @GetMapping("/{fundsName}")
    public Page<OrderDto> openOrders(Principal principal, @PathVariable String fundsName, Pageable pageable) {
        return orderService.openOrder(principal, fundsName, pageable)
                .map(this::toOrderDto);
    }


    private OrderDto toOrderDto(Order order) {
        return conversionService.convert(order, OrderDto.class);
    }
}
