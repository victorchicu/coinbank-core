package com.trader.core.binance.domain.event;

import com.trader.core.binance.domain.account.AssetBalance;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Custom deserializer for an AssetBalance, since the streaming API returns an object in the format {"a":"symbol","f":"free","l":"locked"},
 * which is different than the format used in the REST API.
 */
public class AssetBalanceDeserializer extends JsonDeserializer<AssetBalance> {

  @Override
  public AssetBalance deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
    ObjectCodec oc = jp.getCodec();
    JsonNode node = oc.readTree(jp);
    final String asset = node.get("a").asText();
    final String free = node.get("f").asText();
    final String frozen = node.get("l").asText();
    AssetBalance assetBalance = new AssetBalance();
    assetBalance.setAsset(asset);
    assetBalance.setFree(new BigDecimal(free));
    assetBalance.setLocked(new BigDecimal(frozen));
    return assetBalance;
  }
}
