package de.mobilab.api.services;

import de.mobilab.api.services.specifications.BaseSpecification;
import io.restassured.specification.RequestSpecification;

public abstract class ApiService {

    protected final RequestSpecification specification;

    ApiService() {
        this.specification = new BaseSpecification().create();
    }

}
