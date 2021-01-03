package de.mobilab.api.services;

import io.restassured.specification.RequestSpecification;

public abstract class ApiService {

    protected final RequestSpecification specification;

    ApiService() {
        this.specification = new BaseSpecification().create();
    }

}
