package com.trader.core.binance.domain.general;

import com.trader.core.binance.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Rate limits.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateLimit {

  private RateLimitType rateLimitType;

  private RateLimitInterval interval;

  private Integer limit;

  public RateLimitType getRateLimitType() {
    return rateLimitType;
  }

  public void setRateLimitType(RateLimitType rateLimitType) {
    this.rateLimitType = rateLimitType;
  }

  public RateLimitInterval getInterval() {
    return interval;
  }

  public void setInterval(RateLimitInterval interval) {
    this.interval = interval;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
        .append("rateLimitType", rateLimitType)
        .append("interval", interval)
        .append("limit", limit)
        .toString();
  }
}