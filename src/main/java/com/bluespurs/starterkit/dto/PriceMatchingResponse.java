package com.bluespurs.starterkit.dto;

/**
 * Created by dmytr on 2017-04-13.
 */
public class PriceMatchingResponse {
    private double bestPrice;
    private String productName;
    private String currency;
    private String store;

    public PriceMatchingResponse() {
    }

    public PriceMatchingResponse(BestBuyProduct product) {
        this.bestPrice = product.getSalePrice();
        this.productName = product.getName();
        this.currency = "CAD";
        this.store = "Best Buy";
    }

    public PriceMatchingResponse(WalmartProduct product) {
        this.bestPrice = product.getSalePrice();
        this.productName = product.getName();
        this.currency = "CAD";
        this.store = "Walmart";
    }

    public double getBestPrice() {
        return bestPrice;
    }

    public void setBestPrice(double bestPrice) {
        this.bestPrice = bestPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency() {
        this.currency = currency;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
