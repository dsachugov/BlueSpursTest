package com.bluespurs.starterkit.controller;

import com.bluespurs.starterkit.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Category(UnitTest.class)
public class PriceMatchingControllerUnitTest extends UnitTest {
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        super.setUp();
        PriceMatchingController priceMatchingController = new PriceMatchingController();
        ReflectionTestUtils.setField(priceMatchingController, "bestBuyApiKey", "pfe9fpy68yg28hvvma49sc89");
        ReflectionTestUtils.setField(priceMatchingController, "walmartApiKey", "rm25tyum3p9jm9x9x7zxshfa");
        mockMvc = MockMvcBuilders.standaloneSetup(priceMatchingController).build();
    }

    /**
     * Test the price matching page.
     *
     * @see PriceMatchingController#selectBestPrice(String)
     */
    @Test
    public void testPriceMatchingPage() throws Exception {
        mockMvc.perform(get("/product/search?name=ipad"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("bestPrice")))
            .andExpect(content().string(anyOf(containsString("Walmart"), containsString("Best Buy"))));
    }

    @Test
    public void testPriceMatchingPageNonExistingProduct() throws Exception {
        mockMvc.perform(get("/product/search?name=sddcwfx"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("doesn't contain products")));
    }
}
