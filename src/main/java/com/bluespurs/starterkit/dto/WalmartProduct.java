package com.bluespurs.starterkit.dto;

/**
 * Created by dmytr on 2017-04-13.
 */
public class WalmartProduct {
    private double salePrice;
    private String name;

    public WalmartProduct() {
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
