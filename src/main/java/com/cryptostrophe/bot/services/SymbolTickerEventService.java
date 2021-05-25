package com.cryptostrophe.bot.services;

import com.cryptostrophe.bot.repository.model.SymbolTickerEvent;

import java.util.Optional;

public interface SymbolTickerEventService {
    void updateSymbolTickerEvent(Integer participantId, String symbol, Long eventTime);

    SymbolTickerEvent saveSymbolTickerEvent(SymbolTickerEvent symbolTickerEvent);

    Optional<SymbolTickerEvent> findSymbolTickerEvent(Integer participantId, String symbol);
}