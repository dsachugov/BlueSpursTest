package com.bluespurs.starterkit.controller;

import com.bluespurs.starterkit.dto.*;
import com.bluespurs.starterkit.dto.Error;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dmytr on 2017-04-13.
 */
@RestController
public class PriceMatchingController {
    public static final Logger log = LoggerFactory.getLogger(PriceMatchingController.class);
    @Value("${bestbuy.key}")
    private String bestBuyApiKey;
    @Value("${walmart.key}")
    private String walmartApiKey;

    @RequestMapping("/product/search")
    public String selectBestPrice(@RequestParam(value = "name") String productName) {
        log.info("Visiting price matching page for product={}", productName);
        String bestBuyUrl = buildBestBuyUrl(productName);
        String walmartUrl = buildWalmartUrl(productName);
        RestTemplate restTemplate = new RestTemplate();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        ResponseEntity<String> bestBuyResponse = restTemplate.getForEntity(bestBuyUrl, String.class);
        ResponseEntity<String> walmartResponse = restTemplate.getForEntity(walmartUrl, String.class);
        //http status check
        if (bestBuyResponse.getStatusCode() != HttpStatus.OK) {
            return gson.toJson(new Error("Best buy response has error " + bestBuyResponse.getStatusCode().name()));
        }
        if (walmartResponse.getStatusCode() != HttpStatus.OK) {
            return gson.toJson(new Error("Walmart response has error " + bestBuyResponse.getStatusCode().name()));
        }
        //receiving API response via RestTemplate
        BestBuyResponse bestBuyProducts = gson.fromJson(bestBuyResponse.getBody(), BestBuyResponse.class);
        WalmartResponse walmartProducts = gson.fromJson(walmartResponse.getBody(), WalmartResponse.class);
        //checking for response
        if (bestBuyProducts == null || bestBuyProducts.getProducts() == null) {
            return gson.toJson(new Error("Best buy response doesn't contain products"));
        }
        if (walmartProducts == null || walmartProducts.getItems() == null) {
            return gson.toJson(new Error("Walmart response doesn't contain products"));
        }
        BestBuyProduct bestBuyCheapestProduct = bestBuyProducts.getProducts().get(0);
        WalmartProduct walmartCheapestProduct = walmartProducts.getItems().get(0);
        if (bestBuyCheapestProduct.getSalePrice() < walmartCheapestProduct.getSalePrice()) {
            return gson.toJson(new PriceMatchingResponse(bestBuyCheapestProduct));
        } else {
            return gson.toJson(new PriceMatchingResponse(walmartCheapestProduct));
        }
    }

    private String buildWalmartUrl(String productName) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("http://api.walmartlabs.com/v1/search")
                .append("?apiKey=").append(walmartApiKey)
                .append("&query=").append(productName).append("&sort=price&order=asc");
        return urlBuilder.toString();
    }

    private String buildBestBuyUrl(String productName) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("https://api.bestbuy.com/v1/")
                .append("products(name=").append(productName).append("*)?apiKey=").append(bestBuyApiKey)
                .append("&sort=salePrice.asc&show=salePrice,name&format=json");
        return urlBuilder.toString();
    }
}
