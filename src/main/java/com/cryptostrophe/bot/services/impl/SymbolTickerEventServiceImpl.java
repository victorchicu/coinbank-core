package com.cryptostrophe.bot.services.impl;

import com.cryptostrophe.bot.repository.SymbolTickerEventRepository;
import com.cryptostrophe.bot.repository.model.SymbolTickerEvent;
import com.cryptostrophe.bot.services.SymbolTickerEventService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SymbolTickerEventServiceImpl implements SymbolTickerEventService {
    private final SymbolTickerEventRepository symbolTickerEventRepository;

    public SymbolTickerEventServiceImpl(SymbolTickerEventRepository symbolTickerEventRepository) {
        this.symbolTickerEventRepository = symbolTickerEventRepository;
    }

    @Override
    public void updateSymbolTickerEvent(Integer participantId, Long eventTime) {
        symbolTickerEventRepository.findById(participantId)
                .map(participantEvent -> participantEvent.setEventTime(eventTime))
                .ifPresent(symbolTickerEventRepository::save);
    }

    @Override
    public Optional<SymbolTickerEvent> findSymbolTickerEvent(Integer participantId, String symbol) {
        return symbolTickerEventRepository.findById(participantId);
    }
}
