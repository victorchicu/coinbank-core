package com.crypto.bot.repository;

import com.crypto.bot.BaseTest;
import com.crypto.bot.repository.model.SymbolTickerEventEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class SymbolTickerEventRepositoryIT extends BaseTest {
    @Test
    public void should_save_and_find_symbol_ticker_event() {
        SymbolTickerEventEntity expectedSymbolTickerEvent = symbolTickerEventRepository.save(randomSymbolTickerEvent());

        Optional<SymbolTickerEventEntity> actualSymbolTickerEvent = symbolTickerEventRepository.findSymbolTickerEvent(
                expectedSymbolTickerEvent.getParticipantId(),
                expectedSymbolTickerEvent.getSymbol()
        );

        Assertions.assertEquals(expectedSymbolTickerEvent.getId(), actualSymbolTickerEvent.get().getId());
    }
}