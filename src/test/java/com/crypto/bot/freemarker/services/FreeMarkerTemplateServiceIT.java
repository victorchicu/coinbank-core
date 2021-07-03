package com.crypto.bot.freemarker.services;

import com.crypto.bot.BaseTest;
import com.crypto.bot.binance.client.domain.event.SymbolMiniTickerEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FreeMarkerTemplateServiceIT extends BaseTest {
    static String SYMBOL_FILE_NAME = "1000shibusdt.ftl";

    @Test
    public void should_render_template() {
        SymbolMiniTickerEvent symbolMiniTickerEvent = randomSymbolMiniTickerEvent();
        String template = freeMarkerTemplateService.render(SYMBOL_FILE_NAME, symbolMiniTickerEvent);
        Assertions.assertNotNull(template);
        Assertions.assertFalse(template.isEmpty());
    }
}
