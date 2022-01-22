package com.trader.core.services.impl;

import com.trader.core.clients.ApiWebSocketClient;
import com.trader.core.clients.ApiRestClient;
import com.trader.core.binance.domain.account.AssetBalance;
import com.trader.core.domain.User;
import com.trader.core.services.ExchangeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("GATE")
public class GateExchangeService implements ExchangeService {
    @Override
    public void createAssetTicker(User user, String assetName) {
        throw new UnsupportedOperationException("Gate not support create asset ticker");
    }

    @Override
    public void removeAssetTicker(String assetName) {
        throw new UnsupportedOperationException("Gate not support remove asset ticker");
    }

    @Override
    public ApiRestClient newApiRestClient(User user) {
        throw new UnsupportedOperationException("Gate not support api rest client");
    }

    @Override
    public ApiWebSocketClient newApiWebSocketClient(User user) {
        throw new UnsupportedOperationException("Gate not support api web socket client");
    }

    @Override
    public List<AssetBalance> listAssetBalances(User user) {
        throw new UnsupportedOperationException("Gate not support list asset balances");
    }

    @Override
    public Optional<AssetBalance> findAssetBalanceByName(User user, String assetName) {
        throw new UnsupportedOperationException("Gate not support find asset balance by name");
    }
}
