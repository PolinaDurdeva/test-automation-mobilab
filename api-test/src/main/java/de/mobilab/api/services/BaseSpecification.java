package de.mobilab.api.services;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseSpecification implements Specification {

    private static final Logger LOGGER = LogManager.getLogger(BaseSpecification.class);

    public BaseSpecification() {
    }

    @Override
    public RequestSpecification create() {
        LOGGER.info("Create base specification...");
        RequestSpecification spec = new RequestSpecBuilder()
                .log(LogDetail.URI)
                .addFilter(new AllureRestAssured())
                .build();
        LOGGER.info("Request specification is ready");
        return spec;
    }
}
