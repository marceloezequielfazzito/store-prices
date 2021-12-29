package com.store.prices.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.prices.controller.response.PriceResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAt14062020T100000() throws Exception {

        MvcResult result = mockMvc.perform(get("/price/35455?brandId=1&date=14-06-2020T10:00:00")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        PriceResponse priceResponse = objectMapper.readValue(responseBody, PriceResponse.class);

        assertEquals(35455L,priceResponse.getProductId());
        assertEquals(1L,priceResponse.getBrandId());
        assertEquals(1L,priceResponse.getPriceList());
        assertEquals("2020-06-14T00:00",priceResponse.getStart().toString());
        assertEquals("2020-12-31T23:59:59",priceResponse.getEnd().toString());
        assertEquals(35.5,priceResponse.getPrice());
        assertEquals("EUR",priceResponse.getCurrency());
    }

    @Test
    public void testGetAt14062020T160000() throws Exception {

        MvcResult result = mockMvc.perform(get("/price/35455?brandId=1&date=14-06-2020T16:00:00")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        PriceResponse priceResponse = objectMapper.readValue(responseBody, PriceResponse.class);

        assertEquals(35455L,priceResponse.getProductId());
        assertEquals(1L,priceResponse.getBrandId());
        assertEquals(2L,priceResponse.getPriceList());
        assertEquals("2020-06-14T15:00",priceResponse.getStart().toString());
        assertEquals("2020-06-14T18:30",priceResponse.getEnd().toString());
        assertEquals(25.45,priceResponse.getPrice());
        assertEquals("EUR",priceResponse.getCurrency());
    }

    @Test
    public void testGetAt14062020T210000() throws Exception {

        MvcResult result = mockMvc.perform(get("/price/35455?brandId=1&date=14-06-2020T21:00:00")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        PriceResponse priceResponse = objectMapper.readValue(responseBody, PriceResponse.class);

        assertEquals(35455L,priceResponse.getProductId());
        assertEquals(1L,priceResponse.getBrandId());
        assertEquals(1L,priceResponse.getPriceList());
        assertEquals("2020-06-14T00:00",priceResponse.getStart().toString());
        assertEquals("2020-12-31T23:59:59",priceResponse.getEnd().toString());
        assertEquals(35.5,priceResponse.getPrice());
        assertEquals("EUR",priceResponse.getCurrency());
    }

    @Test
    public void testGetAt15062020T100000() throws Exception {

        MvcResult result = mockMvc.perform(get("/price/35455?brandId=1&date=15-06-2020T10:00:00")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        PriceResponse priceResponse = objectMapper.readValue(responseBody, PriceResponse.class);

        assertEquals(35455L,priceResponse.getProductId());
        assertEquals(1L,priceResponse.getBrandId());
        assertEquals(3L,priceResponse.getPriceList());
        assertEquals("2020-06-15T00:00",priceResponse.getStart().toString());
        assertEquals("2020-06-15T11:00",priceResponse.getEnd().toString());
        assertEquals(30.5,priceResponse.getPrice());
        assertEquals("EUR",priceResponse.getCurrency());
    }


    @Test
    public void testGetAt16062020T210000() throws Exception {

        MvcResult result = mockMvc.perform(get("/price/35455?brandId=1&date=16-06-2020T21:00:00")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        PriceResponse priceResponse = objectMapper.readValue(responseBody, PriceResponse.class);

        assertEquals(35455L,priceResponse.getProductId());
        assertEquals(1L,priceResponse.getBrandId());
        assertEquals(4L,priceResponse.getPriceList());
        assertEquals("2020-06-15T16:00",priceResponse.getStart().toString());
        assertEquals("2020-12-31T23:59:59",priceResponse.getEnd().toString());
        assertEquals(38.95,priceResponse.getPrice());
        assertEquals("EUR",priceResponse.getCurrency());
    }

    @Test
    public void shouldReturnNotFound() throws Exception {
       mockMvc.perform(get("/price/35456?brandId=1&date=14-06-2020T10:00:00")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/price/35456?brandId=1&date=14-06-2020T1000:00")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }



}
