package com.bluespurs.starterkit.dto;

import java.util.List;

/**
 * Created by dmytr on 2017-04-13.
 */
public class BestBuyResponse {
    private int from;
    private int to;
    private int currentPage;
    private int total;
    private List<BestBuyProduct> products;

    public BestBuyResponse() {
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BestBuyProduct> getProducts() {
        return products;
    }

    public void setProducts(List<BestBuyProduct> products) {
        this.products = products;
    }
}
