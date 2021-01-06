package de.mobilab.api.services.specifications;

import io.restassured.specification.RequestSpecification;

public interface Specification {

    RequestSpecification create();

}
