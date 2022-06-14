package com.coinbank.core.domain.services.impl;

import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.TickerEvent;
import com.coinbank.core.domain.configs.BinanceConfig;
import com.coinbank.core.domain.configs.BinanceProperties;
import com.coinbank.core.domain.exceptions.IllegalAssetException;
import com.coinbank.core.domain.services.SocketExchangeClient;

import java.io.Closeable;
import java.util.Optional;
import java.util.function.Consumer;

public class BinanceSocketExchangeClient implements SocketExchangeClient {
    private final BinanceConfig binanceConfig;
    private final BinanceApiWebSocketClient binanceApiWebSocketClient;

    public BinanceSocketExchangeClient(BinanceConfig binanceConfig, BinanceApiWebSocketClient binanceApiWebSocketClient) {
        this.binanceConfig = binanceConfig;
        this.binanceApiWebSocketClient = binanceApiWebSocketClient;
    }

    @Override
    public Closeable onTickerEvent(String assetName, Consumer<TickerEvent> consumer) {
        String symbolName = getSymbolName(assetName);
        return binanceApiWebSocketClient.onTickerEvent(symbolName.toLowerCase(), consumer::accept);
    }


    private String getSymbolName(String assetName) {
        return Optional.ofNullable(binanceConfig.getProps().getAssets().get(assetName))
                .map(BinanceProperties.AssetConfig::getSymbol)
                .orElseThrow(() -> new IllegalAssetException(assetName));
    }
}