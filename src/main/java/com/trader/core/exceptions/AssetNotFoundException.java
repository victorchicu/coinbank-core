package com.trader.core.exceptions;

public class AssetNotFoundException extends RuntimeException {
    public AssetNotFoundException(String assetName) {
        super("Asset " + assetName + " not supported");
    }
}
