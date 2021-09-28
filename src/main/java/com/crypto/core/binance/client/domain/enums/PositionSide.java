package com.crypto.core.binance.client.domain.enums;

/**
 * @author : wangwanlu
 * @since : 2020/3/25, Wed
 **/
public enum PositionSide {

    BOTH("BOTH"),

    SHORT("SHORT"),

    LONG("LONG"),
    ;

    private final String code;

    PositionSide(String side) {
        this.code = side;
    }

    @Override
    public String toString() {
        return code;
    }
}
