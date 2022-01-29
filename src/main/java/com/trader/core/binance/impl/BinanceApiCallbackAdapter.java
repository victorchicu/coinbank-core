package com.trader.core.binance.impl;

import com.trader.core.binance.BinanceApiCallback;
import com.trader.core.binance.BinanceApiError;
import com.trader.core.binance.exception.BinanceApiException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

/**
 * An adapter/wrapper which transforms a Callback from Retrofit into a BinanceApiCallback which is exposed to the client.
 */
public class BinanceApiCallbackAdapter<T> implements Callback<T> {

  private final BinanceApiCallback<T> callback;

  public BinanceApiCallbackAdapter(BinanceApiCallback<T> callback) {
    this.callback = callback;
  }

  public void onResponse(Call<T> call, Response<T> response) {
    if (response.isSuccessful()) {
      callback.onResponse(response.body());
    } else {
      if (response.code() == 504) {
        // HTTP 504 return code is used when the API successfully sent the message but not get a response within the timeout period.
        // It is important to NOT treat this as a failure; the execution status is UNKNOWN and could have been a success.
        return;
      }
      try {
        BinanceApiError apiError = BinanceApiServiceGenerator.getBinanceApiError(response);
        onFailure(call, new BinanceApiException(apiError));
      } catch (IOException e) {
        onFailure(call, new BinanceApiException(e));
      }
    }
  }

  @Override
  public void onFailure(Call<T> call, Throwable throwable) {
    if (throwable instanceof BinanceApiException) {
      callback.onFailure(throwable);
    } else {
      callback.onFailure(new BinanceApiException(throwable));
    }
  }
}