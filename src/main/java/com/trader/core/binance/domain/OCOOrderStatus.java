package com.trader.core.binance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum OCOOrderStatus {
    EXECUTING,
    ALL_DONE,
    REJECT
}