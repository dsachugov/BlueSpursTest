package com.bluespurs.starterkit.dto;

import java.util.List;

/**
 * Created by dmytr on 2017-04-13.
 */
public class WalmartResponse {
    private List<WalmartProduct> items;

    public WalmartResponse() {
    }

    public List<WalmartProduct> getItems() {
        return items;
    }

    public void setItems(List<WalmartProduct> items) {
        this.items = items;
    }
}
