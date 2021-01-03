package de.mobilab.api.responses;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class AssertableResponse {

    private final ValidatableResponse response;

    public AssertableResponse(Response response) {
        this.response = response.then();
    }

    public <T> T as(Class<T> clazz) {
        return response.extract().body().as(clazz);
    }

    public AssertableResponse statusCode(int statusCode) {
        response.statusCode(statusCode);
        return this;
    }
}
