package com.trader.core.assets.services;

import com.trader.core.binance.client.domain.account.AssetBalance;

import java.security.Principal;
import java.util.List;

public interface AssetService {
    void addAssetTickerEvent(Principal principal, String assetName);

    void removeAssetTickerEvent(String assetName);

    List<AssetBalance> listAssetBalances(Principal principal);
}
