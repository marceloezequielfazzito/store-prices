package com.store.prices.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BadRequestResponse {

    @JsonProperty("error")
    private String error;

    public BadRequestResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
