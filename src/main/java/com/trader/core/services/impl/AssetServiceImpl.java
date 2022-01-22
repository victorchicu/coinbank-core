package com.trader.core.services.impl;

import com.trader.core.domain.User;
import com.trader.core.services.AssetService;
import com.trader.core.binance.domain.account.AssetBalance;
import com.trader.core.enums.ExchangeProvider;
import com.trader.core.services.ExchangeProviderService;
import com.trader.core.strategy.ExchangeProviderServiceStrategy;
import com.trader.core.domain.Subscription;
import com.trader.core.services.SubscriptionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {
    private final SubscriptionService subscriptionService;
    private final ExchangeProviderServiceStrategy exchangeProviderServiceStrategy;

    public AssetServiceImpl(
            SubscriptionService subscriptionService,
            ExchangeProviderServiceStrategy exchangeProviderServiceStrategy
    ) {
        this.subscriptionService = subscriptionService;
        this.exchangeProviderServiceStrategy = exchangeProviderServiceStrategy;
    }

    @Override
    public void addAssetTickerEvent(User user, String assetName) {
        ExchangeProviderService provider = exchangeProviderServiceStrategy.getExchangeProvider(
                ExchangeProvider.BINANCE
        );
        provider.createAssetTicker(user, assetName);
    }


    @Override
    public void removeAssetTickerEvent(String assetName) {
        ExchangeProviderService provider = exchangeProviderServiceStrategy.getExchangeProvider(
                ExchangeProvider.BINANCE
        );
        provider.removeAssetTicker(assetName);
    }

    @Override
    public List<AssetBalance> listAssetBalances(User user) {
        ExchangeProviderService provider = exchangeProviderServiceStrategy.getExchangeProvider(
                user.getExchangeProvider()
        );
        Page<Subscription> page = subscriptionService.findSubscriptions(
                user,
                Pageable.unpaged()
        );
        List<AssetBalance> assetBalances = provider.listAssetBalances(user);
        List<Subscription> subscriptions = page.getContent();
        if (!subscriptions.isEmpty()) {
            assetBalances.forEach(assetBalance -> {
                assetBalance.setFlagged(
                        subscriptions.stream()
                                .anyMatch(subscription ->
                                        subscription.getAssetName().equals(assetBalance.getAsset())
                                )
                );
                if (assetBalance.getFlagged()) {
                    removeAssetTickerEvent(assetBalance.getAsset());
                    addAssetTickerEvent(user, assetBalance.getAsset());
                }
            });
        }
        return assetBalances;
    }
}
