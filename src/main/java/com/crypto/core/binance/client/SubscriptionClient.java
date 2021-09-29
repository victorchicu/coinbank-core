package com.crypto.core.binance.client;

import java.util.List;

import com.crypto.core.binance.client.impl.BinanceApiInternalFactory;
import com.crypto.core.binance.client.domain.enums.CandlestickInterval;
import com.crypto.core.binance.client.domain.event.AggregateTradeEvent;
import com.crypto.core.binance.client.domain.event.CandlestickEvent;
import com.crypto.core.binance.client.domain.event.LiquidationOrderEvent;
import com.crypto.core.binance.client.domain.event.MarkPriceEvent;
import com.crypto.core.binance.client.domain.event.OrderBookEvent;
import com.crypto.core.binance.client.domain.event.SymbolBookTickerEvent;
import com.crypto.core.binance.client.domain.event.SymbolMiniTickerEvent;
import com.crypto.core.binance.client.domain.event.SymbolTickerEvent;
import com.crypto.core.binance.client.domain.user.UserDataUpdateEvent;

/***
 * The subscription client interface, it is used for subscribing any market data
 * update and account change, it is asynchronous, so you must implement the
 * SubscriptionListener interface. The server will push any update to the
 * client. if client get the update, the onReceive method will be called.
 */
public interface SubscriptionClient {
    /**
     * Create the subscription client to subscribe the update from server.
     *
     * @return The instance of synchronous client.
     */
    static SubscriptionClient create() {
        return create(new SubscriptionOptions());
    }

    /**
     * Create the subscription client to subscribe the update from server.
     *
     * @param subscriptionOptions The option of subscription connection, see
     *                            {@link SubscriptionOptions}
     * @return The instance of synchronous client.
     */
    static SubscriptionClient create(SubscriptionOptions subscriptionOptions) {
        return BinanceApiInternalFactory.getInstance().createSubscriptionClient(subscriptionOptions);
    }

    /**
     * Unsubscribe specific socket
     *
     * @param userId
     * @param symbolName
     */
    void unsubscribe(String userId, String symbolName);

    /**
     * Unsubscribe all subscription.
     */
    void unsubscribeAll();

    /**
     * Subscribe aggregate trade event. If the aggregate trade is updated,
     * server will send the data to client and onReceive in callback will be called.
     *
     * @param userId
     * @param symbolName
     * @param callback     The implementation is required. onReceive will be called
     *                     if receive server's update.
     * @param errorHandler The error handler will be called if subscription failed
     */
    void subscribeAggregateTradeEvent(
            String userId,
            String symbolName,
            SubscriptionListener<AggregateTradeEvent> callback,
            SubscriptionErrorHandler errorHandler
    );

    /**
     * Subscribe mark price event. If the mark price is updated,
     * server will send the data to client and onReceive in callback will be called.
     *
     * @param userId
     * @param symbolName
     * @param callback     The implementation is required. onReceive will be called
     *                     if receive server's update.
     * @param errorHandler The error handler will be called if subscription failed
     */
    void subscribeMarkPriceEvent(
            String userId,
            String symbolName,
            SubscriptionListener<MarkPriceEvent> callback,
            SubscriptionErrorHandler errorHandler
    );

    /**
     * Subscribe candlestick event. If the candlestick is updated,
     * server will send the data to client and onReceive in callback will be called.
     *
     * @param userId
     * @param symbolName
     * @param interval     The candlestick interval, like "ONE_MINUTE".
     * @param callback     The implementation is required. onReceive will be called
     *                     if receive server's update.
     * @param errorHandler The error handler will be called if subscription failed
     */
    void subscribeCandlestickEvent(
            String userId,
            String symbolName,
            CandlestickInterval interval,
            SubscriptionListener<CandlestickEvent> callback,
            SubscriptionErrorHandler errorHandler
    );

    /**
     * Subscribe individual symbol mini ticker event. If the symbol mini ticker is updated,
     * server will send the data to client and onReceive in callback will be called.
     *
     * @param userId
     * @param symbolName
     * @param callback     The implementation is required. onReceive will be called
     *                     if receive server's update.
     * @param errorHandler The error handler will be called if subscription failed
     */
    void subscribeSymbolMiniTickerEvent(
            String userId,
            String symbolName,
            SubscriptionListener<SymbolMiniTickerEvent> callback,
            SubscriptionErrorHandler errorHandler
    );

    /**
     * Subscribe all market mini tickers event. If the mini tickers are updated,
     * server will send the data to client and onReceive in callback will be called.
     *
     * @param userId
     * @param symbolName
     * @param callback     The implementation is required. onReceive will be called
     *                     if receive server's update.
     * @param errorHandler The error handler will be called if subscription failed
     */
    void subscribeAllMiniTickerEvent(
            String userId,
            String symbolName,
            SubscriptionListener<List<SymbolMiniTickerEvent>> callback,
            SubscriptionErrorHandler errorHandler
    );

    /**
     * Subscribe individual symbol ticker event. If the symbol ticker is updated,
     * server will send the data to client and onReceive in callback will be called.
     *
     * @param userId
     * @param symbolName   The symbol, like "btcusdt".
     * @param callback     The implementation is required. onReceive will be called
     *                     if receive server's update.
     * @param errorHandler The error handler will be called if subscription failed
     */
    void subscribeSymbolTickerEvent(
            String userId,
            String symbolName,
            SubscriptionListener<SymbolTickerEvent> callback,
            SubscriptionErrorHandler errorHandler
    );

    /**
     * Subscribe all market tickers event. If the tickers are updated,
     * server will send the data to client and onReceive in callback will be called.
     *
     * @param userId
     * @param callback     The implementation is required. onReceive will be called
     *                     if receive server's update.
     * @param errorHandler The error handler will be called if subscription failed
     */
    void subscribeAllTickerEvent(
            String userId,
            SubscriptionListener<List<SymbolTickerEvent>> callback,
            SubscriptionErrorHandler errorHandler
    );

    /**
     * Subscribe individual symbol book ticker event. If the symbol book ticker is updated,
     * server will send the data to client and onReceive in callback will be called.
     *
     * @param userId
     * @param symbolName
     * @param callback     The implementation is required. onReceive will be called
     *                     if receive server's update.
     * @param errorHandler The error handler will be called if subscription failed
     */
    void subscribeSymbolBookTickerEvent(
            String userId,
            String symbolName,
            SubscriptionListener<SymbolBookTickerEvent> callback,
            SubscriptionErrorHandler errorHandler
    );

    /**
     * Subscribe all market book tickers event. If the book tickers are updated,
     * server will send the data to client and onReceive in callback will be called.
     *
     * @param userId
     * @param callback     The implementation is required. onReceive will be called
     *                     if receive server's update.
     * @param errorHandler The error handler will be called if subscription failed
     */
    void subscribeAllBookTickerEvent(
            String userId,
            SubscriptionListener<SymbolBookTickerEvent> callback,
            SubscriptionErrorHandler errorHandler
    );

    /**
     * Subscribe individual symbol book ticker event. If the symbol book ticker is updated,
     * server will send the data to client and onReceive in callback will be called.
     *
     * @param userId
     * @param symbolName
     * @param callback     The implementation is required. onReceive will be called
     *                     if receive server's update.
     * @param errorHandler The error handler will be called if subscription failed
     */
    void subscribeSymbolLiquidationOrderEvent(
            String userId,
            String symbolName,
            SubscriptionListener<LiquidationOrderEvent> callback,
            SubscriptionErrorHandler errorHandler
    );

    /**
     * Subscribe all market book tickers event. If the book tickers are updated,
     * server will send the data to client and onReceive in callback will be called.
     *
     * @param userId
     * @param callback     The implementation is required. onReceive will be called
     *                     if receive server's update.
     * @param errorHandler The error handler will be called if subscription failed
     */
    void subscribeAllLiquidationOrderEvent(
            String userId,
            SubscriptionListener<LiquidationOrderEvent> callback,
            SubscriptionErrorHandler errorHandler
    );

    /**
     * Subscribe partial book depth event. If the book depth is updated,
     * server will send the data to client and onReceive in callback will be called.
     *
     * @param userId
     * @param symbolName
     * @param limit        The limit.
     * @param callback     The implementation is required. onReceive will be called
     *                     if receive server's update.
     * @param errorHandler The error handler will be called if subscription failed
     */
    void subscribeBookDepthEvent(
            String userId,
            String symbolName,
            Integer limit,
            SubscriptionListener<OrderBookEvent> callback,
            SubscriptionErrorHandler errorHandler
    );

    /**
     * Subscribe diff depth event. If the book depth is updated,
     * server will send the data to client and onReceive in callback will be called.
     *
     * @param participantId
     * @param symbolName
     * @param callback      The implementation is required. onReceive will be called
     *                      if receive server's update.
     * @param errorHandler  The error handler will be called if subscription failed
     */
    void subscribeDiffDepthEvent(
            String participantId,
            String symbolName,
            SubscriptionListener<OrderBookEvent> callback,
            SubscriptionErrorHandler errorHandler);

    /**
     * Subscribe user data event. If the user data is updated,
     * server will send the data to client and onReceive in callback will be called.
     *
     * @param userId
     * @param listenKey    The listenKey.
     * @param callback     The implementation is required. onReceive will be called
     *                     if receive server's update.
     * @param errorHandler The error handler will be called if subscription failed
     */
    void subscribeUserDataEvent(
            String userId,
            String listenKey,
            SubscriptionListener<UserDataUpdateEvent> callback,
            SubscriptionErrorHandler errorHandler
    );
}
